package com.example.dao;

import com.example.db.Db;
import com.example.model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDao {
    // CREATE
    public int create(Member member) {
        String sql = "INSERT INTO members (name, email) VALUES (?, ?) RETURNING id";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int id = rs.getInt("id");
                member.setId(id);
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating Member", e);
        }
    }

    // READ (all)
    public List<Member> findAll() {
        String sql = "SELECT id, name, email FROM members ORDER BY id";
        List<Member> results = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapRow(rs));
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException("Error reading members", e);
        }
    }

    // READ (by id)
    public Optional<Member> findById(int id) {
        String sql = "SELECT id, name, email FROM members WHERE id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding Member by id", e);
        }
    }

    // UPDATE
    public boolean updateEmail(int id, String newEmail) {
        String sql = "UPDATE members SET email = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newEmail);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating Member", e);
        }
    }

    // DELETE
    public boolean deleteById(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Member", e);
        }
    }

    private Member mapRow(ResultSet rs) throws SQLException {
        return new Member(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}
