package net.digitalstars.repository;

import java.util.List;
import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Integer>{
    List<Item> findByTruckName(String truck_name);
    
}//ItemRepository
