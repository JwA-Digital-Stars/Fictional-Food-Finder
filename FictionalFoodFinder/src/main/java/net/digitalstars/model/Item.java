package net.digitalstars.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    @Id
    @GeneratedValue(generator="item_id_seq",strategy=GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize=1, name="request_id_seq", sequenceName="request_id_seq")
    private long id;
    @Column
    private String name;
    @Column
    private float cost;
    @ManyToOne
    private Truck truck;
    
    public Item(String name, float cost, Truck truck){
        super();
        id = 0;
        this.name = name;
        this.cost = cost;
        this.truck = truck;
    }
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
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
