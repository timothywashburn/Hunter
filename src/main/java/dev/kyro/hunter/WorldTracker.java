package dev.kyro.hunter;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldTracker {

	public static WorldType worldType = WorldType.LOBBY;

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {

		String message = event.message.getFormattedText();

		if(message.contains("\u00A7a\u00A7lSERVER FOUND!")) {

			worldType = WorldType.PIT;
		} else if(message.contains("\u00A7eClick here to view them!")) {

			worldType = WorldType.LOBBY;
		}
	}

	public enum WorldType {
		PIT,
		LOBBY,
		LIMBO
	}
}
