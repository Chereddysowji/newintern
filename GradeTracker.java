import java.util.ArrayList;
import java.util.Scanner;

// Student class to store name and grades
class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.printf("Average: %.2f\n", getAverage());
        System.out.println("------------------------");
    }

    public String getName() {
        return name;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Show All Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    students.add(new Student(name));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students available. Please add a student first.");
                        break;
                    }

                    System.out.println("Select a student by number:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i).getName());
                    }

                    int studentIndex = -1;
                    if (scanner.hasNextInt()) {
                        studentIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // consume newline
                    }

                    if (studentIndex < 0 || studentIndex >= students.size()) {
                        System.out.println("Invalid student number.");
                        break;
                    }

                    System.out.print("Enter grade (0 - 100): ");
                    if (scanner.hasNextDouble()) {
                        double grade = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        if (grade < 0 || grade > 100) {
                            System.out.println("Grade must be between 0 and 100.");
                        } else {
                            students.get(studentIndex).addGrade(grade);
                            System.out.println("Grade added.");
                        }
                    } else {
                        System.out.println("Invalid grade input.");
                        scanner.nextLine(); // clear invalid input
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students to show.");
                    } else {
                        for (Student student : students) {
                            student.printInfo();
                        }
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}