package dev.kyro.hunter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HuntingThread extends Thread {

	public static EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
	public static boolean running = true;

	public static List<UUID> toRequest = new ArrayList<>();
	public static List<UUID> requested = new ArrayList<>();

	@Override
	public void run() {

		try {

			if(Math.random() < 0.03) {

				Minecraft.getMinecraft().thePlayer.sendChatMessage("/l");
				WorldTracker.worldType = WorldTracker.WorldType.LOBBY;
			} else {

				Minecraft.getMinecraft().thePlayer.sendChatMessage("/play pit");
			}
		} catch(Exception ignored) { }

		sleepThread(2000);

		try {
			if(WorldTracker.worldType == WorldTracker.WorldType.PIT) {
				for(Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
					if(!(entity instanceof EntityPlayer)) continue;
					EntityPlayer player = (EntityPlayer) entity;

					if(toRequest.contains(player.getUniqueID()) || requested.contains(player.getUniqueID())
							|| entity == Minecraft.getMinecraft().thePlayer || player.getName() == null || player.isInvisible()) continue;
					toRequest.add(player.getUniqueID());
				}
			}
		} catch(Exception ignored) { }

		sleepThread(3250);
		reRun();
	}

	public void sleepThread(int time) {

		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void reRun() {

		if(running) new HuntingThread().start();
	}
}
