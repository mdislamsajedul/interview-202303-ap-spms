-- AP_TEST.sports_players_tbl definition

CREATE TABLE `sports_players_tbl` (
  `player_id` bigint NOT NULL,
  `sport_id` bigint NOT NULL,
  KEY `sports_players_tbl_PLAYERS_TBL_FK` (`player_id`),
  KEY `sports_players_tbl_SPORTS_TBL_FK` (`sport_id`),
  CONSTRAINT `sports_players_tbl_PLAYERS_TBL_FK` FOREIGN KEY (`player_id`) REFERENCES `PLAYERS_TBL` (`id`),
  CONSTRAINT `sports_players_tbl_SPORTS_TBL_FK` FOREIGN KEY (`sport_id`) REFERENCES `SPORTS_TBL` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;