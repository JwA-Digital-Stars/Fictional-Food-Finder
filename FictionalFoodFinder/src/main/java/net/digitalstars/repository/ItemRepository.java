package net.digitalstars.repository;

import java.util.List;
import net.digitalstars.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long>{
    List<Item> findByTruckName(String truck_name);
    
}//ItemRepository
