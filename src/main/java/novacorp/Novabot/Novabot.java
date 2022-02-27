package novacorp.Novabot;

import static java.util.Collections.emptyList;

import java.net.http.HttpClient;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import novacorp.Novabot.Listeners.ApplicationCommandListener;
import novacorp.Novabot.Listeners.ServerJoinListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Novabot {
    private static final Logger logger = LoggerFactory.getLogger(Novabot.class);
    public static void main(String[] args) {
        try {
            final String token = args[0];
            final String applicationID = args[1];
            final SlashRegistrar registrar = new SlashRegistrar(token, applicationID, HttpClient.newHttpClient());
            logger.info("Getting guilds application is in");
            final List<String> guildIDs = registrar.getGuildIDs();
            logger.info("Registering guild commands");
            guildIDs.stream()
                .forEach(registrar::registerForGuild);
            logger.info("Guild commands registered");
            final JDA jda = JDABuilder.create(emptyList())
                    .setToken(token)
                    .addEventListeners(new OnReadyListener())
                    .addEventListeners(new ApplicationCommandListener())
                    .addEventListeners(new ServerJoinListener(registrar))
                    .build();
            jda.awaitReady();
        }
        catch(IndexOutOfBoundsException e) {
            logger.error("No token/application id for the bot was provided", e);
        }
        catch(LoginException e) {
            logger.error("An error occured trying to login", e);
        }
        catch(Exception e) {
            logger.error("An error occured", e);
        }
    }
}
