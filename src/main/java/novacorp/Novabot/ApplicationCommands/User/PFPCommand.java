package novacorp.Novabot.ApplicationCommands.User;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.UserCommandFunctionality;

public class PFPCommand implements UserCommandFunctionality {
    @Override
    public ReplyCallbackAction execute(final UserContextInteractionEvent event) {
        final User user = event.getTarget();
        final String pfpLink = user.getAvatarUrl();
        return event
            .reply(pfpLink)
            .setEphemeral(true);
    }
}
