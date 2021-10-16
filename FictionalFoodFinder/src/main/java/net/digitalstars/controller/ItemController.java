package net.digitalstars.controller;

import net.digitalstars.model.Item;
import net.digitalstars.service.ItemService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("itemController") @RequestMapping("/item")
public class ItemController {
    
    private final ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService){
        super();
        this.itemService = itemService;
    }

    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Item item){
        itemService.save(item);
        return item.toString();
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Item> findAll(){
        return itemService.findAll();
    }
    
    @GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Item getItem(@PathParam("id") int id){
        Item item = itemService.findById(id);
        
        return item;
    }
    
    @PostMapping(path="/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Item item){        
        itemService.delete(item);
    }
}//ItemController
