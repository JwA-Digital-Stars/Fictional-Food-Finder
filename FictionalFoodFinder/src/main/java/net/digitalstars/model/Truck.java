package net.digitalstars.model;

import java.io.Serializable;
import java.util.*;//List, ArrayList
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Table(name="truck")
public class Truck implements Serializable{
    @Id
    @Column
    private String name;
    @Transient
    private List<Item> menu;
    @Column
    private String hours;
    @OneToOne
    private User truckOwner;
    //private Location location;
    
    public Truck(){
        super();
    }
    
    public Truck(String name, User truckOwner){
        super();
        this.name = name;
        this.truckOwner = truckOwner;
        menu = new ArrayList<>();
        hours = populateHours();
        populateHours();
        //location = new Location();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public List<Item> getMenu(){
        return menu;
    }
    
    public void setMenu(List<Item> menu){
        this.menu = menu;
    }
    
    public String getHours(){
        return hours;
    }
    
    public void setHours(String hours){
        this.hours = hours;
    }
    
    public User getOwnerId(){
        return truckOwner;
    }
    
    public void setOwnerId(User truckOwner){
        this.truckOwner = truckOwner;
    }
    
    /*
    public Location getLocation(){
        return location;
    }
    
    public void setLocation(Location location){
        this.location = location;
    }
    */
    
    public final String populateHours(){
        StringBuilder builder = new StringBuilder();
        String weekday = "9:00am - 5:00pm";
        String weekend = "12:00pm - 6:00pm";
        
        builder.append("Monday: ");
        builder.append(weekday);
        builder.append("\nTuesday: ");
        builder.append(weekday);
        builder.append("\nWednesday: ");
        builder.append(weekday);
        builder.append("\nThursday: ");
        builder.append(weekday);
        builder.append("\nFriday: ");
        builder.append(weekday);
        builder.append("\nSaturday: ");
        builder.append(weekend);
        builder.append("\nSunday: ");
        builder.append(weekend);
        
        return builder.toString();
        
    }
}