package dev.kyro.hunter;

import dev.kyro.hunter.gamecommands.TestCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "hunter", version = "1.0")
public class Hunter {
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        for(int i = 0; i < 10; i++) {
            System.out.println("Hunter loading");
        }

        ArcticJDA.init("<DISCORD_TOKEN>", true);

        registerListeners();
        registerCommands();

        new HReqThread().start();
        new WebhookThread().start();
    }

    public static void registerListeners() {

        MinecraftForge.EVENT_BUS.register(new WorldTracker());
    }

    public static void registerCommands() {

        ClientCommandHandler.instance.registerCommand(new TestCommand());
    }
}
