package agency.model;

public class Director extends User {

    public Director(int id, String username, String password) {
        super(id, username, password, "director");
    }


    public void printInfo() {
        System.out.println("Director: " + getUsername());
    }
}
