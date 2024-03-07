package com.ap.task.apsportsplayersmanagement.reposority;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ap.task.apsportsplayersmanagement.entity.Sport;

public interface SportsRepository extends JpaRepository<Sport, Long> {

	@Query(value = ""
			+ "SELECT distinct ID, NAME from AP_TEST.SPORTS_TBL st "
			+ "join AP_TEST.sports_players_tbl spt on (st.id=spt.sport_id) "
			+ "join (select sport_id, count(player_id) c from AP_TEST.sports_players_tbl group by sport_id HAVING c>=?1) s on (s.sport_id=spt.sport_id)", nativeQuery = true)
	List<Sport> findByPlayerAssociationCount(int count);
	
	@Query(value = ""
			+ "SELECT distinct ID, NAME from AP_TEST.SPORTS_TBL st "
			+ "left join AP_TEST.sports_players_tbl spt on (st.id=spt.sport_id) "
			+ "where spt.player_id is null"
			, nativeQuery = true)
	List<Sport> findSportsWithNoPlayerAssociation();

	Optional<Sport> findByName(String name);

}
