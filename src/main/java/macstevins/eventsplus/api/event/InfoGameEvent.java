package macstevins.eventsplus.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface InfoGameEvent {

	static Event<InfoGameEvent> createEvent() {
		
		return EventFactory.createArrayBacked(InfoGameEvent.class, (listeners) -> (e) -> {
			
			for(InfoGameEvent listener : listeners) listener.event(e);
		
		});
	
	}

	void event(EventInfo e);

}
