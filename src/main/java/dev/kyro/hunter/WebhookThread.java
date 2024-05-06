package dev.kyro.hunter;

import dev.kyro.hunter.libs.DiscordWebhook;
import dev.kyro.hunter.mystics.Mystic;
import dev.kyro.hunter.mystics.Enchant;
import dev.kyro.hunter.mystics.WebhookRequest;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WebhookThread extends Thread {

	public static Map<UUID, Color> colorMap = new HashMap<>();

	@Override
	public void run() {

		if(Mystic.mysticList.isEmpty()) {
			sleepThread();
			return;
		}

		WebhookRequest request = Mystic.mysticList.remove(0);
		Mystic mystic = request.mystic;
		DiscordWebhook webhook = new DiscordWebhook(request.webhook.url);
		Color color = colorMap.get(mystic.owner.UUID);
		if(color == null) {
			color = new Color((int) (Math.random() * 156) + 100, (int) (Math.random() * 156) + 100, (int) (Math.random() * 156) + 100);
			colorMap.put(mystic.owner.UUID, color);
		}

		try {
			DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject()
					.setAuthor(mystic.name, "", "")
					.setTitle("`" + mystic.owner.name + "` - Prestige " + mystic.owner.prestige)
					.setDescription("Tier " + mystic.tier + ", Lives: " + mystic.lives + "/" + mystic.maxLives + (mystic.isGemmed ? ", Gemmed" : ""))
					.setColor(color);
			String mysticEnchantString = "";
			for(Map.Entry<Enchant, Integer> entry : mystic.enchantMap.entrySet()) {

				embedObject.addField(entry.getKey().getDisplayName(), entry.getValue() + "", true);
				mysticEnchantString += entry.getKey().getDisplayName() + " " + entry.getValue() + " ";
			}

			webhook.addEmbed(embedObject);
			webhook.setContent(mysticEnchantString.trim());
		} catch(Exception exception) {
			exception.printStackTrace();
			System.out.println(mystic.tier);
			System.out.println(mystic.lives);
			System.out.println(mystic.maxLives);
		}

		new Thread(() -> {
			try {
				webhook.execute();
			} catch(IOException e) {
//				e.printStackTrace();
				Mystic.mysticList.add(request);
			}
		}).start();

		sleepThread();
	}

	public void sleepThread() {
		int time = 3000;

		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		new WebhookThread().start();
	}
}
