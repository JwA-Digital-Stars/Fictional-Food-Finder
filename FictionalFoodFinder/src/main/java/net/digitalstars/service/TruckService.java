package net.digitalstars.service;

import java.util.List;
import java.util.Optional;
import net.digitalstars.model.Item;
import net.digitalstars.model.Owner;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("truckService")
public class TruckService {
    private ItemService itemService;
    private final TruckRepository truckRepository;
    private Truck currentTruck;
    
    @Autowired
    public TruckService(TruckRepository truckRepository){
        super();
        this.truckRepository = truckRepository;
    }

    public Truck getCurrentTruck() {
        return currentTruck;
    }

    public void setCurrentTruck(Truck currentTruck) {
        this.currentTruck = currentTruck;
    }
        
    public Truck create(Truck truck){
        List<Truck> trucks = findAll();
        if (!trucks.isEmpty())
            for (Truck t : trucks)
                if (t.getName().equals(truck.getName()))
                    return null;
        truckRepository.save(truck);
        return truck;
    }
    
    public Truck save(Truck truck){
        return truckRepository.save(truck);
    }
    
    public Truck findById(String name){
        Optional<Truck> truckOp = truckRepository.findById(name);
        
        if (truckOp.isEmpty())
            return null;
        
        return truckOp.get();
    }
    
    public Truck findByOwner(Owner owner_email){
        return truckRepository.findByOwner(owner_email).orElse(null);
    }
    
    public List<Truck> findAll(){
        return truckRepository.findAll();
    }
    
    public void delete(Truck truck){
        truckRepository.delete(truck);
    }
    
//    public List<Item> getMenu(Truck truck){
//        return itemService.findByTruckName(truck);
//    }
        
}//TruckService
