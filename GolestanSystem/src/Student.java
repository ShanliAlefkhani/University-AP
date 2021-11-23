import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Student extends Person{
    static int counter = 0;
    static Student[] list = new Student[100];

    Lesson[] lessonList = new Lesson[100];
    int lessonCounter = 0;
    Book borrowedBook;
    Food reservedFood;
    long money;

    public Student(String username, String password) {
        super(username, password);
    }
    static void input() {
        File f = new File("src\\Students.txt");
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNextLine()) {
                String s = fileInput.nextLine();
                String[] spl = s.split(" ");
                list[counter] = new Student(spl[0], spl[1]);
                list[counter].lessonCounter = Integer.parseInt(fileInput.nextLine());
                for (int i = 0; i < list[counter].lessonCounter; i++) {
                    list[counter].lessonList[i] = Lesson.list[Lesson.is(fileInput.nextLine())];
                }
                int x = Book.is(fileInput.nextLine());
                if (x != -1) {
                    list[counter].borrowedBook = Book.list[x];
                }
                x = Food.is(fileInput.nextLine());
                if (x != -1) {
                    list[counter].reservedFood = Food.list[x];
                }
                list[counter].money = fileInput.nextLong();
                counter++;
            }
        } catch (FileNotFoundException e) {}
    }
    static void output() {
        try {
            File f = new File ("src\\Students.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < counter; i++) {
                if (i != 0) {
                    fw.write("\n");
                }
                fw.write(list[i].toString());
            }
            fw.close();
        }
        catch (IOException ef) {}
    }
    static int is(String name) {
        for (int i = 0; i < counter; i++) {
            if (name.equals(Student.list[i].username)) {
                return i;
            }
        }
        return -1;
    }
    void login() {
        boolean b = true;
        for (int i = 0; i < Student.counter; i++) {
            if (this.username.equals(Student.list[i].username) && this.password.equals(Student.list[i].password)) {
                System.out.println("<STUDENT MENU>");
                Student.list[i].menu();
                b = false;
                break;
            }
        }
        if (b) {
            System.out.println("Input info was wrong!");
        }
    }
    void menu() {
        boolean bool = true;
        while (bool) {
            System.out.println("0:ViewBookList 1:BorrowBook 2:ReturnBook 3:ViewBorrowedBook 4:ViewFoodList 5:AddFood 6:RemoveFood\n" +
                    "7:viewReservedFood 8:ViewExistedLessonList 9:AddLesson 10:ViewMyLessonList 11:AddMoney 12: viewMoney 13:Exit");
            System.out.print("Input: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int x = Integer.parseInt(scanner.nextLine());

                switch (x) {
                    case 0:
                        Admin.viewBooks();
                        break;
                    case 1:
                        System.out.print("Enter name of the book: ");
                        String inputName = scanner.nextLine();
                        boolean b = true;
                        for (int i = 0; i < Book.counter; i++) {
                            if (Book.list[i].name.equals(inputName)) {
                                this.borrowBook(Book.list[i]);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            System.out.println("This book doesn't exists!");
                        }
                        break;
                    case 2:
                        this.returnBook();
                        break;
                    case 3:
                        this.viewBorrowedBook();
                        break;
                    case 4:
                        Admin.viewFoods();
                        break;
                    case 5:
                        System.out.print("Enter name of the food: ");
                        inputName = scanner.nextLine();
                        b = true;
                        for (int i = 0; i < Food.counter; i++) {
                            if (Food.list[i].name.equals(inputName)) {
                                this.reserveFood(Food.list[i]);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            System.out.println("This food doesn't exists!");
                        }
                        break;
                    case 6:
                        this.removeFood();
                        break;
                    case 7:
                        this.viewReservedFood();
                        break;
                    case 8:
                        Admin.viewLessons();
                        break;
                    case 9:
                        System.out.print("Enter name of the lesson: ");
                        inputName = scanner.nextLine();
                        b = true;
                        for (int i = 0; i < Lesson.counter; i++) {
                            if (Lesson.list[i].name.equals(inputName)) {
                                this.addLesson(Lesson.list[i]);
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            System.out.println("This lesson doesn't exists!");
                        }
                        break;
                    case 10:
                        this.viewLessonList();
                        break;
                    case 11:
                        System.out.print("Enter amount of money: ");
                        long inputMoney = Long.parseLong(scanner.nextLine());
                        this.addMoney(inputMoney);
                        break;
                    case 12:
                        this.viewMoney();
                        break;
                    case 13:
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
    boolean reserveFood(Food food) {
        if (reservedFood == null) {
            if (food.reserve()) {
                if (money >= food.price) {
                    reservedFood = food;
                    money -= food.price;
                    System.out.println("Food reserved successfully!");
                    return true;
                }
                System.out.println("Your money isn't enough!");
                return false;
            }
            System.out.println("this food is finished!");
            return false;
        }
        System.out.println("You have already reserved a food!");
        return false;
    }
    void removeFood() {
        if (reservedFood != null) {
            reservedFood.remove();
            money += reservedFood.price * 90 / 100;
            reservedFood = null;
            System.out.println("Food removed successfully!");
        }
        else {
            System.out.println("You didn't reserve any food!");
        }
    }
    void viewReservedFood() {
        if (reservedFood != null) {
            System.out.println(reservedFood.name);
        }
        else {
            System.out.println("You didn't reserved any food!");
        }
    }
    boolean borrowBook(Book book) {
        if (borrowedBook == null) {
            if (book.borrow(this.username)) {
                borrowedBook = book;
                System.out.println("Book borrowed successfully!");
                return true;
            }
            System.out.println("This book is already borrowed!");
            return false;
        }
        System.out.println("You have already borrowed a book!");
        return false;
    }
    void returnBook() {
        if (borrowedBook != null) {
            borrowedBook.retUrn();
            borrowedBook = null;
            System.out.println("Book returned successfully!");
        }
        else {
            System.out.println("You didn't borrow any book!");
        }
    }
    void viewBorrowedBook() {
        if (borrowedBook != null) {
            System.out.println(borrowedBook.name);
        }
        else {
            System.out.println("You didn't borrow any book!");
        }
    }
    boolean addLesson(Lesson lesson) {
        if (lesson.addStudent(this.username)) {
            lessonList[lessonCounter++] = lesson;
            System.out.println("lesson added successfully!");
            return true;
        }
        System.out.println("class dige ja nadare ://");
        return false;
    }
    void addMoney(long money) {
        this.money += money;
        System.out.println("Your money added successfully!");
    }
    void viewMoney() {
        System.out.println(money);
    }
    void viewLessonList() {
        if (lessonCounter == 0) {
            System.out.println("You have no lessons!");
            return;
        }
        for (int i = 0; i < lessonCounter; i++) {
            System.out.println(i + ": " + lessonList[i].name + " ");
        }
        System.out.println();
    }
    @Override
    public String toString() {
        String s = this.username + " " + this.password + "\n" + this.lessonCounter;
        for (int i = 0; i < lessonCounter; i++) {
            s += "\n" + lessonList[i].name;
        }
        if (borrowedBook != null) {
            s += "\n" + borrowedBook.name;
        }
        else {
            s += "\n-";
        }
        if (reservedFood != null) {
            s += "\n" + reservedFood.name;
        }
        else {
            s += "\n-";
        }
        s += "\n" + this.money;
        return s;
    }
}