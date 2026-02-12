package com.example.model;

public class Member {
    private int id;
    private String name;
    private String email;

    public Member(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Member(String name, String email) {
        this(0, name, email);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getname() { return name; }
    public void setname(String name) { this.name= name; }

    public String getemail() { return email; }
    public void setemail(String email) { this.email = email; }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
