package net.digitalstars.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item implements Serializable{
    @Embeddable
    public class ItemID implements Serializable{
        @Column
        public String name;
        @OneToOne
        public Truck truck;
        
        public ItemID(){
            super();
        }
        
        public ItemID(String itemName, Truck truck){
            super();
            name = itemName;
            this.truck = truck;
        }
    }
    @EmbeddedId
    private ItemID id;
    @Column
    private float cost;
    
    public Item(){
        super();
        id = new ItemID();
        cost = 0;
    }
    
    public Item(String name, float cost, Truck truck){
        super();
        id = new ItemID(name, truck);
        this.cost = cost;
    }
    
    public String getName(){
        return id.name;
    }
    
    public void setName(String name){
        id.name = name;
    }
    
    public float getCost(){
        return cost;
    }
    
    public void setCost(float cost){
        this.cost = cost;
    }
    
    public Truck getTruck(){
        return id.truck;
    }
    
    public void setTruckId(Truck truck){
        id.truck = truck;
    }
    
    @Override
    public String toString(){
        return String.format("Item(itemName=%s, cost=%.2f, truckId=%s)", id.name, cost, id.truck.getName());
    }
}
