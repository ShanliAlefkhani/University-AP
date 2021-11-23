import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Food.input();
        Book.input();
        Lesson.input();
        Professor.input();
        Student.input();

        System.out.println("<LOGIN MENU>");
        while (true) {
            System.out.print("Enter your type(0: Admin / 1: Professor / 2: Student / 3: Exit): ");
            Scanner scanner = new Scanner(System.in);
            try {
                int inputType = Integer.parseInt(scanner.nextLine());
                if (inputType > 3) {
                    System.out.println("!!!");
                    continue;
                }
                if (inputType == 3) {
                    Food.output();
                    Book.output();
                    Lesson.output();
                    Professor.output();
                    Student.output();
                    break;
                }
                System.out.print("Enter username: ");
                String inputUser = scanner.nextLine();
                System.out.print("Enter password: ");
                String inputPass = scanner.nextLine();
                Person p;
                switch (inputType) {
                    case 0:
                        p = new Admin(inputUser, inputPass);
                        p.login();
                        break;
                    case 1:
                        p = new Professor(inputUser, inputPass);
                        p.login();
                        break;
                    case 2:
                        p = new Student(inputUser, inputPass);
                        p.login();
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("!!!");
            }
        }
    }
}