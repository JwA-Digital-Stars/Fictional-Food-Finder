package com.example.service;

import com.example.model.Customer;
import com.example.model.Truck;
import com.example.model.TruckOwner;
import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.*;//List
import org.springframework.beans.factory.annotation.Autowired;

public class UserService{
    
    @Autowired
    private UserRepository userRepo;
    
    public User create(String email, String password, String name, String type){
        if (type.equalsIgnoreCase("customer"))
            return userRepo.save(new Customer(email, password, name));
        else if (type.equalsIgnoreCase("truck owner"))
            return userRepo.save(new TruckOwner(email, password, name));
        else
            return null;
    }
    
    public List<User> getAll(){
        return userRepo.findAll();
    }
    
    
    public User getUser(String email, String type){
        User temp = null;
        switch (type){
            case "customer":
                temp = new Customer(email);
                break;
            case "owner":
                temp = new TruckOwner(email);
                break;
            default:
                break;
        }
        
        if (temp == null)
            return null;
        
        Optional<User> userOp = userRepo.findById(temp.getUserID());
        
        if (userOp.isEmpty())
            return null;
        
        return userOp.get();
    }
    
    public User getCustomer(String email){
        List<User> users = getAll();
        
        for (User u : users){
            if (u.getEmail().equals(email) && u.getType().equals("customer"))
                return u;
        }
        return null;
    }
    
    public User getTruckOwner(String email){
        List<User> users = getAll();
        
        for (User u : users){
            if (u.getEmail().equals(email) && u.getType().equals("owner"))
                return u;
        }
        return null;
    }
    
    public boolean addFavorite(Customer customer, Truck truck){
        if (customer.getFavorites().contains(truck))
            return false;
            
        customer.getFavorites().add(truck);
        
        userRepo.save(customer);
        return true;
    }
    
    public boolean removeFavorite(Customer customer, Truck truck){        
        if (!customer.getFavorites().contains(truck))
            return false;
            
        customer.getFavorites().remove(truck);
        
        userRepo.save(customer);
        return true;
    }
    
    public Truck addTruck(TruckOwner owner, Truck truck){
        owner.setTruck(truck);
        userRepo.save(owner);
        return truck;
    }
    
    public void delete(User user){
        userRepo.delete(user);
    }
}
