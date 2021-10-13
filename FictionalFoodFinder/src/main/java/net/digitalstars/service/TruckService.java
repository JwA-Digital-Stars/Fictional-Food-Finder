package net.digitalstars.service;

import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("truckService")
public class TruckService {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private ItemService itemService;
    private final TruckRepository truckRepository;
    
    @Autowired
    public TruckService(TruckRepository truckRepository){
        super();
        this.truckRepository = truckRepository;
    }
    
    
    public Truck save(Truck truck){
        return truckRepository.save(truck);
    }
    
    public Truck findById(String name){
        return truckRepository.findById(name).orElse(null);
    }
    
    public boolean addItem(Truck truck, Item item){        
        if (truck == null)
            return false;
        
        if (item.getId().getTruck() != truck)
            return false;
        
        itemService.save(item);
        truck.getMenu().add(item);
        truckRepository.save(truck);
        return true;
    }
    
    public boolean removeItem(Truck truck, String itemName){
        
        if (truck == null)
            return false;
        
        Item item = new Item(itemName, truck);
        item = itemService.findById(item.getId());
        
        if (item == null)
            return false;
        
        truck.getMenu().remove(item);
        itemService.delete(item);
        truckRepository.save(truck);
        return true;
    }
    
    public void delete(Truck truck){
        truckRepository.delete(truck);
    }
    
    public String menu(Truck truck){
        StringBuilder menuBuilder = new StringBuilder();
        
        truck.getMenu().stream().map(i -> {
            menuBuilder.append(i.menuFormat());
            return i;
        }).forEachOrdered(_item -> {
            menuBuilder.append("\n");
        });
        
        return menuBuilder.toString();
    }
    
}//TruckService
