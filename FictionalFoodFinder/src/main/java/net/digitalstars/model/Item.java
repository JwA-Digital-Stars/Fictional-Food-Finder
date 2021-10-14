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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Truck getTruck() {
            return truck;
        }

        public void setTruck(Truck truck) {
            this.truck = truck;
        }
        
    }
    
    @EmbeddedId
    private ItemId id;
    @Column
    private float cost;
    
    @Autowired
    public Item(String name, Truck truck){
        super();
        id.name = name;
        id.truck = truck;
    }

    public ItemId getId() {
        return id;
    }

    public void setId(ItemId id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
    public String menuFormat(){
        return String.format("%s ---- $%.2f", id.name, cost);
    }
}
