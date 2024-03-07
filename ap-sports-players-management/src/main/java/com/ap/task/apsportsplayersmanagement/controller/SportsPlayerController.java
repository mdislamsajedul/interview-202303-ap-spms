package com.ap.task.apsportsplayersmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;
import com.ap.task.apsportsplayersmanagement.services.ISportsPalyersService;

@RestController
@RequestMapping("/")
public class SportsPlayerController {
	@Autowired
	ISportsPalyersService sportsPalyersService;
	
	@GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("SportsPlayersController");
    }
	
	@GetMapping("/sports")
    public ResponseEntity<Map<String, Object>> getAllSportsAndAssociatedPlayers(@RequestParam("name") String name) {
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	
    	Optional<String> sportsName = Optional.ofNullable(name);
    	if(sportsName.isPresent()) {
    		Optional<Sport> sport = sportsPalyersService.getSportsWithAssociatedPlayers(sportsName.get());
    		if(sport.isPresent()) {
    			responseMap.put("status", "success");
    			responseMap.put("result", sport.get());
    		}else {
    			responseMap.put("status", "success");
        		responseMap.put("message", "No Sport found named "+sportsName.get()+"!");
    		}
    		
    	}else {
    		responseMap.put("status", "error");
    		responseMap.put("message", "Please check the Request Param!");
    	}
    	
    	
        return ResponseEntity.ok(responseMap);
    }
	@GetMapping("/players/no-sports")
    public ResponseEntity<Map<String, Object>> getPlayersWithNoSports() {
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	
    	List<Player> players = sportsPalyersService.getPlayersWithNoSports();
    	
    	Optional<List<Player>> playersOptional = Optional.ofNullable(players);
		if(playersOptional.isPresent()) {
			responseMap.put("status", "success");
			responseMap.put("result", playersOptional.get());
		}else {
			responseMap.put("status", "success");
			responseMap.put("result", playersOptional.get());
		}
 
    	
    	
        return ResponseEntity.ok(responseMap);
    }
	/*
	@PostMapping(value="/save-player-sports", consumes = "application/json;charset=UTF-8")
	public ResponseEntity<Map<String, Object>> savePlayerSport(@RequestBody Sport sport) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Optional<Sport> sportsToUpdate = Optional.ofNullable(sport);
    	if(sportsToUpdate.isPresent()) {
    		//sportsToUpdate.get()responseMap.s
    		//sportsPalyersService.updatePlayerSports(sport);
    		sport = sportsPalyersService.savePlayerSports(sport);
    		responseMap.put("status", "success");
    		responseMap.put("result", sport);
    	}else {
    		responseMap.put("status", "error");
    		responseMap.put("message", "Please check the Request Payload!");
    	}
		
    	
		return ResponseEntity.ok(responseMap);
	}
	*/
	@PutMapping(value="/save-player-sports", consumes = "application/json;charset=UTF-8")
	public ResponseEntity<Map<String, Object>> updatePlayerSport(@RequestBody Sport sport) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Optional<Sport> sportsToUpdate = Optional.ofNullable(sport);
    	if(sportsToUpdate.isPresent()) {
    		sportsPalyersService.updatePlayerSports(sport);
    		responseMap.put("status", "success");
    		responseMap.put("result", sport);
    	}else {
    		responseMap.put("status", "error");
    		responseMap.put("message", "Please check the Request Payload!");
    	}
		
    	
		return ResponseEntity.ok(responseMap);
	}
	
	@DeleteMapping("/sports")
    public ResponseEntity<Map<String, Object>> deleteSportsAndAssociatedPlayers(@RequestParam("name") String name) {
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	
    	Optional<String> sportsName = Optional.ofNullable(name);
    	if(sportsName.isPresent()) {
    		sportsPalyersService.deleteSportsAndAssociatedPlayersData(sportsName.get());
    		
    		responseMap.put("status", "success");
    		responseMap.put("result", "Sport And Assiociation deleted!");
    		
    		
    	}else {
    		responseMap.put("status", "error");
    		responseMap.put("message", "Please check the Request Param!");
    	}
    	
    	
        return ResponseEntity.ok(responseMap);
    }
	

}
