ALTER TABLE `binding` 				ADD CONSTRAINT `binding_ibfk_1` 			FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `binding` 				ADD CONSTRAINT `binding_ibfk_2` 			FOREIGN KEY (`bindingTypeFK`) 		REFERENCES `binding_type` (`id`) 		ON DELETE CASCADE;
ALTER TABLE `metric` 				ADD CONSTRAINT `metric_ibfk_1` 				FOREIGN KEY (`commandFK`) 			REFERENCES `command` (`id`) 			ON DELETE CASCADE;
ALTER TABLE `metric` 				ADD CONSTRAINT `metric_ibfk_2` 				FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `metric` 				ADD CONSTRAINT `metric_ibfk_3` 				FOREIGN KEY (`userFK`) 				REFERENCES `user` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `default_role` 			ADD CONSTRAINT `default_role_ibfk_1` 		FOREIGN KEY (`roleFK`) 				REFERENCES `role` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `default_role` 			ADD CONSTRAINT `default_role_ibfk_2` 		FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `guild_setting` 		ADD CONSTRAINT `guild_setting_ibfk_1` 		FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `module_settings` 		ADD CONSTRAINT `module_settings_ibfk_1`		FOREIGN KEY (`commandFK`)		 	REFERENCES `command` (`id`) 			ON DELETE CASCADE;
ALTER TABLE `module_settings` 		ADD CONSTRAINT `module_settings_ibfk_2` 	FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `module_settings`		ADD CONSTRAINT `module_settings_ibfk_3` 	FOREIGN KEY (`roleFK`) 				REFERENCES `role` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `owner` 				ADD CONSTRAINT `owner_ibfk_1` 				FOREIGN KEY (`id`) 					REFERENCES `user` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `role` 					ADD CONSTRAINT `role_ibfk_1` 				FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `role` 					ADD CONSTRAINT `role_ibfk_2`				FOREIGN KEY (`parent`) 				REFERENCES `role` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `role_member` 			ADD CONSTRAINT `role_member_ibfk_1` 		FOREIGN KEY (`roleFK`) 				REFERENCES `role` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `role_member` 			ADD CONSTRAINT `role_member_ibfk_2`			FOREIGN KEY (`snowflakeTypeFK`) 	REFERENCES `snowflake_type` (`id`) 		ON DELETE CASCADE;
ALTER TABLE `alias` 				ADD CONSTRAINT `alias_ibfk_1` 				FOREIGN KEY (`commandFK`) 			REFERENCES `command` (`id`) 			ON DELETE CASCADE;
ALTER TABLE `alias`					ADD CONSTRAINT `alias_ibfk_2` 				FOREIGN KEY (`guildFK`) 			REFERENCES `guild` (`id`) 				ON DELETE CASCADE;
ALTER TABLE `command`				ADD CONSTRAINT `command_ibfk_1`				FOREIGN KEY (`description`)			REFERENCES `localisation` (`ìd`)		ON DELETE CASCADE;
ALTER TABLE `binding_type`			ADD CONSTRAINT `binding_type_ibfk_1`		FOREIGN KEY (`localisationFK`) 		REFERENCES `localisation` (`id`) 		ON DELETE CASCADE;
