package agency.model;

public class Director extends User {

    public Director(int id, String username, String password) {
        super(id, username, password, "director");
    }

    // Здесь можно добавить методы, уникальные для директора
    public void printInfo() {
        System.out.println("Director: " + getUsername());
    }
}
