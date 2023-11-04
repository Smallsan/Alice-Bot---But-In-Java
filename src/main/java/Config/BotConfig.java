package Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConfig extends ListenerAdapter {
    private static JDA jda;
    private static int confessionCounter;
    private static File configFile;
    private static MessageChannel logChannel;
    private static MessageChannel confessionChannel;
    private static Properties properties;
    private static String confessionChannelID;
    private static String logChannelID;
    private static String stalkID;
    private static String ownerID;
    private static String status;
    private static String activity;
    private static String customStatus;
    private static boolean messageResponderIsEnabled;
    private static boolean messageLoggerIsEnabled;
    private static boolean publicConfessionIsEnabled;
    private static boolean messageStalkerIsEnabled;

    @Override
    public void onReady(ReadyEvent event) {
        configFile = new File("config/config.txt");
        properties = new Properties();
        startConfig();
        jda = event.getJDA();
        confessionChannel = jda.getTextChannelById(confessionChannelID);
        logChannel = jda.getTextChannelById(logChannelID);
    }
    private void startConfig(){
        try {
            if (!configFile.exists()) {
                if (configFile.getParentFile().mkdirs())
                    System.out.println("Config file has been created");
                else
                    System.out.println("File already exists");
                properties.setProperty("Confession_Counter", "0");
                properties.setProperty("Confession_Channel_ID", "1089229124452749483");
                properties.setProperty("Log_Channel_ID", "967685973456609320");
                properties.setProperty("Owner_ID", "218119227142438914");
                properties.setProperty("Message_Responder", "true");
                properties.setProperty("Message_Logger", "true");
                properties.setProperty("Public_Confession", "false");
                properties.setProperty("Status", "Online");
                properties.setProperty("Activity", "Playing");
                properties.setProperty("Custom_Status", "With Your Heart");
                properties.setProperty("Message_Stalker", "false");
                properties.setProperty("Stalk_ID", "509683395224141827");
                properties.store(new FileOutputStream(configFile), null);
            }
                properties.load(new FileInputStream(configFile));
                messageResponderIsEnabled = Boolean.parseBoolean(properties.getProperty("Message_Responder"));
                messageLoggerIsEnabled = Boolean.parseBoolean(properties.getProperty("Message_Logger"));
                publicConfessionIsEnabled = Boolean.parseBoolean(properties.getProperty("Public_Confession"));
                messageStalkerIsEnabled = Boolean.parseBoolean(properties.getProperty("Message_Stalker"));
                confessionCounter = Integer.parseInt(properties.getProperty("Confession_Counter"));
                confessionChannelID = properties.getProperty("Confession_Channel_ID");
                logChannelID = properties.getProperty("Log_Channel_ID");
                status = properties.getProperty("Status").toUpperCase();
                activity = properties.getProperty("Activity").toUpperCase();
                customStatus = properties.getProperty("Custom_Status");
                ownerID = properties.getProperty("Owner_ID");
                stalkID = properties.getProperty("Stalk_ID");

        }
        catch (IOException ex) {
            System.out.println("Crash");
        }
    }
    private static void saveProperties() {
        try {
            properties.store(new FileOutputStream(configFile), null);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static boolean isMessageResponderIsEnabled(){
        return messageResponderIsEnabled;
    }
    public static boolean isMessageLoggerIsEnabled() {return messageLoggerIsEnabled;}
    public static boolean isPublicConfessionIsEnabled(){
        return publicConfessionIsEnabled;
    }
    public static boolean isMessageStalkerIsEnabled() {return messageStalkerIsEnabled; }
    public static MessageChannel getLogChannel(){return logChannel;}
    public static MessageChannel getConfessionChannel(){
        return confessionChannel;
    }
    public static int getConfessionCounter(){
        return confessionCounter;
    }
    public static String getStatus() { return status; }
    public static String getActivity() { return activity; }
    public static String getCustomStatus() { return customStatus; }
    public static String getOwnerID() { return ownerID; }
    public static String getStalkID() { return stalkID; }
    public static void setConfessionCounter(int confessionCounter){
        properties.setProperty("Confession_Counter", String.valueOf(confessionCounter));
        saveProperties();
        BotConfig.confessionCounter = confessionCounter;
    }
    public static void setMessageResponder(boolean messageResponderIsEnabled){
        properties.setProperty("Message_Responder", String.valueOf(messageResponderIsEnabled));
        saveProperties();
        BotConfig.messageResponderIsEnabled = messageResponderIsEnabled;
    }
    public static void setMessageStalker (boolean messageStalkerIsEnabled){
        properties.setProperty("Message_Stalker", String.valueOf(messageStalkerIsEnabled));
        saveProperties();
        BotConfig.messageStalkerIsEnabled = messageStalkerIsEnabled;
    }
    public static void setPublicConfession(boolean publicConfessionIsEnabled){
        properties.setProperty("Public_Confession", String.valueOf(publicConfessionIsEnabled));
        saveProperties();
        BotConfig.publicConfessionIsEnabled = publicConfessionIsEnabled;
    }
    public static void setMessageLogger(boolean messageLoggerIsEnabled) {
        properties.setProperty("Message_Logger", String.valueOf(messageLoggerIsEnabled));
        saveProperties();
        BotConfig.messageLoggerIsEnabled = messageLoggerIsEnabled;
    }
    public static void setStatus(String status) {
        properties.setProperty("Status", String.valueOf(status));
        saveProperties();
        BotConfig.status = status;
    }
    public static void setCustomStatus(String customStatus){
        properties.setProperty("Custom_Status", String.valueOf(customStatus));
        saveProperties();
        BotConfig.customStatus = customStatus;
    }
    public static void setActivity(String activity) {
        properties.setProperty("Activity", String.valueOf(activity));
        saveProperties();
        BotConfig.activity = activity;
    }
    public static void setLogChannel(String logChannelID) {
        properties.setProperty("Log_Channel_ID", String.valueOf(logChannelID));
        saveProperties();
        BotConfig.logChannelID = logChannelID;
        logChannel = jda.getTextChannelById(logChannelID);
    }
    public static void setConfessionChannel(String confessionChannelID) {
        properties.setProperty("Confession_Channel_ID", String.valueOf(confessionChannelID));
        saveProperties();
        BotConfig.confessionChannelID = confessionChannelID;
        confessionChannel = jda.getTextChannelById(confessionChannelID);
    }

    public static void setStalkID(String stalkID) {
        properties.setProperty("Stalk_ID", String.valueOf(stalkID));
        saveProperties();
        BotConfig.stalkID = stalkID;
    }
}

