package net.digitalstars.model;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="favorites")
public class Favorite {
    @OneToOne
    private User customer;
    @OneToOne
    private Truck truck;
    
    public Favorite(){
        super();
    }
    
    public Favorite(Customer customer, Truck truck){
        super();
        this.customer = customer;
        this.truck = truck;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
    
}//Favorite
