package net.digitalstars.model;

import java.io.Serializable;
import java.util.*;//List, ArrayList
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Entity @Table(name="truck")
public class Truck implements Serializable{

    @Id @Column 
    private String name;
    @Column
    private String hours;
    @OneToOne
    private Owner owner;
    //private Location location;
    
    public Truck(String name, Owner owner){
        super();
        this.name = name;
        this.owner = owner;
        hours = populateHours();
        //location = new Location();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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