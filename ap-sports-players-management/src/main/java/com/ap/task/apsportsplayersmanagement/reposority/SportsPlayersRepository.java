package com.ap.task.apsportsplayersmanagement.reposority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ap.task.apsportsplayersmanagement.entity.Sport;

public interface SportsPlayersRepository extends JpaRepository<Sport, Long> {
	@Modifying
	@Transactional
	@Query(value = ""
			+ "delete From sports_players_tbl where sport_id=?1"
			, nativeQuery = true)
	int deleteSportsPlayersRelationBySportId(long id);

	

}
