package Commands.Modules;
import Config.BotConfig;
import Config.BotUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PublicConfession extends ListenerAdapter {
    private PrintWriter logWriter;
    private final EmbedBuilder embedBuilder;
    private int confessionCounter;
    private MessageChannel confessionChannel;
    private String confession;
    private SlashCommandInteractionEvent event;

    public PublicConfession() {
        embedBuilder = new EmbedBuilder();
        final File logFileName = new File("log/public-confession-log.txt");
        logFileName.getParentFile().mkdirs();
        try {
            logWriter = new PrintWriter(new FileWriter(logFileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onConfessionCommand(String confession, SlashCommandInteractionEvent event){
        this.event = event;
        confessionChannel = BotConfig.getConfessionChannel();
        confessionCounter = BotConfig.getConfessionCounter();
        this.confession = confession;
        if (BotConfig.isPublicConfessionIsEnabled()) {
            confessionSender();
            confessionLog();
        }
    }

    private void confessionSender(){
        confessionCounter ++;
        confessionChannel.sendMessageEmbeds(embedBuilder
                .setTitle("Confession " + confessionCounter)
                .setThumbnail("https://i.pinimg.com/originals/68/07/39/680739eaa243c983e7d345a23a6269d8.jpg")
                .setDescription(confession)
                .setFooter("Confess Something Using The /Public-Confession")
                .build()).queue();
        BotConfig.setConfessionCounter(confessionCounter);
    }

    private void confessionLog(){
        String log = confessionLogFormatter();
        logWriter.println(log);
        logWriter.flush();
    }

    private String confessionLogFormatter() {
        return (String.format("[%s][%s][%s:] %s %s %s %s\n",
                event.getGuild().getName(),
                BotUtils.getCurrentTime(),
                event.getChannel().getName(),
                event.getMember().getUser().getId(),
                event.getMember().getUser().getName(),
                confession,
                "Confession " + confessionCounter));
    }
    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        super.onShutdown(event);
        logWriter.close();
    }
}
