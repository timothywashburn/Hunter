package dev.kyro.test;

import dev.kyro.arcticjda.ArcticJDA;
import dev.kyro.hunter.hypixelapi.HypixelAPI;
import dev.kyro.hunter.hypixelapi.HypixelPlayer;

import java.util.UUID;

public class Main {

	public static void main(String[] args) {
		ArcticJDA.init("ODUzNzY3NzE2MTA4NjMyMTA0.YMaLJQ.Opl6gBie5OjuLtzDaGQ3TvgJRdE", true);
		ArcticJDA.registerCommand(new TestDiscordCommand());

		HypixelPlayer hypixelPlayer = new HypixelPlayer(HypixelAPI.request(UUID.fromString("1db946e6-edfe-42ac-9fd6-bf135aa5130e")));
		System.out.println(hypixelPlayer.name);
	}
}
