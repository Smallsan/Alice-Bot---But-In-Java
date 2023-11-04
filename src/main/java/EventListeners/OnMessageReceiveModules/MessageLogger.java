package EventListeners.OnMessageReceiveModules;
import Config.BotConfig;
import Config.BotUtils;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class MessageLogger extends ListenerAdapter {
    private PrintWriter logWriter;
    private MessageReceivedEvent event;
    private MessageChannel logChannel;

    // Constructor
    public MessageLogger() {
        createLogWriter();
    }

    // Create the log writer and set the file name
    private void createLogWriter() {
        String logFolderPath = "logs";
        File logFolder = new File(logFolderPath);
        logFolder.mkdirs();

        String logFileName = String.format("%s/message-log_%s.txt", logFolderPath, getCurrentDate());
        try {
            logWriter = new PrintWriter(new FileWriter(logFileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the current date in the desired format
    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }

    // Initiator
    public void logMessage(MessageReceivedEvent event) {
        logChannel = BotConfig.getLogChannel();
        this.event = event;
        if (BotConfig.isMessageLoggerIsEnabled()) {
            messageLoggerStart();
        }
    }

    // Logs Messages And Sends Them In Discord
    private void messageLoggerStart() {
        if (logChannel != null) {
            String log = logFileFormatter();
            MessageUtils.logEmbedFormatter(event);
            logChannel.sendMessageEmbeds(MessageUtils.logEmbedFormatter(event)).queue();
            logWriter.println(log);
            logWriter.flush();
        }
    }

    // Formats Logs In TextFile
    private String logFileFormatter() {
        return (String.format("[%s][%s][%s] %s: %s %s\n",
                event.getGuild().getName(),
                BotUtils.getCurrentTime(),
                event.getChannel().getName(),
                event.getAuthor().getId(),
                event.getAuthor().getAsTag(),
                event.getMessage()));
    }

    // Closes The TextFile On Bot Shutdown
    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        super.onShutdown(event);
        logWriter.close();
    }
}

