package dev.kyro.hunter.mystics;

public class WebhookRequest {

	public Mystic mystic;
	public Webhook webhook;

	public WebhookRequest(Mystic mystic, Webhook webhook) {
		this.mystic = mystic;
		this.webhook = webhook;
	}

	public enum Webhook {

		TIER_2("<DISCORD_WEBHOOK>"),
		TIER_3("<DISCORD_WEBHOOK>"),
		BREACHING("<DISCORD_WEBHOOK>"),
		JEWEL("<DISCORD_WEBHOOK>"),
		REG_1("<DISCORD_WEBHOOK>");

		public String url;

		Webhook(String url) {
			this.url = url;
		}
	}
}
