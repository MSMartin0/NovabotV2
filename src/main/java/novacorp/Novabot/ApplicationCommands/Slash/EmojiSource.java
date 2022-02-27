package novacorp.Novabot.ApplicationCommands.Slash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.SlashCommandFunctionality;

public class EmojiSource implements SlashCommandFunctionality {
    private static final Pattern EMOJI_REGEX =
        Pattern.compile("<(.*?):(.*?):(.*?)>");
    private static final String CDN_LINK =
        "https://cdn.discordapp.com/emojis/";
    public ReplyCallbackAction execute(final SlashCommandInteractionEvent event) {
        final String messageContent = event
            .getOption("emoji")
            .getAsString();
        final StringBuffer buffer = new StringBuffer();
        final Matcher matcher = EMOJI_REGEX
            .matcher(messageContent);
        if(matcher.find()) {
            buffer.append(CDN_LINK);
            final String emojiID = matcher.group(3);
            buffer.append(emojiID);
            if(matcher.group(1).equals("a")){
                buffer.append(".gif");
            }
        }
        else {
            buffer.setLength(0);
            buffer.append("Invalid emoji provided");
        }
        return event
            .reply(buffer.toString());
    }
}
