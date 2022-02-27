package novacorp.Novabot.ApplicationCommands.Slash;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import novacorp.Novabot.ApplicationCommands.Interface.SlashCommandFunctionality;

public class Weedday implements SlashCommandFunctionality {
    @Override
    public ReplyCallbackAction execute(final SlashCommandInteractionEvent event) {
        final int dayCount = calculateDays();
        final StringBuffer buffer = new StringBuffer();
        buffer.append("There are ");
        buffer.append(dayCount);
        buffer.append(" days until 4/20");
        return event.reply(buffer.toString());
    }
    private int calculateDays() {
        final LocalDate currentDate = LocalDate.now();
        final int year = currentDate.getYear();
        final LocalDate nextWeedDay = LocalDate.of(year, 4, 20);
        final long daysUntil =
            ChronoUnit.DAYS.between(currentDate, nextWeedDay);
        if(daysUntil < 0) {
            return 365 + (int) daysUntil;
        }
        else {
            return (int) daysUntil;
        }
    }
}
