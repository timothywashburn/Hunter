package dev.kyro.hunter.gamecommands;

import dev.kyro.hunter.HuntingThread;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class TestCommand implements ICommand {

    public static HuntingThread huntingThread = null;

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;

        if(huntingThread == null) {
//            HReqThread.totalRequested = 0;
//            HuntingThread.requested.clear();
//            Mystic.mysticList.clear();
            HuntingThread.running = true;
            huntingThread = new HuntingThread();
            huntingThread.start();
            System.out.println("Started thread");
        } else {
            HuntingThread.running = false;
            huntingThread = null;
            System.out.println("Stopped thread");
        }
    }

    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "test";
    }

    @Override
    public List<String> getCommandAliases() {
        return new ArrayList<>();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
