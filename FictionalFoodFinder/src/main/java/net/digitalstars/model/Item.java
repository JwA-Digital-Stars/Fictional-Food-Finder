package net.digitalstars.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    @Transient
    public static List<Integer> ids = new ArrayList<>();

    @Autowired
    public Item(String name, float cost, Truck truck){
        super();
        id = generateId();
        this.cost = cost;
        this.name = name;
        this.truck = truck;
    }
    
    @Autowired
    public Item(String name, Truck truck){
        super();
        id = generateId();
        this.name = name;
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
    
    private int generateId(){
        int[] i = new int[3];
        StringBuilder idBuilder = new StringBuilder();
        for (int j = 0; j < 3; j++){
            i[j] = (int)(Math.random()*(900)+100);
            idBuilder.append(i[j]);
        }//for (int j = 0; j < 3; j++)
        int k = Integer.valueOf(idBuilder.toString());
        if (ids.contains(k))
            k = generateId();
        ids.add(k);
        return k;
    }//generateId()
}
