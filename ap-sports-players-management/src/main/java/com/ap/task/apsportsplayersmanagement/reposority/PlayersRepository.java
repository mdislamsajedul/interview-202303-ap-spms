package com.ap.task.apsportsplayersmanagement.reposority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ap.task.apsportsplayersmanagement.entity.Player;
import com.ap.task.apsportsplayersmanagement.entity.Sport;


public interface PlayersRepository extends JpaRepository<Player, Long>{

	List<Player> findByGender(String string);

	List<Player> findByGenderAndLevel(String string, int i);

	List<Player> findByGenderAndLevelAndAge(String string, int i, int j);
	
	@Query(value = ""
			+ "SELECT id, EMAIL, LEVEL, AGE, GENDER,spt.* from AP_TEST.PLAYERS_TBL pt "
			+ "left join AP_TEST.sports_players_tbl spt on (pt.id=spt.player_id) "
			+ "where spt.sport_id  is null"
			, nativeQuery = true)
	List<Player> findPlayersWithNoSportAssociation();


}
