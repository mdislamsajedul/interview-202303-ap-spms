package com.ap.task.apsportsplayersmanagement.services;

import java.util.List;
import java.util.Optional;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;

public interface ISportsPalyersService {

	/*
	 (A-1) ORM Query for Player Retrieval
	Write an ORM query to retrieve players with a given gender, level, and age (consider these as "AND" conditions).
	 */
	public List<Player> getPlayersByGenderAndLevelAndAge(String gender, int level, int age);
	
	/*
	 (A-2) ORM Query for Sports Retrieval
	Write an ORM query to retrieve sports with multiple (≥ 2) associated players.
	 */
	public List<Sport> findSportsByPlayerAssociationCount(int count);
	
	/*
	 (A-3) ORM Query for Sports with No Players
Write an ORM query to retrieve sports with no associated players.
	 */
	public List<Sport> findSportWithNoPlayerAssociation();
	
	/*
	 (B-1) API Endpoint: Get Sports with Associated Players
	Create an API endpoint to return sports with given names (1 or more names) in JSON format. 
	Associated players should also be returned.	 
	 */
	public Optional<Sport> getSportsWithAssociatedPlayers(String name);
	
	/*
	 (B-2) API Endpoint: Get Players with No Sports
Create an API endpoint to return players with no associated sports.
	 */
	public List<Player> getPlayersWithNoSports();
	
	/*
	 (B-3) API Endpoint: Update Player Sports
Create an API endpoint to update a player's associated sports.
	 */
	public Sport updatePlayerSports(Sport sport);
	public Sport savePlayerSports(Sport sport) throws Exception ;
	
	/*
	 (B-4) API Endpoint: Delete Sports and Associated Data
Create an API endpoint to delete sports and their associated data.
	 */
	public String deleteSportsAndAssociatedPlayersData(String sport);
	
	/*
	 (B-5) API Endpoint: Paginated Player List with Sports Filter
Implement an API endpoint to provide a paginated player list with sports filtering. Pagination should be by 10 players per page.
	 */
	public List<Sport> getPaginatedPlayerListwithSportsFilter(Sport sport);

	//List<Sport> getSportsWithAssociatedPlayers();
}
