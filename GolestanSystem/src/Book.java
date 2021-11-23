import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Book {
    static int counter = 0;
    static Book[] list = new Book[100];

    String name;
    boolean isAvailable = true;
    String borrower;

    public Book(String name) {
        this.name = name;
    }
    static void input() {
        File f = new File ("src\\Books.txt");
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNextLine()) {
                String inputName = fileInput.nextLine();
                boolean inputA = Boolean.parseBoolean(fileInput.nextLine());
                list[counter] = new Book(inputName);
                if (!inputA) {
                    list[counter].borrow(fileInput.nextLine());
                }
                counter++;
            }
        } catch (FileNotFoundException e) {}
    }
    static void output() {
        try {
            File f = new File ("src\\Books.txt");
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
            if (name.equals(Book.list[i].name)) {
                return i;
            }
        }
        return -1;
    }
    boolean borrow(String borrower) {
        if (isAvailable) {
            this.borrower = borrower;
            isAvailable = false;
            return true;
        }
        return false;
    }
    void retUrn() {
        borrower = null;
        isAvailable = true;
    }
    @Override
    public String toString() {
        String s = this.name + "\n" + this.isAvailable;
        if (!this.isAvailable) {
            s += "\n" + this.borrower;
        }
        return s;
    }
}