CREATE TABLE `guild_setting` (
  `id` bigint(11) PRIMARY KEY AUTO_INCREMENT,
  `guildFK` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
