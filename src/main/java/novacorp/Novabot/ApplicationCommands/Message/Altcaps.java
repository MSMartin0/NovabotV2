package novacorp.Novabot.ApplicationCommands.Message;

import java.util.Locale;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.MessageCommandFunctionality;

public class Altcaps implements MessageCommandFunctionality {
    public ReplyCallbackAction execute(final MessageContextInteractionEvent event) {
        final String message = event
            .getTarget()
            .getContentStripped();
        final String alternateMessage = alternate(message);
        return event
            .reply(alternateMessage)
            .setEphemeral(true);
    }
    private String alternate(final String message){
        final char[] messageArr = message
            .toLowerCase(Locale.ROOT)
            .toCharArray();
        boolean makeUppercase =
            (System.currentTimeMillis() % 2 == 0);
        for(int I = 0; I < messageArr.length; I++){
            if(Character.isLetter(messageArr[I])) {
                if(makeUppercase) {
                    messageArr[I] = Character.toUpperCase(messageArr[I]);
                }
                makeUppercase = !makeUppercase;
            }
        }
        return String.valueOf(messageArr);
    }
}
