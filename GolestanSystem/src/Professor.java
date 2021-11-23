import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Professor extends Person{
    static int counter = 0;
    static Professor[] list = new Professor[100];

    int classCounter = 0;
    Lesson[] classList = new Lesson[100];

    public Professor(String username, String password) {
        super(username, password);
    }
    static void input() {
        File f = new File("src\\Professors.txt");
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNextLine()) {
                String s = fileInput.nextLine();
                String[] spl = s.split(" ");
                list[counter] = new Professor(spl[0], spl[1]);
                list[counter].classCounter = Integer.parseInt(fileInput.nextLine());
                for (int i = 0; i < list[counter].classCounter; i++) {
                    list[counter].classList[i] = Lesson.list[Lesson.is(fileInput.nextLine())];
                }
                counter++;
            }
        } catch (FileNotFoundException e) {}
    }
    static void output() {
        try {
            File f = new File ("src\\Professors.txt");
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
            if (name.equals(Professor.list[i].username)) {
                return i;
            }
        }
        return -1;
    }
    void login() {
        boolean b = true;
        for (int i = 0; i < Professor.counter; i++) {
            if (this.username.equals(Professor.list[i].username) && this.password.equals(Professor.list[i].password)) {
                System.out.println("<PROFESSOR MENU>");
                Professor.list[i].menu();
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
            //view class counter inja
            System.out.println("0: Set class  1: View class list  2: Exit");
            System.out.print("Input: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int x = Integer.parseInt(scanner.nextLine());

                switch (x) {
                    case 0:
                        System.out.print("Enter name of the class: ");
                        String inputName = scanner.nextLine();
                        System.out.print("Enter the capacity of the class: ");
                        int inputCapacity = Integer.parseInt(scanner.nextLine());
                        this.setClass(inputName, inputCapacity);
                        break;
                    case 1:
                        this.viewClassList();
                        break;
                    case 2:
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
    void setClass(String name, int classCapacity) {
        Lesson lesson = new Lesson(name, this.username, classCapacity);
        classList[classCounter++] = lesson;
        Lesson.list[Lesson.counter++] = lesson;
        System.out.println("You have set the class successfully!");
    }
    void viewClassList() {
        if (classCounter == 0) {
            System.out.println("You have no class!");
            return;
        }
        for (int i = 0; i < classCounter; i++) {
            System.out.print(i + ": " + classList[i].name + "\n");
        }
        System.out.println();
    }
    @Override
    public String toString() {
        String s = this.username + " " + this.password + "\n" + this.classCounter;
        for (int i = 0; i < this.classCounter; i++) {
            s += "\n" + classList[i].name;
        }
        return s;
    }
}