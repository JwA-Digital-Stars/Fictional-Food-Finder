package com.example.model;

public class TruckOwner extends User{
    private Truck truck;
    
    public TruckOwner(){
        super();
        id.type = "owner";
        truck = new Truck();
    }
    
    public TruckOwner(String email){
        super(email);
        id.type = "owner";
        truck = new Truck();
    }
    
    public TruckOwner(String email, String password, String name){
        super(email, password, name);
        truck = new Truck();
        id.type = "owner";
    }
    
    public Truck getTruck(){
        return truck;
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
    }
    
    @Override
    public String toString(){
        return String.format("User(email=%s, name=%s, type=owner, truck=%s)", id.email, name, truck.getName());
    }
}