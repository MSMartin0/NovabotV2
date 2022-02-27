package novacorp.Novabot.ApplicationCommands.Factory;

import novacorp.Novabot.ApplicationCommands.Interface.UserCommandFunctionality;
import novacorp.Novabot.ApplicationCommands.User.*;

public class UserCommandFactory {
    public UserCommandFunctionality getUserCommandFunctionality(final String name) {
        switch(name) {
            case "pfp":
                return new PFPCommand();
            default:
                throw new IllegalArgumentException("Slash functionality factory does not contain: " + name);
        }
    }
}
