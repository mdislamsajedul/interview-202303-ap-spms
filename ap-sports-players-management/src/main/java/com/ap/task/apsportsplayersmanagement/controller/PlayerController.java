package com.ap.task.apsportsplayersmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.reposority.PlayersRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {
	@Autowired
	PlayersRepository pr; 
	
	@GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("SportsPlayersController");
    }
	
	@GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
    	
    	//List<Player> playerList = pr.findAll();
    	
    	List<Player> playerList = pr.findPlayersWithNoSportAssociation();
    	
    	playerList.forEach(System.out::println);
    	
        return ResponseEntity.ok(playerList);
    }
	
	@GetMapping("/players/filter")
    public ResponseEntity<List<Player>> getFilteredPlayers() {
    	
    	//List<Player> playerList = pr.findByGender("male");
		//List<Player> playerList = pr.findByGenderAndLevel("male",2);
		List<Player> playerList = pr.findByGenderAndLevelAndAge("male",2,34);
    	
    	playerList.forEach(System.out::println);
    	
        return ResponseEntity.ok(playerList);
    }
	

}
