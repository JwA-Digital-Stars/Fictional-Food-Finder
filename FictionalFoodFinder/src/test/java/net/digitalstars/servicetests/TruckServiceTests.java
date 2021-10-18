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
import org.springframework.boot.test.context.SpringBootTest;

import net.digitalstars.model.Owner;
import net.digitalstars.model.Truck;
import net.digitalstars.repository.TruckRepository;
import net.digitalstars.service.TruckService;

@SpringBootTest
public class TruckServiceTests {
	
	@InjectMocks
	private TruckService truckService;
	
	@Mock
	private TruckRepository truckRepository;
	
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void setUp() {
		truckService = new TruckService(truckRepository);
	}
	
	@Test
	public void findAllTrucks() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck truck1 = new Truck("Uncle Danes Hotdog Truck",UncleDane);
		Truck truck2 = new Truck("Uncle Danes Taco Truck",UncleDane);
		
		Mockito.when(truckRepository.findAll()).thenReturn(Arrays.asList(truck1,truck2));
		
		List<Truck> listOfTrucks = truckService.findAll();
		assertEquals(2, listOfTrucks.size());
	}
	
	@Test
	public void findSpecificTruck() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck HotdogTruck = new Truck("Uncle Danes Hotdog Truck", UncleDane);
		Optional<Truck> OptionalHotdogTruck = Optional.of(HotdogTruck);
		Truck TacoTruck = new Truck("Uncle Danes Taco Truck", UncleDane);
		Optional<Truck> OptionalTacoTruck = Optional.of(TacoTruck);
		
		Mockito.when(truckRepository.findById(HotdogTruck.getName())).thenReturn(OptionalHotdogTruck);
		Mockito.when(truckRepository.findById(TacoTruck.getName())).thenReturn(OptionalTacoTruck);
		
		assertEquals(TacoTruck, truckService.findById(TacoTruck.getName()));
		assertEquals(HotdogTruck, truckService.findById(HotdogTruck.getName()));
		
		
	}
	
	@Test
	public void findingNonexistentTruck() {
		Owner UncleDane = new Owner("Owner1@test.net", "678" , "Uncle Dane");
		
		Truck HotdogTruck = new Truck("Uncle Danes Hotdog Truck", UncleDane);
		Optional<Truck> OptionalHotdogTruck = Optional.of(HotdogTruck);
		Truck TacoTruck = new Truck("Uncle Danes Taco Truck", UncleDane);
		Optional<Truck> OptionalTacoTruck = Optional.of(TacoTruck);
		
		Mockito.when(truckRepository.findById(HotdogTruck.getName())).thenReturn(OptionalHotdogTruck);
		Mockito.when(truckRepository.findById(TacoTruck.getName())).thenReturn(OptionalTacoTruck);
		
		assertNotEquals(TacoTruck, truckService.findById("Truck that does not exist"));
		assertNotEquals(HotdogTruck, truckService.findById("Truck that does not exist"));
		
		
	}

}
