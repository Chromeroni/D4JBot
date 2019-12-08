package hera.events.commands;

import hera.enums.BoundChannel;
import hera.events.eventSupplements.MessageSender;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class Leave extends AbstractCommand {

	private MessageSender ms;
	
	// constructor
	Leave() {
		super(null, 0, false);
		ms = MessageSender.getInstance();
	}

	@Override
	protected void commandBody(String[] params, MessageReceivedEvent e) {
		IVoiceChannel userChannel = e.getAuthor().getVoiceStateForGuild(e.getGuild()).getChannel();
		IVoiceChannel botChannel = e.getClient().getOurUser().getVoiceStateForGuild(e.getGuild()).getChannel();

		if(userChannel != null) {
			if(botChannel != null) {
				if(userChannel.getLongID() == botChannel.getLongID()) {
					botChannel.leave();
				} else ms.sendMessage(BoundChannel.MUSIC.getBoundChannel(), "", e.getAuthor().mention() + ", you need to be in the same voice channel as me to make me leave.");
			} else ms.sendMessage(BoundChannel.MUSIC.getBoundChannel(), "", "I am currently not in a voice channel.");
		} else ms.sendMessage(BoundChannel.MUSIC.getBoundChannel(), "", e.getAuthor().mention() + ", you need to be in a voice channel to make me leave.");
	}
}