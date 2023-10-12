package macstevins.eventsplus.event;

import macstevins.eventsplus.api.event.GameEvent;
import net.fabricmc.fabric.api.event.Event;

public interface PlayerWorldEvent {

//	String PLAYER_CONNECTION = "player.connection";
//	String PLAYER_LEAVE = "player.connection.state.leave";
//	String PLAYER_JOIN = "player.connection.state.join";

	Event<GameEvent> CONNECTION_EVENT = GameEvent.createEvent();
	Event<GameEvent> LEAVE_EVENT = GameEvent.createEvent();
	Event<GameEvent> JOIN_EVENT = GameEvent.createEvent();

}
