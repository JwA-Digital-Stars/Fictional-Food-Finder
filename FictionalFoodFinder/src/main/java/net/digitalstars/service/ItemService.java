package net.digitalstars.service;

import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {
    private final ItemRepository itemRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository){
        super();
        this.itemRepository = itemRepository;
    }
    
    public Item create(Item item){
        List<Item> items = findAll();
        for (Item i : items){
            if (i.getName().equals(item.getName()) && i.getTruck().equals(item.getTruck()))
                return null;
        }
        itemRepository.save(item);
        return item;
    }
    
    public Item save(Item item) {
        return itemRepository.save(item);
    }
    
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    
    public List<Item> getItems(Truck truck){
        List<Item> items = itemRepository.findAll();
        
        items.stream().filter(i -> (i.getTruck() != truck)).forEachOrdered(i -> {
            items.remove(i);
        });
        
        return items;
    }
    
    public Item findById(int id){
        Optional<Item> itemOp = itemRepository.findById(id);
        return itemOp.orElse(null);
    }
    
//    public List<Item> findByTruckName(Truck truck){
//        return itemRepository.findByTruckName(truck.getName());
//    }
    
    public void delete(Item item){
        itemRepository.delete(item);
    }
    
    public Item updateCost(Item item, float newCost){
        item.setCost(newCost);
        itemRepository.save(item);
        return item;
    }

}//ItemService
