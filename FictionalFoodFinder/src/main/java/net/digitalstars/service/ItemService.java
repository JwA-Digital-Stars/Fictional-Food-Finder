package net.digitalstars.service;

import com.digitalstars.model.Item;
import com.digitalstars.model.Item.ItemID;
import com.digitalstars.model.Truck;
import com.digitalstars.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepo;
    
    public Item create(String itemName, float cost, Truck truck) {
        return itemRepo.save(new Item(itemName, cost, truck));
    }
    
    public List<Item> getItems(){
        return itemRepo.findAll();
    }
    
    public List<Item> getItems(Truck truck){
        List<Item> items = itemRepo.findAll();
        
        items.stream().filter(i -> (i.getTruck() != truck)).forEachOrdered(i -> {
            items.remove(i);
        });
        
        return items;
    }
    
    public Item getItem(ItemID id){
        Optional<Item> itemOp = itemRepo.findById(id);
        return itemOp.orElse(null);
    }
    
    public void delete(Item item){
        itemRepo.delete(item);
    }
    
    public Item updateCost(Item item, float newCost){
        item.setCost(newCost);
        itemRepo.save(item);
        return item;
    }

}//ItemService
