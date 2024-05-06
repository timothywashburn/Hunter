package dev.kyro.test;

import dev.kyro.arcticjda.controllers.ADiscordCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class TestDiscordCommand extends ADiscordCommand {

	public TestDiscordCommand() {
		super("test", "ping");
	}

	@Override
	public void execute(GuildMessageReceivedEvent event, List<String> args) {

		event.getChannel().sendMessage("pong").queue();
	}
}
