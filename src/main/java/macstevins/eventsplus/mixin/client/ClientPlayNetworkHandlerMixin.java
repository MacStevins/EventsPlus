package macstevins.eventsplus.mixin.client;

import macstevins.eventsplus.ModCore;
import macstevins.eventsplus.api.event.EventInfo;
import macstevins.eventsplus.event.InGameHudEvent;
import macstevins.eventsplus.event.PlayerWorldEvent;
import macstevins.eventsplus.event.ScoreboardEvent;
import macstevins.eventsplus.event.TeamEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

	@Inject(method = "onGameJoin", at = @At("TAIL"))
	private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
		
		PlayerWorldEvent.CONNECTION_EVENT.invoker().event();
		
		if(!ModCore.isStillConnected)
			PlayerWorldEvent.JOIN_EVENT.invoker().event();
		
		ModCore.isStillConnected = true;
	
	}

	@Inject(method = "onOverlayMessage", at = @At("TAIL"))
	private void onOverlayMessage(OverlayMessageS2CPacket packet, CallbackInfo ci) {
		
		EventInfo e = new EventInfo();
		e.setState(EventInfo.TEXT_TITLE, packet.getMessage());
		e.lockStates();
		
		InGameHudEvent.ACTION_BAR_EVENT.invoker().event(e);
	
	}

	@Inject(method = "onScoreboardObjectiveUpdate", at = @At("TAIL"))
	private void onScoreboardObjectiveUpdate(ScoreboardObjectiveUpdateS2CPacket packet, CallbackInfo ci) {
		
		EventInfo e = new EventInfo();
		e.setState(EventInfo.UPDATEMODE, packet.getMode());
		e.setState(EventInfo.TEXT_TITLE, packet.getName());
		e.setState(ScoreboardEvent.OBJECTIVE_DISPLAYNAME, packet.getDisplayName());
		e.setState(ScoreboardEvent.OBJECTIVE_RENDERTYPE, packet.getType());
		e.lockStates();
		
		ScoreboardEvent.OBJECTIVE_EVENT.invoker().event(e);
	
	}

	@Inject(method = "onSubtitle", at = @At("TAIL"))
	private void onSubtitle(SubtitleS2CPacket packet, CallbackInfo ci) {
		
		EventInfo e = new EventInfo();
		e.setState(EventInfo.TEXT_TITLE, packet.getSubtitle());
		e.lockStates();
		
		InGameHudEvent.SUBTITLE_EVENT.invoker().event(e);
	
	}

	@Inject(method = "onTeam", at = @At("TAIL"))
	private void onTeamUpdate(TeamS2CPacket packet, CallbackInfo ci) {
		
		EventInfo e = new EventInfo();
		e.setState(EventInfo.UPDATEMODE, packet.getTeamOperation());
		e.setState(TeamEvent.UPDATEMODE_PLAYER, packet.getPlayerListOperation());
		e.setState(TeamEvent.TEAM, packet.getTeam());
		e.setState(TeamEvent.TEAM_NAME, packet.getTeamName());
		e.setState(TeamEvent.PLAYER_NAMES, packet.getPlayerNames());
		e.lockStates();
		
		TeamEvent.TEAM_EVENT.invoker().event(e);
	
	}

	@Inject(method = "onTitle", at = @At("TAIL"))
	private void onTitle(TitleS2CPacket packet, CallbackInfo ci) {
		
		EventInfo e = new EventInfo();
		e.setState(EventInfo.TEXT_TITLE, packet.getTitle());
		e.lockStates();
		
		InGameHudEvent.TITLE_EVENT.invoker().event(e);
	
	}

}
