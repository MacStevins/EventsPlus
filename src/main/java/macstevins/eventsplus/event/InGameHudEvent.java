package macstevins.eventsplus.event;

import macstevins.eventsplus.api.event.InfoGameEvent;
import net.fabricmc.fabric.api.event.Event;

public interface InGameHudEvent {

	Event<InfoGameEvent> ACTION_BAR_EVENT = InfoGameEvent.createEvent();
	Event<InfoGameEvent> SUBTITLE_EVENT = InfoGameEvent.createEvent();
	Event<InfoGameEvent> TITLE_EVENT = InfoGameEvent.createEvent();

}
