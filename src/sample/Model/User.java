package sample.Model;

/**
 * User Class
 */
public class User {

    private int userID;
    private String userName;
    private String password;


    /**
     * @param userId User ID
     * @param username Username
     * @param password User Password
     */
    public User (int userId, String username, String password){
        this.userID=userId;
        this.userName=username;
        this.password=password;
    }

    /**
     * @param userId User ID
     * @param username UserName
     */
    public User (int userId, String username){
        this.userID=userId;
        this.userName=username;
    }

    /**
     * @return returns the user password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * @return gets the username
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * @return gets the user ID
     */
    public int getUserID(){
        return userID;
    }

    /**
     * @return Overriding the to string method to print username
     */
    public String toString(){
        return userName;
    }
}
