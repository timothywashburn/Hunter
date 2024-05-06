package dev.kyro.hunter.mystics;

import dev.kyro.hunter.hypixelapi.HypixelPlayer;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mystic {

	public static List<WebhookRequest> mysticList = new ArrayList<>();

	public NBTCompound data;

	public HypixelPlayer owner;
	public MysticType type;
	public String name;
	public List<String> lore = new ArrayList<>();
	public long nonce;
	public int tier;
	public int lives;
	public int maxLives;
	public boolean isGemmed;
	public Map<Enchant, Integer> enchantMap = new HashMap<>();

	public Mystic(HypixelPlayer owner, NBTCompound data) {

		this.owner = owner;
		this.data = data;

		try {

			NBTCompound display = data.getCompound("tag").getCompound("display");
			NBTCompound attributes = data.getCompound("tag").getCompound("ExtraAttributes");

			type = MysticType.getMysticType(data.getInt("id", -1));

			name = display.getString("Name", "").replaceAll("[^\\x00-\\x7F]+.", "");
			for(Object line : display.getList("Lore")) {
				lore.add(((String) line).replaceAll("[^\\x00-\\x7F]+.", ""));
			}

			nonce = attributes.getLong("Nonce", -1);
			tier = attributes.getInt("UpgradeTier", -1);
			lives = attributes.getInt("Lives", -1);
			maxLives = attributes.getInt("MaxLives", -1);
			isGemmed = attributes.containsKey("UpgradeGemsUses");
			for(Object enchantObject : attributes.getList("CustomEnchants")) {

				NBTCompound enchantInfo = (NBTCompound) enchantObject;
				Enchant enchant = Enchant.getEnchant(enchantInfo.getString("Key", ""));
				if(enchant == null) continue;
				int enchantLvl = enchantInfo.getInt("Level", -1);
				enchantMap.put(enchant, enchantLvl);
			}

			isWorthy();

//			if(owner.prestige < 20 && enchantMap.containsKey(MysticEnchant.HIDDEN_JEWEL)) mysticList.add(this);
		} catch(Exception ignored) {

//			ignored.printStackTrace();
		}
	}

	public void isWorthy() {
		int enchants = enchantMap.size();

//		if(get(Enchant.BREACHING_CHARGE) != 0) addMystic(WebhookRequest.Webhook.BREACHING);
//		if(has(Enchant.HIDDEN_JEWEL) || has(Enchant.MELE_HIDDEN_JEWEL)) addMystic(WebhookRequest.Webhook.JEWEL);
		if(get(Enchant.REGULARITY) == 1) addMystic(WebhookRequest.Webhook.REG_1);

		if(owner.prestige >= 12 || enchantMap.containsKey(Enchant.SELF_CHECKOUT) ||
				enchantMap.containsKey(Enchant.HEALER)) return;

		boolean hasRare = false;
		for(Map.Entry<Enchant, Integer> entry : enchantMap.entrySet()) {
			if(!entry.getKey().isRare) continue;
			hasRare = true;
			break;
		}
		if(hasRare && tier != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(get(Enchant.BILLIONAIRE) == 3
				|| get(Enchant.DUELIST) == 3
				|| get(Enchant.CRITICALLY_FUNKY) == 3
				|| get(Enchant.TRUE_SHOT) == 3
				|| get(Enchant.PIT_BLOB) == 3
				|| get(Enchant.EXECUTIONER) > 1
				|| get(Enchant.SOLITUDE) > 1
				|| get(Enchant.RETRO_GRAVITY_MICROCOSM) > 1
				|| get(Enchant.REGULARITY) > 1
				|| get(Enchant.COMBO_PERUNS_WRATH) > 1
				|| has(Enchant.GAMBLE)
				|| has(Enchant.MEGA_LONGBOW)
				|| has(Enchant.VOLLEY)) addMystic(WebhookRequest.Webhook.TIER_3);
		if(tier != 3 && get(Enchant.SHARP) == 3 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.FANCY_RAIDER) == 3 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.DIAMOND_STOMP) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.KING_BUSTER) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.SHARK) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.COMBO_SWIFT) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.BERSERKER) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.PAIN_FOCUS) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.COMBO_HEAL) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.COMBO_DAMAGE) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.PARASITE) > 1 && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.MIRROR) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.CRITICALLY_FUNKY) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.LIFESTEAL) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.SPRINT_DRAIN) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.WASP) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.PIN_DOWN) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && has(Enchant.FASTER_THAN_THEIR_SHADOW) && enchants != 3) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.SWEATY) > 1 && enchants != 3 && type != MysticType.BOW) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.MOCTEZUMA) + get(Enchant.GOLD_BOOST) > 1 && type != MysticType.BOW
				&& get(Enchant.MOCTEZUMA) != 0 && get(Enchant.GOLD_BOOST) != 0) addMystic(WebhookRequest.Webhook.TIER_2);
		if(tier != 3 && get(Enchant.MOCTEZUMA) + get(Enchant.GOLD_BOOST) + get(Enchant.GOLD_BUMP) > 2 && type != MysticType.BOW) addMystic(WebhookRequest.Webhook.TIER_2);
		if(get(Enchant.MOCTEZUMA) + get(Enchant.GOLD_BOOST) + addGem() == 6) addMystic(WebhookRequest.Webhook.TIER_3);
		if(get(Enchant.SPRINT_DRAIN) + get(Enchant.WASP) + addGem() == 6) addMystic(WebhookRequest.Webhook.TIER_3);
		if(get(Enchant.PAIN_FOCUS) + get(Enchant.LIFESTEAL) + addGem() == 6) addMystic(WebhookRequest.Webhook.TIER_3);
	}
	
	public void addMystic(WebhookRequest.Webhook webhook) {
		
		mysticList.add(new WebhookRequest(this, webhook));
//		try {
//			File nonceFile = Misc.getFileFromResource("nonce.txt");
//			assert nonceFile != null;
//
//			Writer output = new BufferedWriter(new FileWriter(nonceFile, true));
//			output.append(String.valueOf(nonce));
//			output.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}

	public int addGem() {

		return isGemmed ? 0 : 1;
	}

	public int get(Enchant enchant) {

		return enchantMap.getOrDefault(enchant, 0);
	}

	public boolean has(Enchant enchant) {

		return enchantMap.containsKey(enchant);
	}

	public boolean isMystic() {

		return enchantMap.size() != 0;
	}
}
