package novacorp.Novabot.ApplicationCommands.Interface;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public interface SlashCommandFunctionality {
    ReplyCallbackAction execute(final SlashCommandInteractionEvent event);
}