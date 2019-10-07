package POJO;

public class User {
    private int userid;
    private String mail;
    private String username;
    private String password;
    public User(int userid,String mail,String username,String password){
        this.userid=userid;
        this.mail=mail;
        this.username=username;
        this.password=password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
