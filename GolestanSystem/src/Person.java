public abstract class Person {
    String username;
    String password;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
    abstract void login();
    abstract void menu();
}