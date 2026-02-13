package com.example.dao;

import com.example.db.Db;
import com.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {
    // CREATE
    public int create(Book book) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?) RETURNING id";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int id = rs.getInt("id");
                book.setId(id);
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating Book", e);
        }
    }

    // READ (all)
    public List<Book> findAll() {
        String sql = "SELECT id, title, author FROM books ORDER BY id";
        List<Book> results = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapRow(rs));
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException("Error reading books", e);
        }
    }

    // READ (by id)
    public Optional<Book> findById(int id) {
        String sql = "SELECT id, title, author FROM books WHERE id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding Book by id", e);
        }
    }

    // UPDATE
    public boolean updateTitle(int id, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newTitle);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating Book", e);
        }
    }

    // DELETE
    public boolean deleteById(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Book", e);
        }
    }

    private Book mapRow(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author")
        );
    }
}
