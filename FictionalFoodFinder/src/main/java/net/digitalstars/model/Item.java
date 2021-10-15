package net.digitalstars.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="item")
public class Item implements Serializable{

    @Id @Column
    private int id;
    @Column
    private String name;
    @Column
    private float cost;
    @ManyToOne
    private Truck truck;
    
    public Item(String name, float cost, Truck truck){
        super();
        this.name = name;
        this.cost = cost;
        this.truck = truck;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
    
    public String menuFormat(){
        return String.format("%s ---- $%.2f", name, cost);
    }
}
