package macstevins.eventsplus.mixin.client;

import macstevins.eventsplus.ModCore;
import macstevins.eventsplus.event.PlayerWorldEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Environment(EnvType.CLIENT)
@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {

	@Unique
	private static final ArrayList<Text> reasons = new ArrayList<>(Arrays.asList(Text.translatable("disconnect.closed"),
																		   Text.translatable("disconnect.disconnected"),
																		   Text.translatable("disconnect.exceeded_packet_rate"),
																		   Text.translatable("disconnect.kicked"),
																		   Text.translatable("disconnect.lost"),
																		   Text.translatable("disconnect.overflow"),
																		   Text.translatable("disconnect.quitting"),
																		   Text.translatable("disconnect.spam"),
																		   Text.translatable("disconnect.timeout"),
																		   Text.translatable("multiplayer.disconnect.banned"),
																		   Text.translatable("multiplayer.disconnect.chat_validation_failed"),
																		   Text.translatable("multiplayer.disconnect.duplicate_login"),
																		   Text.translatable("multiplayer.disconnect.flying"),
																		   Text.translatable("multiplayer.disconnect.generic"),
																		   Text.translatable("multiplayer.disconnect.idling"),
																		   Text.translatable("multiplayer.disconnect.illegal_characters"),
																		   Text.translatable("multiplayer.disconnect.invalid_entity_attacked"),
																		   Text.translatable("multiplayer.disconnect.invalid_packet"),
																		   Text.translatable("multiplayer.disconnect.invalid_player_movement"),
																		   Text.translatable("multiplayer.disconnect.invalid_vehicle_movement"),
																		   Text.translatable("multiplayer.disconnect.ip_banned"),
																		   Text.translatable("multiplayer.disconnect.kicked"),
																		   Text.translatable("multiplayer.disconnect.out_of_order_chat"),
																		   Text.translatable("multiplayer.disconnect.server_shutdown"),
																		   Text.translatable("multiplayer.disconnect.too_many_pending_chats"),
																		   Text.translatable("multiplayer.disconnect.unexpected_query_response"),
																		   Text.translatable("multiplayer.disconnect.unsigned_chat"),
																		   Text.translatable("multiplayer.status.quitting")));

	@Inject(method = "disconnect", at = @At("TAIL"))
	private void onGameLeave(Text disconnectReason, CallbackInfo ci) {
		
		if(!reasons.contains(disconnectReason))
			return;
		
		PlayerWorldEvent.LEAVE_EVENT.invoker().event();

		ModCore.isStillConnected = false;
	
	}

}
