package d4jbot.events;

import java.util.Arrays;
import java.util.List;

import d4jbot.misc.MessageOfTheDayManager;
import d4jbot.misc.MessageSender;
import d4jbots.enums.BotPrefix;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

public class Motd {

	private MessageSender ms;
	private MessageOfTheDayManager motdm;
	
	// default constructor
	public Motd() { }
	
	// constructor
	public Motd(MessageSender ms, MessageOfTheDayManager motdm) {
		this.ms = ms;
		this.motdm = motdm;
	}
		
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent e) {
		if(e.getMessage().getContent().startsWith(BotPrefix.BOT_PREFIX.getBotPrefix() + "motd")) {
			if(e.getAuthor().getPermissionsForGuild(e.getGuild()).contains(Permissions.ADMINISTRATOR)) {
				List<String> list = Arrays.asList(e.getMessage().getContent().split(" "));
				
				if(list.size() > 1) {
					String motd = "";
					for(int i = 1; i < list.size(); i++) {
						motd += list.get(i);
					}
					
					motdm.setMessageOfTheDay(e, motd);
					
				} else ms.sendMessage(e.getChannel(), true, "Invalid usage of $motd.\nSyntax: $motd <messageOfTheDay>");
			} else ms.sendMessage(e.getChannel(), true, "You need to be an Administrator of this server to use this command.");
		}
	}
}
