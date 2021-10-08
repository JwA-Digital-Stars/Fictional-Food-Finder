package com.digitalstars.model;

public class TruckOwner extends User{
    private Truck truck;
    
    public TruckOwner(){
        super();
        type = "owner";
        truck = new Truck();
    }
    
    public TruckOwner(String email, String password, String name){
        super(email, password, name);
        truck = new Truck();
        type = "owner";
    }
    
    public Truck getTruck(){
        return truck;
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
    }
    
    @Override
    public String toString(){
        return String.format("User(email=%s, name=%s, type=owner, truck=%s)", email, name, truck.getName());
    }
}