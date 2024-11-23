import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

public class ASM2 {

    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Enter student list");
            System.out.println("2. Find students by last name");
            System.out.println("3. Find and edit students by full name");
            System.out.println("4. End");
            System.out.print("Enter your choice (1-4): ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    enterStudentList();
                    break;
                case 2:
                    findStudentsByLastName();
                    break;
                case 3:
                    findAndEditStudentByFullName();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enterStudentList() {
        System.out.println("\n--- Add Students ---");
        while (true) {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            students.add(new Student(firstName, lastName));
            System.out.println("Student added successfully.");

            System.out.print("Add another student? (y/n): ");
            String more = scanner.nextLine().trim().toLowerCase();
            if (!more.equals("y")) {
                break;
            }
        }
    }

    private static void findStudentsByLastName() {
        System.out.println("\n--- Find Students by Last Name ---");
        System.out.print("Enter last name to search: ");
        String lastName = scanner.nextLine().trim();

        boolean found = false;
        for (Student student : students) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with that last name.");
        }
    }

    private static void findAndEditStudentByFullName() {
        System.out.println("\n--- Find and Edit Student by Full Name ---");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        boolean found = false;
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName) &&
                student.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println("Found: " + student);
                System.out.print("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                System.out.print("Enter new last name: ");
                String newLastName = scanner.nextLine();
                student = new Student(newFirstName, newLastName);
                students.add(student);
                students.removeIf(s -> s.getFirstName().equalsIgnoreCase(firstName) && s.getLastName().equalsIgnoreCase(lastName));
                System.out.println("Student details updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No student found with that full name.");
        }
    }
}

