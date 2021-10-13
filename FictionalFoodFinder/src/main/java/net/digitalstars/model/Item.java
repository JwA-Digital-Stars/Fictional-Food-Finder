package net.digitalstars.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Entity @Table(name="item")
public class Item implements Serializable{
    @Getter @Setter
    @ToString @EqualsAndHashCode
    @NoArgsConstructor @AllArgsConstructor
    @Embeddable
    public class ItemId implements Serializable{
        @Column
        private String name;
        @OneToOne
        private Truck truck;
    }
    
    @EmbeddedId
    private ItemId id;
    @Column
    private float cost;
    
    @Autowired
    public Item(String name, Truck truck){
        super();
        id.setName(name);
        id.setTruck(truck);
    }
    
    public String menuFormat(){
        return String.format("%s ---- $%.2f", id.getName(), cost);
    }
}