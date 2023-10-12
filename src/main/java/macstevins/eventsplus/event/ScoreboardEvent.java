package macstevins.eventsplus.event;

import macstevins.eventsplus.api.event.InfoGameEvent;
import net.fabricmc.fabric.api.event.Event;

public interface ScoreboardEvent {

	String OBJECTIVE_DISPLAYNAME = "scoreboard.objective.displayName";
	String OBJECTIVE_RENDERTYPE = "scoreboard.objective.renderType";

	Event<InfoGameEvent> OBJECTIVE_EVENT = InfoGameEvent.createEvent();

}
