package net.digitalstars.service;

import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.TruckRepository;
import java.util.Optional;
import net.digitalstars.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("truckService")
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;
    
    public Truck create(String name, Owner owner){
        
        return truckRepository.save(new Truck(name, owner));
    }
    
    public Truck getTruck(String name){
        Optional<Truck> truckOp = truckRepository.findById(name);
        return truckOp.orElse(null);
    }
    
    public boolean addItem(Truck truck, Item item){        
        if (truck == null)
            return false;
        
        truck.getMenu().add(item);
        truckRepository.save(truck);
        return true;
    }
    
    public boolean removeItem(Truck truck, Item item){
        
        if (truck == null)
            return false;
        
        truck.getMenu().remove(item);
        truckRepository.save(truck);
        return true;
    }
    
    public void delete(Truck truck){
        truckRepository.delete(truck);
    }
    
}//TruckService
