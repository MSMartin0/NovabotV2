package novacorp.Novabot;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnReadyListener implements EventListener {
    private static final Logger logger = LoggerFactory.getLogger(OnReadyListener.class);
    @Override
    public void onEvent(GenericEvent event) {
        if(event instanceof ReadyEvent){
            logger.info("Bot has logged in successfully");
        }
    }
}
