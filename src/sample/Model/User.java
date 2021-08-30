package sample.Model;

public class User {

    private int userID;
    private String userName;
    private String password;


    public User (int userId, String username, String password){
        this.userID=userId;
        this.userName=username;
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getUserID(){
        return userID;
    }
}