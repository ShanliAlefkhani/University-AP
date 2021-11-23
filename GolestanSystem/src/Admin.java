import java.util.Scanner;

public class Admin extends Person {
    public Admin(String username, String password) { super(username, password); }
    void login() {
        if (this.username.equals("admin") && this.password.equals("123456")) {
            System.out.println("<ADMIN MENU>");
            this.menu();
        }
        else {
            System.out.println("Input info was wrong!");
        }
    }
    void menu() {
        boolean bool = true;
        while (bool) {
            System.out.println("0: Add book  1: Add food  2: Show professor info  3: Show student info  4: View books  5: View foods" +
                    " 6: View professors  7: View students  8: Exit");
            System.out.print("Input: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int x = Integer.parseInt(scanner.nextLine());
                switch (x) {
                    case 0:
                        System.out.print("Enter name of the book: ");
                        String inputName = scanner.nextLine();
                        this.addBook(inputName);
                        break;
                    case 1:
                        System.out.print("Enter name of the food: ");
                        inputName = scanner.nextLine();
                        System.out.print("Enter price of the food: ");
                        long inputPrice = Long.parseLong(scanner.nextLine());
                        System.out.print("Enter number of the food: ");
                        int inputNumber = Integer.parseInt(scanner.nextLine());
                        this.addFood(inputName, inputPrice, inputNumber);
                        break;
                    case 2:
                        System.out.print("Enter username of the professor: ");
                        inputName = scanner.nextLine();
                        boolean b = true;
                        for (int i = 0; i < Professor.counter; i++) {
                            if (Professor.list[i].username.equals(inputName)) {
                                this.showProfessorInfo(Professor.list[i]);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            System.out.println("There is no professor with this username!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter username of the student: ");
                        inputName = scanner.nextLine();
                        b = true;
                        for (int i = 0; i < Student.counter; i++) {
                            if (Student.list[i].username.equals(inputName)) {
                                this.showStudentInfo(Student.list[i]);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            System.out.println("There is no student with this username!");
                        }
                        break;
                    case 4:
                        this.viewBooks();
                        break;
                    case 5:
                        this.viewFoods();
                        break;
                    case 6:
                        this.viewProfessors();
                        break;
                    case 7:
                        this.viewStudents();
                        break;
                    case 8:
                        bool = false;
                        break;
                    default:
                        System.out.println("!!!");
                }
            }
            catch (Exception e) {
                System.out.println("!!!");
            }
        }
    }
    void addBook(String name) {
        Book book = new Book(name);
        boolean b = true;
        for (int i = 0; i < Book.counter; i++) {
            if (Book.list[i].name.equals(name)) {
                System.out.println("This book already exists!");
                b = false;
                break;
            }
        }
        if (b) {
            Book.list[Book.counter++] = book;
            System.out.println("Book added successfully!");
        }
    }
    void addFood(String name, long price, int number) {
        Food food = new Food(name, price, number);
        boolean b = true;
        for (int i = 0; i < Food.counter; i++) {
            if (Food.list[i].name.equals(name)) {
                System.out.println("This food already exists!");
                b = false;
                break;
            }
        }
        if (b) {
            Food.list[Food.counter++] = food;
            System.out.println("Food added successfully!");
        }
    }
    void showProfessorInfo(Professor professor) {
        System.out.println(professor.username + " " + professor.password);
    }
    void showStudentInfo(Student student) {
        System.out.println(student.username + " " + student.password);
    }
    static void viewBooks() {
        if (Book.counter == 0) {
            System.out.println("There is no book!");
            return;
        }
        for (int i = 0; i < Book.counter; i++) {
            System.out.println(i + ": " + Book.list[i].name + " ");
        }
        System.out.println();
    }
    static void viewFoods() {
        if (Food.counter == 0) {
            System.out.println("There is no food!");
            return;
        }
        for (int i = 0; i < Food.counter; i++) {
            System.out.println(i + ": " + Food.list[i].name + " ");
        }
        System.out.println();
    }
    static void viewLessons() {
        if (Lesson.counter == 0) {
            System.out.println("There is no lesson!");
            return;
        }
        for (int i = 0; i < Lesson.counter; i++) {
            System.out.println(i + ": " + Lesson.list[i].name + " ");
        }
        System.out.println();
    }
    static void viewProfessors() {
        if (Professor.counter == 0) {
            System.out.println("There is no professor!");
            return;
        }
        for (int i = 0; i < Professor.counter; i++) {
            System.out.println(i + ": " + Professor.list[i].username + " ");
        }
        System.out.println();
    }
    static void viewStudents() {
        if (Student.counter == 0) {
            System.out.println("There is no student!");
            return;
        }
        for (int i = 0; i < Student.counter; i++) {
            System.out.println(i + ": " + Student.list[i].username + " ");
        }
        System.out.println();
    }
}