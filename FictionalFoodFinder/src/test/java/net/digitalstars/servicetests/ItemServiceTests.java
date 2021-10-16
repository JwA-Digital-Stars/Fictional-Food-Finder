package net.digitalstars.servicetests;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.digitalstars.model.Item;
import net.digitalstars.model.Owner;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.ItemRepository;
import net.digitalstars.service.ItemService;

public class ItemServiceTests {
	
	@InjectMocks
	private ItemService itemService;
	
	@Mock
	private ItemRepository itemRepository;
	
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void setUp() {
		itemService = new ItemService(itemRepository);
	}
	
	@Test
	public void getAllItems() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck truck1 = new Truck("Uncle Danes HotDog Truck",UncleDane);
		Truck truck2 = new Truck("Uncle Danes Taco Truck",UncleDane);
		
		Item item1 = new Item(1,"Hotdog",2, truck1);
		Item item2 = new Item(2,"Tacos",4,truck2);
		
		Mockito.when(itemRepository.findAll()).thenReturn(Arrays.asList(item1,item2));
		
		List<Item> listOfItems = itemService.findAll();
		assertEquals(2, listOfItems.size());
	}
	
	@Test
	public void getItemById() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck truck1 = new Truck("Uncle Danes HotDog Truck",UncleDane);
		Truck truck2 = new Truck("Uncle Danes Taco Truck",UncleDane);
		
		Item hotdog = new Item(1,"Hotdog",2, truck1);
		Optional<Item> optionalHotdog = Optional.of(hotdog); 
		
		Item taco = new Item(2,"Tacos",4,truck2);
		Optional<Item> optionalTaco = Optional.of(taco); 
		
		Mockito.when(itemRepository.findById(hotdog.getId())).thenReturn(optionalHotdog);
		Mockito.when(itemRepository.findById(taco.getId())).thenReturn(optionalTaco);
		
		assertEquals(hotdog, itemService.findById(hotdog.getId()));
		assertEquals(taco, itemService.findById(taco.getId()));
	}
	
	@Test
	public void FalseGetItemById() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck truck1 = new Truck("Uncle Danes HotDog Truck",UncleDane);
		Truck truck2 = new Truck("Uncle Danes Taco Truck",UncleDane);
		
		Item hotdog = new Item(1,"Hotdog",2, truck1);
		Optional<Item> optionalHotdog = Optional.of(hotdog); 
		
		Item taco = new Item(2,"Tacos",4,truck2);
		Optional<Item> optionalTaco = Optional.of(taco); 
		
		Mockito.when(itemRepository.findById(hotdog.getId())).thenReturn(optionalHotdog);
		Mockito.when(itemRepository.findById(taco.getId())).thenReturn(optionalTaco);
		
		assertNotEquals(taco, itemService.findById(3));
		assertNotEquals(hotdog, itemService.findById(3));
	}
}
