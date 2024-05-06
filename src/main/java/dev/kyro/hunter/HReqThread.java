package dev.kyro.hunter;

import dev.kyro.hunter.hypixelapi.APIKeys;
import dev.kyro.hunter.hypixelapi.HypixelAPI;
import dev.kyro.hunter.hypixelapi.HypixelPlayer;
import dev.kyro.hunter.mystics.Mystic;

public class HReqThread extends Thread {

	public static int totalRequested = 0;

	@Override
	public void run() {

		if(HuntingThread.toRequest.isEmpty()) {
			sleepThread();
			return;
		}

		new Thread(() -> {
			HypixelPlayer hypixelPlayer = new HypixelPlayer(HypixelAPI.request(HuntingThread.toRequest.remove(0)));
			HuntingThread.requested.add(hypixelPlayer.UUID);
			if(hypixelPlayer.name != null) totalRequested++;
			System.out.println(totalRequested + " " + Mystic.mysticList.size());
		}).start();

		sleepThread();
	}

	public void sleepThread() {
		int time = 500 / APIKeys.apiKeys.size() + 10;

		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		new HReqThread().start();
	}
}
