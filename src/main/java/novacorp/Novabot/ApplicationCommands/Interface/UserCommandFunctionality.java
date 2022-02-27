package novacorp.Novabot.ApplicationCommands.Interface;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public interface UserCommandFunctionality {
    ReplyCallbackAction execute(final UserContextInteractionEvent event);
}
