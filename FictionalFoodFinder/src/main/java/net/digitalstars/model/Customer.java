package net.digitalstars.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
    private List<Truck> favorites;
    
    @Autowired
    public Customer(String email, String password, String name){
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        favorites = new ArrayList<>();
    }
}

