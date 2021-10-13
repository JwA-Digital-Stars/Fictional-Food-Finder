package net.digitalstars.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

@Entity @Table(name="owner")
public class Owner implements Serializable{
    @Id @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @OneToOne
    private Truck truck;
    
    @Autowired
    public Owner(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
        truck = new Truck();
    }
}

