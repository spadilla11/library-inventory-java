package com.example.app;

import com.example.dao.StudentDao;
import com.example.model.Student;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();

        // CREATE
        Student s = new Student("Brittany", "Sample", "brittany.sample@example.com");
        int newId = dao.create(s);
        System.out.println("Created: " + s);

        // READ ALL
        System.out.println("\nAll Students:");
        dao.findAll().forEach(System.out::println);

        // READ BY ID
        System.out.println("\nFind By ID " + newId + ":");
        System.out.println(dao.findById(newId).orElse(null));

        // UPDATE
        System.out.println("\nUpdate Email:");
        boolean updated = dao.updateEmail(newId, "brittany.updated@example.com");
        System.out.println("Updated? " + updated);
        System.out.println(dao.findById(newId).orElse(null));

        // DELETE
        System.out.println("\nDelete:");
        boolean deleted = dao.deleteById(newId);
        System.out.println("Deleted? " + deleted);

        // VERIFY
        System.out.println("\nAll Students After Delete:");
        dao.findAll().forEach(System.out::println);
    }
}
