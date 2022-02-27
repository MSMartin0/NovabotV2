package novacorp.Novabot.Listeners;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import novacorp.Novabot.SlashRegistrar;

public class ServerJoinListener extends ListenerAdapter {
    private final SlashRegistrar slashRegistrar;
    public ServerJoinListener(final SlashRegistrar slashRegistrar) {
        this.slashRegistrar = slashRegistrar;
    }
    @Override
    public void onGuildJoin(final GuildJoinEvent event) {
        final String guildID = event
            .getGuild()
            .getId();
        slashRegistrar.registerForGuild(guildID);
    }
}
