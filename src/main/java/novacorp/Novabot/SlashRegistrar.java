package novacorp.Novabot;

import static java.util.stream.Collectors.toList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashRegistrar {
    private static final Logger logger = LoggerFactory.getLogger(SlashRegistrar.class);
    private final JsonArray commandObjects;
    private final String token;
    private final String applicationID;
    private final HttpClient httpClient;
    private final Gson gson;
    public SlashRegistrar(final String token, final String applicationID, final HttpClient httpClient) {
        this.token = token;
        this.applicationID = applicationID;
        this.httpClient = httpClient;
        this.gson = new Gson();
        this.commandObjects = loadCommandsObject();
    }
    public List<String> getGuildIDs()
        throws InterruptedException, IOException, URISyntaxException{
        logger.debug("Building HTTPS request");
        final HttpRequest.Builder builder = getAuthTokenHTTPSRequestBuilder();
        final String uriString = "https://discord.com/api/v9/users/@me/guilds";
        final URI uri = new URI(uriString);
        final HttpRequest request = builder.uri(uri)
            .GET()
            .build();
        logger.debug("Sending HTTPS request");
        final HttpResponse<String> response = httpClient
            .send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() >= 400) {

            throw new IOException("Got status code " + response.statusCode() + "\nBody: " + response.body());
        }
        final JsonArray guildArray = JsonParser
            .parseString(response.body())
            .getAsJsonArray();
        final Type listType = new TypeToken<List<JsonObject>>(){}.getType();
        final List<JsonObject> guildList = gson.fromJson(guildArray, listType);
        final List<String> guildIDs = guildList.stream()
            .map(guildObject -> {
                if(guildObject.has("id")) {
                    return guildObject.get("id").getAsString();
                }
                return "";
            })
            .filter(id -> (id != null && id.length() != 0))
            .collect(toList());
        return guildIDs;
    }
    public void registerForGuild(final String guildID) {
        logger.debug("Registering for guild " + guildID);
        try {
            logger.debug("Building HTTPS request");
            final HttpRequest.Builder builder = getAuthTokenHTTPSRequestBuilder();
            final String uriString = String
                .format("https://discord.com/api/v9/applications/%s/guilds/%s/commands",
                applicationID, guildID);
            final URI uri = new URI(uriString);
            final HttpRequest request = builder.uri(uri)
                .PUT(BodyPublishers.ofString(commandObjects.toString()))
                .build();
            logger.debug("Sending HTTPS request");
            final HttpResponse<String> response = httpClient
                .send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() >= 400) {

                throw new IOException("Got status code " + response.statusCode() + "\nBody: " + response.body());
            }
        }
        catch(Exception e) {
            logger.error("An error occured in uploading the commands", e);
            logger.error("Guild " + guildID);
            logger.error("Error", e);
        }
    }
    private JsonArray loadCommandsObject() {
        final InputStream settingsStream =
            Novabot.class.getClassLoader()
                .getResourceAsStream("commands.json");
        final BufferedReader settingsReader =
            new BufferedReader(new InputStreamReader(settingsStream,
                StandardCharsets.UTF_8));
        final JsonArray commandObjectsRaw = JsonParser
            .parseReader(settingsReader)
            .getAsJsonObject()
            .get("commands")
            .getAsJsonArray();
        return commandObjectsRaw;
    }
    private HttpRequest.Builder getAuthTokenHTTPSRequestBuilder() {
        return HttpRequest.newBuilder()
            .header("Authorization", "Bot " + token)
            .headers("Content-Type", "application/json");
    }
}
