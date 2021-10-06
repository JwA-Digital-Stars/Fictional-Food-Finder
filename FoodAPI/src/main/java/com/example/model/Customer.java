package com.example.model;

import java.util.*;//List, ArrayList

public class Customer extends User{
    private List<Truck> favorites;
    public Customer(){
        super();
        id.type = "customer";
        favorites = new ArrayList<>();
    }
    
    public Customer(String email){
        super(email);
        id.type = "customer";
        favorites = new ArrayList<>();
    }
    
    public Customer(String email, String password, String name){
        super(email, password, name);
        id.type = "customer";
        favorites = new ArrayList<>();
    }
    
    public List<Truck> getFavorites(){
        return favorites;
    }
    
    public void setFavorites(List<Truck> favorites){
        this.favorites = favorites;
    }
    
    @Override
    public String toString(){
        return String.format("User(email=%s, name=%s, type=customer)", id.email, name);
    }
}