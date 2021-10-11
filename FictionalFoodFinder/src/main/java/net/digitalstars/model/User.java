package net.digitalstars.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public abstract class User implements Serializable {
    @Id
    @Column
    protected int id;
    @Column
    protected String email;
    @Column
    protected String password;
    @Column
    protected String name;
    @Column
    protected String type;
    
    public User(){
        super();
        email = "default@email.com";
        password = "Password";
        name = "Your name here";
        type = "user";
    }
    
    public User(String email, String password, String name){
        super();
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

