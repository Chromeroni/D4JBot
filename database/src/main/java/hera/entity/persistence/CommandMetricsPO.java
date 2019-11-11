package hera.entity.persistence;

import hera.entity.mapped.CommandMetrics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "command_metrics")
public class CommandMetricsPO {

	public static final String ENTITY_NAME = "CommandMetricsPO";

	@Id
	private int commandFK;

	@Id
	private Long guildFK;

	@Id
	private Long userFK;

	private int callCount;

	public CommandMetricsPO() {
	}

	public CommandMetricsPO(int commandFK, Long guildFK, Long userFK, int callCount) {
		this.commandFK = commandFK;
		this.guildFK = guildFK;
		this.userFK = userFK;
		this.callCount = callCount;
	}

	public CommandMetrics mapToNonePO() {
		return new CommandMetrics(
				this.commandFK,
				this.guildFK,
				this.userFK,
				this.callCount
		);
	}

	public int getCommandFK() {
		return commandFK;
	}

	public void setCommandFK(int commandFK) {
		this.commandFK = commandFK;
	}

	public Long getGuildFK() {
		return guildFK;
	}

	public void setGuildFK(Long guildFK) {
		this.guildFK = guildFK;
	}

	public Long getUserFK() {
		return userFK;
	}

	public void setUserFK(Long userFK) {
		this.userFK = userFK;
	}

	public int getCallCount() {
		return callCount;
	}

	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}
}
