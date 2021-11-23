import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Food {
    static int counter = 0;
    static Food[] list = new Food[100];

    String name;
    long price;
    int number;

    public Food(String name, long price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }
    static void input () {
        File f = new File ("src\\Foods.txt");
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNextLine()) {
                String inputName = fileInput.nextLine();
                Long inputPrice = Long.parseLong(fileInput.nextLine());
                int inputNum = Integer.parseInt(fileInput.nextLine());
                list[counter] = new Food(inputName, inputPrice, inputNum);
                counter++;
            }
        } catch (FileNotFoundException e) {}
    }
    static void output() {
        try {
            File f = new File ("src\\Foods.txt");
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
            if (name.equals(Food.list[i].name)) {
                return i;
            }
        }
        return -1;
    }
    boolean reserve() {
        if (this.number > 0) {
            this.number--;
            return true;
        }
        return false;
    }
    void remove() {
        this.number++;
    }
    @Override
    public String toString() {
        String s = this.name + "\n" + this.price + "\n" + this.number;
        return s;
    }
}