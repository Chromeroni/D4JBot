package hera.store.unit;

import hera.database.entities.Binding;
import hera.database.types.BindingType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class BindingAccessUnit extends StorageAccessUnit<Binding>{

	public BindingAccessUnit() {
		super(Binding.class, Binding.ENTITY_NAME);
	}

	public List<Binding> forGuild(Long guild) {
		return get(Collections.singletonMap("guildFK", guild));
	}

	public List<Binding> forGuildAndType(Long guild, BindingType type) {
		return get(new LinkedHashMap<String, Object>() {{
			put("guildFK", guild);
			put("bindingTypeFK", type.name());
		}});
	}
}
