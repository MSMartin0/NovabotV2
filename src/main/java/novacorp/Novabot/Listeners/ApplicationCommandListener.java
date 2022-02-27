package novacorp.Novabot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import novacorp.Novabot.ApplicationCommands.Factory.*;
import novacorp.Novabot.ApplicationCommands.Interface.*;

public class ApplicationCommandListener extends ListenerAdapter {
    private final SlashCommandFactory slashCommandFactory;
    private final UserCommandFactory userCommandFactory;
    private final MessageCommandFactory messageCommandFactory;
    public ApplicationCommandListener() {
        this(new SlashCommandFactory(), new UserCommandFactory(), new MessageCommandFactory());
    }
    public ApplicationCommandListener(final SlashCommandFactory slashCommandFactory,
                                      final UserCommandFactory userCommandFactory,
                                      final MessageCommandFactory messageCommandFactory) {
        this.slashCommandFactory = slashCommandFactory;
        this.userCommandFactory = userCommandFactory;
        this.messageCommandFactory = messageCommandFactory;
    }
    @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event){
        final String eventName = event.getName();
        final SlashCommandFunctionality slashCommandFunctionality = slashCommandFactory
            .getSlashFunctionality(eventName);
        try{
            slashCommandFunctionality
                .execute(event)
                .queue();
        }
        catch (Exception e) {
            event
                .reply("An error has occured in the slash function")
                .setEphemeral(true)
                .queue();
            e.printStackTrace();
        }
    }
    @Override
    public void onUserContextInteraction(final UserContextInteractionEvent event) {
        final String eventName = event.getName();
        final UserCommandFunctionality userCommandFunctionality = userCommandFactory
            .getUserCommandFunctionality(eventName);
        try{
            userCommandFunctionality
                .execute(event)
                .queue();
        }
        catch (Exception e) {
            event
                .reply("An error has occured in the slash function")
                .setEphemeral(true)
                .queue();
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageContextInteraction(final MessageContextInteractionEvent event) {
        final String eventName = event.getName();
        final MessageCommandFunctionality messageCommandFunctionality = messageCommandFactory
            .getMessageCommandFunctionality(eventName);
        try{
            messageCommandFunctionality
                .execute(event)
                .queue();
        }
        catch (Exception e) {
            event
                .reply("An error has occured in the slash function")
                .setEphemeral(true)
                .queue();
            e.printStackTrace();
        }
    }
}
