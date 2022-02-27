package novacorp.Novabot.ApplicationCommands.Interface;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public interface MessageCommandFunctionality {
    ReplyCallbackAction execute(final MessageContextInteractionEvent event);
}
