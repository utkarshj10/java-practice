import java.util.Scanner;

public class StudentPerformance {

    String name;
    int marks1, marks2, marks3, marks4, marks5;
    int totalMarks;
    double percentage;
    char grade;
    String passFail;

    char Grade(double percentage) {
        if (percentage >= 90) {
            grade = 'A';
        } else if (percentage >= 80) {
            grade = 'B';
        } else if (percentage >= 70) {
            grade = 'C';
        } else if (percentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }

    String passOrFail() {
        if (marks1 >= 40 && marks2 >= 40 && marks3 >= 40 && marks4 >= 40 && marks5 >= 40) {
            passFail = "Pass";
        } else {
            passFail = "Fail";
        }
        return passFail;
    }

    StudentPerformance(String name, int marks1, int marks2, int marks3, int marks4, int marks5) {
        this.name = name;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.marks4 = marks4;
        this.marks5 = marks5;
        this.totalMarks = marks1 + marks2 + marks3 + marks4 + marks5;
        percentage = (double) totalMarks / 5;          //for 5 subjects only
    }

    void displayResults() {
        System.out.println("Student Name: " + name);
        System.out.println("Marks in Subject 1: " + marks1);
        System.out.println("Marks in Subject 2: " + marks2);
        System.out.println("Marks in Subject 3: " + marks3);
        System.out.println("Marks in Subject 4: " + marks4);
        System.out.println("Marks in Subject 5: " + marks5);
        System.out.printf("\nTotal Marks: %d/500\n", totalMarks);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + Grade(percentage));
        System.out.println("Result: " + passOrFail());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Subject1: ");
        int marks1 = sc.nextInt();

        System.out.print("Subject2: ");
        int marks2 = sc.nextInt();

        System.out.print("Subject3: ");
        int marks3 = sc.nextInt();

        System.out.print("Subject4: ");
        int marks4 = sc.nextInt();

        System.out.print("Subject5: ");
        int marks5 = sc.nextInt();

        StudentPerformance student = new StudentPerformance(name, marks1, marks2, marks3, marks4, marks5);

        student.displayResults();

        sc.close();
    }
}