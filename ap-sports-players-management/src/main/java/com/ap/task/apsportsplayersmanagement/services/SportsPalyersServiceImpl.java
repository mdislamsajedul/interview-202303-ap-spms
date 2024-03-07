package com.ap.task.apsportsplayersmanagement.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;
import com.ap.task.apsportsplayersmanagement.reposority.PlayersRepository;
import com.ap.task.apsportsplayersmanagement.reposority.SportsPlayersRepository;
import com.ap.task.apsportsplayersmanagement.reposority.SportsRepository;

@Service
public class SportsPalyersServiceImpl implements ISportsPalyersService {
	@Autowired
	SportsRepository sportsRepository;
	@Autowired
	PlayersRepository playersRepository;
	@Autowired
	SportsPlayersRepository sportsPlayersRepository;
	
	List<String> exceptionLog = new ArrayList<String>();

	@Override
	public List<Player> getPlayersByGenderAndLevelAndAge(String gender, int level, int age) {
		return playersRepository.findByGenderAndLevelAndAge(gender, level, age);
	}

	@Override
	public List<Sport> findSportsByPlayerAssociationCount(int count) {
		Optional<Integer> countOptional = Optional.ofNullable(count);
		return sportsRepository.findByPlayerAssociationCount(countOptional.orElse(0));
	}

	@Override
	public List<Sport> findSportWithNoPlayerAssociation() {
		return sportsRepository.findSportsWithNoPlayerAssociation();
	}

	/*
	 * @Override public List<Sport> getSportsWithAssociatedPlayers() { return
	 * sportsRepository.findAll(); }
	 */
	
	@Override
	public Optional<Sport> getSportsWithAssociatedPlayers(String name) {
		return sportsRepository.findByName(name);
		
	}

	@Override
	public List<Player> getPlayersWithNoSports() {
		return playersRepository.findPlayersWithNoSportAssociation();
	}

	@Override
	public Sport updatePlayerSports(Sport sport) {
		sportsRepository.save(sport);
		return sport;
	}
	
	@Override
	public Sport savePlayerSports(Sport sport) throws Exception {
		Optional<List<Player>> players = Optional.empty();
		Optional<Sport> sportToSave = Optional.empty(); 
		if(this.isAValidSport.test(sport)) {
			if(this.isANewSport.test(sport)) {
				sportToSave = Optional.ofNullable(sportsRepository.save(new Sport(sport.getName())));
			}else {
				sportToSave = Optional.ofNullable(sportsRepository.save(sport));
			}
			players = Optional.ofNullable(sport.getPlayers().stream().filter(isAValidPlayer).map(savePlayer).collect(Collectors.toList()));
			if(players.isPresent()) {
				sportToSave.get().setPlayers(players.get());
			}
			
		}else {
			String log = this.exceptionLog.stream().map(Object::toString)
		            .collect(Collectors.joining(", "));
			throw new Exception(log);
		}
		if(players.isPresent()) {
			sportToSave.get().setPlayers(players.get());
			return sportsRepository.save(sportToSave.get());
		}else {
			throw new Exception("Failed to Save! "+this.exceptionLog.stream().map(Object::toString)
		            .collect(Collectors.joining(", ")));
		}
		
	}
	
	Function<Player, Player> savePlayer = (player)->{
		if(!this.isANewPlayer.test(player)) {
			return playersRepository.save(player);
		}else {
			Player p = new Player(player.getEmail(),player.getLevel(),player.getAge(),player.getGender());
			return playersRepository.save(player);
		}
	};
	Function<Sport, Sport> saveSport = (sport)->{
		Optional<Long> checkId = Optional.ofNullable(sport.getId());
		if(checkId.isPresent() && checkId.get()!=0) {
			return sportsRepository.save(sport);
		}else {
			return new Sport();
		}
	};
	
	Consumer<String> addToLog = (log)->{
		this.exceptionLog.add(log);
	};
	
	Predicate<Sport> isAValidSport = (sport)->{
		Optional<String>  nameOpt = Optional.ofNullable(sport.getName());
		
		boolean status=true;
		
		if(nameOpt.isPresent() && nameOpt.get().length()>0) {
			status = status && true;
		}else {
			status = status && false;
			this.addToLog.accept("Not a valid Sport: Name cannot be null or empty");
		}
		
		return status;
	};
	
	Predicate<Player> isAValidPlayer = (player)->{
		Optional<Integer> levelOpt = Optional.ofNullable(player.getLevel());
		Optional<Integer> ageOpt = Optional.ofNullable(player.getAge());
		Optional<String>  emailOpt = Optional.ofNullable(player.getEmail());
		Optional<String>  genderOpt = Optional.ofNullable(player.getGender());
		
		boolean status=true;
		
		if(levelOpt.isPresent() && levelOpt.get()!=0) {
			status = status && true;
		}else {
			status = status && false;
			this.addToLog.accept("Not a valid Level: 1 ~ 10 / not null");
		}
		if(ageOpt.isPresent() && ageOpt.get()!=0) {
			status = status && true;
		}else {
			status = status && false;
			this.addToLog.accept("Not a valid Age: not null or 0");
		}
		if(emailOpt.isPresent() && emailOpt.get().length()>0) {
			status = status && true;
		}else {
			status = status && false;
			this.addToLog.accept("Email Not Provided: not null");
		}
		if(genderOpt.isPresent() 
				&& Arrays.asList(new String[]{"male", "female"}).contains(genderOpt.get())
				) {
			status = status && true;
		}else {
			status = status && false;
			this.addToLog.accept("Not a Valid Gender: Only \"male\" or \"female\"");
		}
		
		return status;
	};
	
	Predicate<Sport> isANewSport = (sport)->{
		Optional<Long> idCheck = Optional.ofNullable(sport.getId());
		if(idCheck.isPresent() && idCheck.get()!=0) {
			return false;
		}else {
			return true;
		}
	};
	Predicate<Player> isANewPlayer = (player)->{
		Optional<Long> idCheck = Optional.ofNullable(player.getId());
		if(idCheck.isPresent() && idCheck.get()!=0) {
			return false;
		}else {
			return true;
		}
	};

	@Override
	public String deleteSportsAndAssociatedPlayersData(String sportName) {
		Optional<Sport> sportToDelete = sportsRepository.findByName(sportName);
		if(sportToDelete.isPresent()) {
			sportsPlayersRepository.deleteSportsPlayersRelationBySportId(sportToDelete.get().getId());
			sportsRepository.delete(sportToDelete.get());
			return "Sport "+sportToDelete.get().getName()+" Delete Successfull! ";
		}else {
			return "No Sport found named: "+sportName+"! ";
		}
	}

	@Override
	public List<Sport> getPaginatedPlayerListwithSportsFilter(Sport sport) {
		// TODO Auto-generated method stub
		return null;
	}

}
