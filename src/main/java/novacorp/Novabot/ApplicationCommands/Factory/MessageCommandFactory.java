package novacorp.Novabot.ApplicationCommands.Factory;

import novacorp.Novabot.ApplicationCommands.Interface.MessageCommandFunctionality;
import novacorp.Novabot.ApplicationCommands.Message.*;

public class MessageCommandFactory {
    public MessageCommandFunctionality getMessageCommandFunctionality(final String name){
        switch(name){
            case "altcaps":
                return new Altcaps();
            default:
                throw new IllegalArgumentException("Slash functionality factory does not contain: " + name);
        }
    }
}
