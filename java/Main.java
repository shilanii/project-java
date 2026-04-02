import java.util.*;

class Student {
    int id;
    String name;
    int age;
    String course;

    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name +
                ", Age: " + age + ", Course: " + course);
    }
}

public class Main {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== Student Database Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            choice = getChoice(); // ✅ strict validation

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: System.out.println("Exiting program..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    // ✅ Choice validation (no empty, no wrong input)
    static int getChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Choice cannot be empty! Please enter a choice.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter numbers only.");
            }
        }
    }

    // ✅ Integer validation (for ID, Age)
    static int getValidInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty! Please enter a value.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter numbers only.");
            }
        }
    }

    // ✅ String validation (for name, course)
    static String getValidString(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty! Please try again.");
        }
    }

    // Add student
    static void addStudent() {
        int id = getValidInt("Enter ID: ");
        String name = getValidString("Enter Name: ");
        int age = getValidInt("Enter Age: ");
        String course = getValidString("Enter Course: ");

        students.add(new Student(id, name, age, course));
        System.out.println("Student added successfully!");
    }

    // View all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    // Search student
    static void searchStudent() {
        int id = getValidInt("Enter ID to search: ");

        for (Student s : students) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Update student
    static void updateStudent() {
        int id = getValidInt("Enter ID to update: ");

        for (Student s : students) {
            if (s.id == id) {
                s.name = getValidString("Enter new name: ");
                s.age = getValidInt("Enter new age: ");
                s.course = getValidString("Enter new course: ");

                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Delete student
    static void deleteStudent() {
        int id = getValidInt("Enter ID to delete: ");

        boolean removed = students.removeIf(s -> s.id == id);

        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
}