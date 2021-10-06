package com.example.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public abstract class User implements Serializable {
    @Embeddable
    public class UserID{
        @Column
        public String email;
        @Column
        public String type;
    }
    
    @EmbeddedId
    protected UserID id;
    @Column
    protected String password;
    @Column
    protected String name;

    public User(){
        super();
        id.email = "default@email.com";
        password = "Password";
        name = "Your name here";
    }
    
    public User(String email){
        super();
        id.email = email;
        id.type = "user";
        password = "Password";
        name = "Your name here";
    }
    
    public User(String email, String password, String name){
        super();
        id.email = email;
        this.password = password;
        this.name = name;
    }
    
    public String getEmail(){
        return id.email;
    }
    
    public void setEmail(String email){
        id.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getType() {
        return id.type;
    }

    public void setType(String type) {
        id.type = type;
    }
    
    public UserID getUserID(){
        return id;
    }
    
    public void setUserID(UserID id){
        this.id = id;
    }
    
}

