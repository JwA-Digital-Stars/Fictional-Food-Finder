package net.digitalstars.service;

import net.digitalstars.model.Item;
import net.digitalstars.model.Item.ItemId;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {
    
    @Autowired
    private TruckService truckService;
    
    private final ItemRepository itemRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository){
        super();
        this.itemRepository = itemRepository;
    }
    
    public String create(Item item){
        List<Item> items = findAll();
        for (Item i : items){
            if (i.getId() == item.getId())
                return "This item already exists";
        }
        itemRepository.save(item);
        return "Item added!";
    }
    
    public Item save(Item item) {
        return itemRepository.save(item);
    }
    
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    
    public List<Item> getItems(Truck truck){
        List<Item> items = itemRepository.findAll();
        
        items.stream().filter(i -> (i.getId().getTruck() != truck)).forEachOrdered(i -> {
            items.remove(i);
        });
        
        return items;
    }
    
    public Item findById(ItemId id){
        Optional<Item> itemOp = itemRepository.findById(id);
        return itemOp.orElse(null);
    }
    
    public Item findById(String itemName, String truckName){
        Truck truck = truckService.findById(itemName);
        if (truck == null)
            return null;
        Item item = new Item(itemName, truck);
        Optional<Item> itemOp = itemRepository.findById(item.getId());
        return itemOp.orElse(null);
    }
    
    public void delete(Item item){
        itemRepository.delete(item);
    }
    
    public Item updateCost(Item item, float newCost){
        item.setCost(newCost);
        itemRepository.save(item);
        return item;
    }

}//ItemService
