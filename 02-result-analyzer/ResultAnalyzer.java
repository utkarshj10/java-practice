import java.util.Scanner;

public class ResultAnalyzer {

    String [] subjects;
    int [] marks;
    String maxSubject;
    String minSubject;
    int passCount = 0;
    int failCount = 0;
    int totalMarks = 0;
    double averageMarks = 0.0;

    void subjectNames(Scanner sc) {
        int subjectCount = 0;
        System.out.print("Enter the number of subjects: ");
        subjectCount = sc.nextInt();

        subjects = new String[subjectCount];
        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjects[i] = sc.next();
        }
        
    }

    void subjectMarks(Scanner sc) {
        marks = new int[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter marks for " + subjects[i] + ": ");
            marks[i] = sc.nextInt();
        }
    }

    int maxMarks(int[] marks) {
        int max = marks[0];
        maxSubject = subjects[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > max) {
                max = marks[i];
                maxSubject = subjects[i];
            }
        }
        return max;
    }

    int minMarks(int[] marks) {
        int min = marks[0];
        minSubject = subjects[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < min) {
                min = marks[i];
                minSubject = subjects[i];
            }
        }
        return min;
    }

    void analysis(int[] marks) {
        passCount = 0;
        failCount = 0;
        totalMarks = 0;
        averageMarks = 0.0;
        for (int grade : marks) {
            if (grade >= 40) {
                passCount++;
            } else {
                failCount++;
            }
        }
        
        for (int mark : marks) {
            totalMarks += mark;
        }
        averageMarks = (double) totalMarks / marks.length;

    }

    void displayResults() {
        System.out.println("Results Analysis:\n");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);
        System.out.println("Maximum Marks: " + maxMarks(marks) + " (" + maxSubject + ")");
        System.out.println("Minimum Marks: " + minMarks(marks) + " (" + minSubject + ")");
        System.out.println("Number of Subjects Passed: " + passCount);
        System.out.println("Number of Subjects Failed: " + failCount);
    }

    public static void main(String[] args) {
        ResultAnalyzer result = new ResultAnalyzer();
        Scanner sc = new Scanner(System.in);
        result.subjectNames(sc);
        result.subjectMarks(sc);
        result.analysis(result.marks);
        result.displayResults();
        sc.close();
    }
}
