package novacorp.Novabot.ApplicationCommands.Slash;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.SlashCommandFunctionality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GalacticAlphabet implements SlashCommandFunctionality {
    private static final Logger logger = LoggerFactory.getLogger(GalacticAlphabet.class);
    private static final Map<Character, Integer> GALACTIC_CHAR_MAP = Map.ofEntries(
        new SimpleEntry<>('a', 0x00001511),
        new SimpleEntry<>('b', 0x00000296),
        new SimpleEntry<>('c', 0x000014f5),
        new SimpleEntry<>('d', 0x000021b8),
        new SimpleEntry<>('e', 0x000014b7),
        new SimpleEntry<>('f', 0x00002393),
        new SimpleEntry<>('g', 0x000022a3),
        new SimpleEntry<>('h', 0x00002351),
        new SimpleEntry<>('i', 0x0000254e),
        new SimpleEntry<>('j', 0x000022ee),
        new SimpleEntry<>('k', 0x0000a58c),
        new SimpleEntry<>('l', 0x0000a58e),
        new SimpleEntry<>('m', 0x000014b2),
        new SimpleEntry<>('n', 0x000030ea),
        new SimpleEntry<>('o', 0x0000004a),
        new SimpleEntry<>('p', 0x002100a1),
        new SimpleEntry<>('q', 0x00001451),
        new SimpleEntry<>('r', 0x00002237),
        new SimpleEntry<>('s', 0x000014ed),
        new SimpleEntry<>('t', 0x21380323),
        new SimpleEntry<>('u', 0x0000268d),
        new SimpleEntry<>('v', 0x0000234a),
        new SimpleEntry<>('w', 0x00002234),
        new SimpleEntry<>('x', 0x0307002f),
        new SimpleEntry<>('y', 0x007c007c),
        new SimpleEntry<>('z', 0x00002a05)
    );
    @Override
    public ReplyCallbackAction execute(final SlashCommandInteractionEvent event) {
        final String messageContent = event
            .getOption("message")
            .getAsString();
        final String galacticString = convertToGalactic(messageContent);
        if(galacticString.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }
        return event.reply(galacticString);
    }
    private String convertToGalactic(final String message){
        final StringBuffer newStringBuffer = new StringBuffer();
        for(int I = 0; I < message.length(); I++) {
            final char character = message.charAt(I);
            if(GALACTIC_CHAR_MAP.containsKey(character)) {
                final int newCharacter = GALACTIC_CHAR_MAP.get(character);
                final int lowerCharacter = newCharacter & 0x0000FFFF;
                final int upperCharacter = (newCharacter & 0xFFFF0000) >> 16;
                if(upperCharacter > 0) {
                    newStringBuffer.append((char) upperCharacter);
                }
                newStringBuffer.append((char) lowerCharacter);
            }
            else {
                newStringBuffer.append(character);
            }
        }
        return newStringBuffer.toString();
    }
}
