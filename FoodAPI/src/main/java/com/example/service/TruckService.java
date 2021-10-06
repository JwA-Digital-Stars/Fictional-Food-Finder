package com.example.service;

import com.example.model.Item;
import com.example.model.Truck;
import com.example.repository.TruckRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class TruckService {

    @Autowired
    private TruckRepository truckRepo;
    
    public Truck create(String email, String name){
        
        return truckRepo.save(new Truck(email, name));
    }
    
    public Truck getTruck(String name){
        Optional<Truck> truckOp = truckRepo.findById(name);
        
        if (truckOp.isEmpty())
            return null;
        
        return truckOp.get();
    }
    
    public boolean addItem(Truck truck, Item item){        
        if (truck == null)
            return false;
        
        truck.getMenu().add(item);
        truckRepo.save(truck);
        return true;
    }
    
    public boolean removeItem(Truck truck, Item item){
        
        if (truck == null)
            return false;
        
        truck.getMenu().remove(item);
        truckRepo.save(truck);
        return true;
    }
    
    public void delete(Truck truck){
        truckRepo.delete(truck);
    }
    
}//TruckService
