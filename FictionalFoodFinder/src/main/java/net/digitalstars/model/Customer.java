package net.digitalstars.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="customer")
public class Customer implements Serializable{

    @Id @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @ManyToMany
    @Transient
    private List<Truck> favorites;
    
    @Autowired
    public Customer(String email, String password, String name){
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        favorites = new ArrayList<>();
    }

    public String getEmail() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Truck> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Truck> favorites) {
        this.favorites = favorites;
    }
    
}

