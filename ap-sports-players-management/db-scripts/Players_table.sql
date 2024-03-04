-- AP_TEST.PLAYERS_TBL definition

CREATE TABLE `PLAYERS_TBL` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(100) NOT NULL,
  `LEVEL` int NOT NULL COMMENT 'allows 1-10',
  `AGE` int NOT NULL,
  `GENDER` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PLAYERS_TBL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;