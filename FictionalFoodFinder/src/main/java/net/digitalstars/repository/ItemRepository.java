package net.digitalstars.repository;

import net.digitalstars.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
}//ItemRepository
