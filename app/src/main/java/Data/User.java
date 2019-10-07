package Data;

public class User {
    private int id;
    private String account;
    private String password;
    private String email;


    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
