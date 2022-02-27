package novacorp.Novabot;

import java.math.BigInteger;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

@Getter
public class ConversionTable {
    private final BigInteger decimalValue;
    private final String binaryValue;
    private final String octalValue;
    private final String hexValue;
    public ConversionTable(final BigInteger decimalValue, final String binaryValue,
        final String octalValue, final String hexValue) {
        this.decimalValue = decimalValue;
        this.binaryValue = binaryValue;
        this.octalValue = octalValue;
        this.hexValue = hexValue;
    }
    public MessageEmbed getEmbed() {
        final EmbedBuilder builder = new EmbedBuilder()
            .setTitle("Conversion Table")
            .addField("Decimal", decimalValue.toString(), false)
            .addField("Binary", binaryValue, false)
            .addField("Octal", octalValue, false)
            .addField("Hexadecimal", hexValue, false);
        return builder.build();
    }
    public static ConversionTable fromBigInteger(final BigInteger bigInteger) {
        final String binaryValue = bigInteger.toString(2);
        final String octalValue = bigInteger.toString(8);
        final String hexValue = bigInteger.toString(16);
        final ConversionTable table = new ConversionTable(bigInteger, binaryValue,
            octalValue, hexValue);
        return table;
    }
}
