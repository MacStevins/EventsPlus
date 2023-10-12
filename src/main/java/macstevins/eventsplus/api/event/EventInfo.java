package macstevins.eventsplus.api.event;

import com.google.gson.reflect.TypeToken;
import macstevins.eventsplus.ModCore;

import java.util.HashMap;

public class EventInfo {

	public static final String TEXT = "text";
	public static final String TEXT_TITLE = "text.title";

	public static final String UPDATEMODE = "updateMode";

	private final HashMap<String, Object> states = new HashMap<>();

	private boolean lockStates;

	@Deprecated
	public Object getObjectState(String name) { return states.getOrDefault(name, new EmptyState()); }

	@SuppressWarnings("unchecked")
	@Deprecated
	public <T> T getState(String name) { return (T) states.getOrDefault(name, new EmptyState()); }

	public <T> T getState(String name, Class<T> clazz) { return clazz.cast(states.getOrDefault(name, new EmptyState())); }

	@SuppressWarnings("unchecked")
	public <T> T getState(String name, TypeToken<T> token) { return (T) token.getRawType().cast(states.getOrDefault(name, new EmptyState())); }

	public String[] getStates() { return states.keySet().toArray(new String[0]); }

	public void lockStates() { lockStates = true; }

	public void setState(String name, Object state) {
		
		if(lockStates) {
			
			ModCore.LOGGER.warn("Event State " + this + " is locked");
			return;
		
		}
		
		states.put(name, state);
	
	}

	public static class EmptyState {}

}
