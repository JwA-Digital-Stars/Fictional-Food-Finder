package com.digitalstars.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item implements Serializable{
    @Embeddable
    public class ItemID{
        @Column
        public String name;
        @Column
        public String truckId;
        
        public ItemID(){
            super();
            name = "Item name";
            truckId = "Truck name";
        }
        
        public ItemID(String itemName, String truckName){
            super();
            name = itemName;
            truckId = truckName;
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
    
    public Item(String name, float cost, String truckId){
        super();
        id = new ItemID(name, truckId);
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
    
    public String getTruckId(){
        return id.truckId;
    }
    
    public void setTruckId(String truckName){
        id.truckId = truckName;
    }
    
    @Override
    public String toString(){
        return String.format("Item(itemName=%s, cost=%.2f, truckId=%s)", id.name, cost, id.truckId);
    }
}
