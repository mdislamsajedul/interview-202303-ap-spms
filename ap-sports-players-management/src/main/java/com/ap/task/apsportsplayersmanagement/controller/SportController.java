package com.ap.task.apsportsplayersmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;
import com.ap.task.apsportsplayersmanagement.reposority.PlayersRepository;
import com.ap.task.apsportsplayersmanagement.reposority.SportsPlayersRepository;
import com.ap.task.apsportsplayersmanagement.reposority.SportsRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/sports")
public class SportController {
	@Autowired
	SportsRepository sr;
	@Autowired
	SportsPlayersRepository spr;
	
	@GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("SportsPlayersController");
    }
	
	@GetMapping("/sports")
    public ResponseEntity<List<Sport>> getAllPlayers() {
    	
    	//List<Sport> sportList = sr.findAll();
    	//long count = sr.countByPlayers();
    	//System.out.println(count);
    	
    	List<Sport> sportList = sr.findByPlayerAssociationCount(2);
		//List<Sport> sportList = sr.findSportWithNoPlayerAssociation();
    	spr.deleteSportsPlayersRelationBySportId(1);
    	
    	sportList.forEach(System.out::println);
    	
        return ResponseEntity.ok(sportList);
    }
	

}
