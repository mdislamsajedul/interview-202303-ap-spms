package com.ap.task.apsportsplayersmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;
import com.ap.task.apsportsplayersmanagement.services.ISportsPalyersService;

@SpringBootTest
class ApSportsPlayersManagementApplicationTests {

	@Autowired
	ISportsPalyersService sportsPalyersService;
	
	@Test
	void contextLoads() {
	}
	/*
	BiPredicate<Integer,Integer> testIntValue = (in, expected)->{
		return in==expected;
		
	};
	BiPredicate<String,String> testStringValue = (in, expected)->{
		return in.equals(expected);
		
	};
	
	BiFunction<Player,Player,Boolean> testPlayer = (p1, p2)->{
		System.err.println("Chome -- "+p1);
		return testStringValue.test(p1.getGender(), p2.getGender())
		&& testIntValue.test(p1.getLevel(), p2.getLevel())
		&& testIntValue.test(p1.getAge(), p2.getAge());
		
	};
	*/
	//gender, level, and age
	@Test
	void checkFilterByGenderLevelAndAge() {
		
		String gender="male";
		int level = 2, age=34;
		Player p = new Player("", 4, 34, "male");
		List<Player> playerList = sportsPalyersService.getPlayersByGenderAndLevelAndAge(p.getGender(), p.getLevel(), p.getAge());
		//playerList.stream().map((x)->testPlayer).forEach(System.out::println);;
		
		playerList.stream().forEach((x)->{
			assertThat(x.getGender()).isEqualTo(p.getGender());
			assertThat(x.getLevel().compareTo(p.getLevel()));
			assertThat(x.getAge().compareTo(p.getAge()));
			//System.out.println(x);
			;
		});
	}
	
	//sports with multiple (≥ 2) associated players
	@Test
	void checkSportsWithMultipleAssociatedPlayers() {
		
		System.out.println("Testing: checkSportsWithMultipleAssociatedPlayers");
		int count = 1;
		List<Sport> resultSports = sportsPalyersService.findSportsByPlayerAssociationCount(count);
		
		resultSports.stream().forEach((x)->{
			assertThat(x.getPlayers().size()==count);
			System.out.println(x+" - Count: "+x.getPlayers().size()+", check count"+count);
			;
		});
	}
	//sports with multiple (≥ 2) associated players
	@Test
	void checkSportWithNoPlayerAssociation() {
		
		System.out.println("Testing: checkSportWithNoPlayerAssociation");
		List<Sport> resultSports = sportsPalyersService.findSportWithNoPlayerAssociation();
		
		resultSports.stream().forEach((x)->{
			Optional<List<Player>> list = Optional.ofNullable(x.getPlayers());
			assertThat(list.isEmpty());
			System.out.println(x+" - Count: "+x.getPlayers().size());
			;
		});
	}
}
