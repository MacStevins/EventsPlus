package macstevins.eventsplus.event;

import macstevins.eventsplus.api.event.InfoGameEvent;
import net.fabricmc.fabric.api.event.Event;

public interface TeamEvent {

	String TEAM = "team";
	String PLAYER_NAMES = "team.playerName.list";
	String TEAM_NAME = "text.team.name";
	String UPDATEMODE_PLAYER = "updateMode.player";

	Event<InfoGameEvent> TEAM_EVENT = InfoGameEvent.createEvent();

}
