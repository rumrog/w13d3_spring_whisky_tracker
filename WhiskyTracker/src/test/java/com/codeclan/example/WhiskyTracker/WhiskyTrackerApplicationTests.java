package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}


	@Test
	public void createWhiskyAndDistillery(){
		Distillery distillery1 = new Distillery("Glendronach", "Highland");
		distilleryRepository.save(distillery1);


		Whisky whisky1 = new Whisky("The Glendronach Revival", 15, 2018, distillery1);
		whiskyRepository.save(whisky1);
	}

	@Test
	public void canFindWhiskiesByYear() {
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(2018);
		assertEquals(6, foundWhiskies.size());
	}

	@Test
	public void canFindDistilleryByRegion() {
		List<Distillery> foundDistilleries = distilleryRepository.findByRegion("Lowland");
		assertEquals(2, foundDistilleries.size());
	}

	@Test
	public void canFindWhiskiesByDistilleryAndAge() {
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryIdAndAge(1L, 15);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canFindWhiskiesByRegion() {
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion("Island");
		assertEquals(6, foundWhiskies.size());
	}
}
