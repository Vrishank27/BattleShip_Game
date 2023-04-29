package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    // private static User u = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // private User(){}

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public static User getInstance(){
    //     if(u == null){
    //         User user = new User();
    //         u = user;
    //         return user;
    //     }
    //     else{
    //         return u;
    //     }
    // }

    // public static User getInstance(String usn , String pwd){
    //     if(u == null){
    //         User user = new User(usn , pwd);
    //         u =  user;
    //         return u;
    //     }
    //     return u;
    // }
}
