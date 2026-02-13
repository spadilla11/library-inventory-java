package com.example.app;

import com.example.dao.BookDao;
import com.example.dao.MemberDao;
import com.example.model.Book;
import com.example.model.Member;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void manageBooks(BookDao dao, Scanner scanner) {

        System.out.print("Would you like to add[a], view[v], edit title[e], or delete[d] a book? ");

        String choice = scanner.nextLine();


        if (choice.equals("a")) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            dao.create(new Book(title, author)); // THIS IS WHAT ACTUALLY CREATES THE BOOK
            System.out.println("Book added.");
            System.out.println();

        } else if (choice.equals("v")) {

            List<Book> books = dao.findAll();

            if (books.isEmpty()){
                System.out.println("No books saved yet.");
                System.out.println();
            } else {
                books.forEach(book -> {
                    System.out.println("Book Id: " + book.getId());
                    System.out.println("Book Author: " + book.getAuthor());
                    System.out.println("Book Title: " + book.getTitle());
                    System.out.println();
                });
            }


        } else if (choice.equals("e")) {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();

            dao.updateTitle(id, newTitle);
            System.out.println("Book title updated.");
            System.out.println();

        } else if (choice.equals("d")) {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            dao.deleteById(id);
            System.out.println("Book deleted.");
            System.out.println();
        } else {
            System.out.println("Invalid input.");
        }
    }


    public static void manageMembers(MemberDao dao, Scanner scanner) {

        System.out.print("Would you like to add[a], view[v], edit email[e], or delete[d] a member.? ");

        String choice = scanner.nextLine();
        if (choice.equals("a")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            dao.create(new Member(name, email));
            System.out.println("Member added.");
            System.out.println();
        }
        else if (choice.equals("v")) {
            List<Member> members = dao.findAll();

            if (members.isEmpty()){
                System.out.println("No members saved yet.");
                System.out.println();
            } else {
                members.forEach(member -> {
                    System.out.println("Member ID: " + member.getId());
                    System.out.println("Member Name: " + member.getName());
                    System.out.println("Member Email: " + member.getEmail());
                });
            }
        }
        else if (choice.equals("e")) {
            System.out.print("Enter member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            dao.updateEmail(id, email);
            System.out.println("Member email updated.");
            System.out.println();
        }
        else if (choice.equals("d")) {
            System.out.print("Enter member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            dao.deleteById(id);
            System.out.println("Member deleted.");
            System.out.println();
        }
        else {
            System.out.println("Invalid input.");
        }
    }

    public static void main(String[] args) {

        BookDao bookDao = new BookDao();
        MemberDao memberDao = new MemberDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Library Management System!");
            System.out.print("Would you like to manage Books[b], Members[m] or Quit[q]? ");

            String choice = scanner.nextLine();

            if (choice.equals("b")) {
                manageBooks(bookDao, scanner);
            }
            else if (choice.equals("m")) {
                manageMembers(memberDao, scanner);

            }
            else if (choice.equals("q")) {
                System.out.println("You are leaving the program...BYE");
                return;
            }
            else {
                System.out.println("Invalid input.");
                System.out.println();
            }
        }
    }

}



