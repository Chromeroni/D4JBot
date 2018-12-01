package hera.events.commands;

import hera.enums.BoundChannel;
import hera.events.eventSupplements.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Report extends Command {

    private static final Logger LOG = LoggerFactory.getLogger(Report.class);

    private MessageSender ms;

    // constructor
    Report() {
        super(null, 2, true);
        this.ms = MessageSender.getInstance();
    }

    @Override
    protected void commandBody(String[] params, MessageReceivedEvent e) {
        LOG.debug("Start of: Report.execute");

        String reportedUser = params[0];
        String message = params[1];

        ms.sendMessage(BoundChannel.REPORT.getBoundChannel(), "", "Reporter:	 " + e.getAuthor().mention()
                + "\nRecipient:	" + reportedUser + "\n\nReport message:\n" + message);
        ms.sendMessage(e.getChannel(), "", "Reported " + reportedUser);
        LOG.info(e.getAuthor() + " reported user " + reportedUser + " with message: " + message);

        LOG.debug("End of: Report.execute");
    }
}
