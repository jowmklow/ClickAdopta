package local.gonzalez.clickadopta;
import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String username;
    private String password;

    public Client (int id, String username, String password,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Client(int id, String username, String password) {
        this(id,username,password,"user");
    }

    public Client(String username, String password) {
        this(0,username,password,"user");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
