import Config.BotConfig;
import EventListeners.OnGuildReady;
import EventListeners.OnMessageReceive;
import Commands.OnSlashCommands;
import EventListeners.OnReady;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class AliceBot{
    public static void main(String[] args) {
            botStart();
        };
    private static void botStart() {
        String token = ("");
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.addEventListeners(new BotConfig(), new OnMessageReceive(), new OnReady(), new OnSlashCommands(), new OnGuildReady());

        try {
            JDA jda = builder.build();
            jda.awaitReady();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

