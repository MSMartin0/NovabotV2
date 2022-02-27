package novacorp.Novabot.ApplicationCommands.Factory;

import novacorp.Novabot.ApplicationCommands.Slash.*;
import novacorp.Novabot.ApplicationCommands.Interface.SlashCommandFunctionality;

public class SlashCommandFactory {
    public SlashCommandFunctionality getSlashFunctionality(final String name){
        switch(name){
            case "emoji":
                return new EmojiSource();
            case "weedday":
                return new Weedday();
            case "galactic":
                return new GalacticAlphabet();
            case "conversion":
                return new ConversionCalculator();
            default:
                throw new IllegalArgumentException("Slash functionality factory does not contain: " + name);
        }
    }
}
