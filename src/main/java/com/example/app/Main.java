package com.example.app;

import com.example.dao.BookDao;
import com.example.dao.MemberDao;
import com.example.model.Book;
import com.example.model.Member;

import java.util.Scanner;

public class Main {

    public static void manageBooks(BookDao dao, Scanner scanner) {

        System.out.print("Would you like to add[a], view[v], edit[e], or delete[d] a book? ");

        String choice = scanner.nextLine();


        if (choice.equals("a")) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            dao.create(new Book(title, author));
            System.out.println("Book added.");
        } else if (choice.equals("v")) {
            dao.findAll().forEach(System.out::println);
        } else if (choice.equals("e")) {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();

            dao.updateTitle(id, newTitle);
            System.out.println("Book updated.");
        } else if (choice.equals("d")) {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();

            dao.deleteById(id);
            System.out.println("Book deleted.");
        } else {
            System.out.println("Invalid input.");
        }
    }


    public static void manageMembers(MemberDao dao, Scanner scanner) {

        System.out.print("Would you like to add[a], view[v], edit[e], or delete[d] a member?");

        String choice = scanner.nextLine();


        if (choice.equals("a")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            dao.create(new Member(name, email));
            System.out.println("Member added.");
        }
        else if (choice.equals("v")) {
            dao.findAll().forEach(System.out::println);
        }
        else if (choice.equals("e")) {
            System.out.print("Enter member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            dao.updateEmail(id, email);
            System.out.println("Member updated.");
        }
        else if (choice.equals("d")) {
            System.out.print("Enter member ID: ");
            int id = scanner.nextInt();

            dao.deleteById(id);
            System.out.println("Member deleted.");
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
            System.out.println("Welcome to Book Management System");
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
            }
        }
    }

}



