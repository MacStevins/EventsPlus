package macstevins.eventsplus.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface GameEvent {

	static Event<GameEvent> createEvent() {
		
		return EventFactory.createArrayBacked(GameEvent.class, (listeners) -> () -> {
			
			for(GameEvent listener : listeners) listener.event();
		
		});
	
	}

	void event();

}
