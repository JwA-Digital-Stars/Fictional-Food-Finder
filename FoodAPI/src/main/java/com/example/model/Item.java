package com.example.model;

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
    }
    @EmbeddedId
    private ItemID id;
    @Column
    private float cost;
    
    public Item(){
        super();
    }
    
    public Item(String name, float cost){
        super();
        id.name = name;
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
