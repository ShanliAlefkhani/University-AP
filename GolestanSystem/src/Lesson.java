import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lesson {
    static int counter = 0;
    static Lesson[] list = new Lesson[100];

    String name;
    String professor;
    int classCapacity;
    int studentCounter = 0;
    String[] studentList = new String[100];

    public Lesson(String name, String professor, int classCapacity) {
        this.name = name;
        this.professor = professor;
        this.classCapacity = classCapacity;
    }
    static void input () {
        File f = new File ("src\\Lessons.txt");
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNextLine()) {
                String inputName = fileInput.nextLine();
                String inputPName = fileInput.nextLine();
                int inputCC = Integer.parseInt(fileInput.nextLine());
                list[counter] = new Lesson(inputName, inputPName, inputCC);
                int inputSC = Integer.parseInt(fileInput.nextLine());
                for (int i = 0; i < inputSC; i++) {
                    String s = fileInput.nextLine();
                    list[counter].addStudent(s);
                }
                counter++;
            }
        } catch (FileNotFoundException e) {}
    }
    static void output() {
        try {
            File f = new File ("src\\Lessons.txt");
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
            if (name.equals(Lesson.list[i].name)) {
                return i;
            }
        }
        return -1;
    }
    boolean addStudent(String student) {
        if (classCapacity > 0) {
            studentList[studentCounter++] = student;
            classCapacity--;
            return true;
        }
        System.out.println("Class is full!"); //inja
        return false;
    }
    void viewStudentList() {
        if (studentCounter == 0) {
            System.out.println("There are no students!");
            return;
        }
        for (int i = 0; i < studentCounter; i++) {
            System.out.print(i + ": " + studentList[i] + " ");
        }
        System.out.println();
    }
    @Override
    public String toString() {
        String s = this.name + "\n" + this.professor + "\n" + this.classCapacity + "\n" + this.studentCounter;
        for (int i = 0; i < this.studentCounter; i++) {
            s += "\n" + this.studentList[i];
        }
        return s;
    }
}