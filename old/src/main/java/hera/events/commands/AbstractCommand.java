package hera.events.commands;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hera.events.eventSupplements.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

abstract public class AbstractCommand {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);
	private static final Map<String, AbstractCommand> instances = new HashMap<>();

	public static AbstractCommand getInstance(String className) {
		if (!instances.containsKey(className)) {
			try {
				instances.put(className, (AbstractCommand) Class.forName(className).newInstance());
			} catch (Exception e) {
				LOG.error("Creating instance of " + className + " failed");
				LOG.error(e.getMessage() + " : " + e.getCause());
			}
		}
		return instances.get(className);
	}

	private List<String> permissions;
	private int numberOfParameters;
	private boolean hasMessageParameter;

	protected AbstractCommand(List<String> permissions, int numberOfParameters, boolean hasMessageParameter) {
		this.permissions = permissions;
		this.numberOfParameters = numberOfParameters;
		this.hasMessageParameter = hasMessageParameter;
	}

	public void execute(MessageReceivedEvent e) {
		if(checkUserPermissions(e.getAuthor().getPermissionsForGuild(e.getGuild()), e.getAuthor().getRolesForGuild(e.getGuild()), e.getGuild())) {
			String[] params = extractCommandParameters(e.getMessage().getContent());

			if(params != null) commandBody(params, e);
			else MessageSender.getInstance().sendMessage(e.getChannel(), "Invalid usage", "Expected " + numberOfParameters + " parameter(s)");

		} else MessageSender.getInstance().sendMessage(e.getChannel(), "Permission denied", "You don't possess the rights to execute this commands");
	}

	private boolean checkUserPermissions(EnumSet<Permissions> userPermissions, List<IRole> userRoles, IGuild guild) {
		if(permissions == null) return true;

		boolean hasExecutePermission;
		for(String permission : permissions) {
			switch(permission) {
				case "ADMINISTRATOR":
					hasExecutePermission = userPermissions.contains(Permissions.ADMINISTRATOR);
					break;
				default:
					hasExecutePermission = userRoles.contains(guild.getRolesByName(permission).get(0));
			}

			if(hasExecutePermission) return true;
		}

		return false;
	}

	private String[] extractCommandParameters(String message) {
		String[] splitMessage = message.split(" ");

		if(numberOfParameters == 999) numberOfParameters = splitMessage.length - 1;

		if((splitMessage.length - 1 < numberOfParameters) || (splitMessage.length - 1 > numberOfParameters && !hasMessageParameter)) return null;

		String[] params = new String[numberOfParameters];

		for(int i = 0; i < numberOfParameters; i++) {
			params[i] = splitMessage[i+1];
		}

		if(hasMessageParameter) {
			String longParam = "";
			for(int i = numberOfParameters; i < splitMessage.length; i++) {
				longParam = longParam + " " + splitMessage[i];
			}
			params[numberOfParameters - 1] = longParam.trim();
		}

		return params;
	}

	abstract protected void commandBody(String[] params, MessageReceivedEvent e);

	protected List<String> getPermissions() {
		return permissions;
	}

	protected void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	protected int getNumberOfParameters() {
		return numberOfParameters;
	}

	protected void setNumberOfParameters(int numberOfParameters) {
		this.numberOfParameters = numberOfParameters;
	}

	protected boolean hasMessageParameter() {
		return hasMessageParameter;
	}

	protected void setHasMessageParameter(boolean hasMessageParameter) {
		this.hasMessageParameter = hasMessageParameter;
	}

}