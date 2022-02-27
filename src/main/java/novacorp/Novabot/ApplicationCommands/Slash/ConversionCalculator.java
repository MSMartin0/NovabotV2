package novacorp.Novabot.ApplicationCommands.Slash;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Map;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.SlashCommandFunctionality;
import novacorp.Novabot.ConversionTable;

public class ConversionCalculator implements SlashCommandFunctionality {
    private static final Map<String, Integer> BASES = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("binary", 2),
        new AbstractMap.SimpleEntry<>("octal", 8),
        new AbstractMap.SimpleEntry<>("hex", 16)
    );
    @Override
    public ReplyCallbackAction execute(final SlashCommandInteractionEvent event) {
        final String subcommandName = event
            .getSubcommandName();
        final OptionMapping optionValue = event
            .getOption(subcommandName);
        BigInteger decimalValue;
        final String valueString = optionValue
            .getAsString();
        if(!subcommandName.equals("decimal")) {
            final int base = BASES.get(subcommandName);
            try {
                decimalValue = new BigInteger(valueString, base);
            }
            catch(final NumberFormatException e) {
                final String response = "Please enter a valid " +
                    subcommandName + " value";
                return event
                    .reply(response)
                    .setEphemeral(true);
            }
        }
        else {
            decimalValue = new BigInteger(valueString);
        }
        final ConversionTable table = ConversionTable
            .fromBigInteger(decimalValue);
        final MessageEmbed embed = table.getEmbed();
        return event
            .replyEmbeds(embed);
    }
}
