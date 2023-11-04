package EventListeners.OnMessageReceiveModules;
import Config.BotConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class MessageCommands {
    public void checkForChtCmds(MessageReceivedEvent event) {
        final String prefix = "!";
        String alice = ""; //UserID
        String message = event.getMessage().getContentDisplay().toUpperCase();
        if (event.getAuthor().getId().equals(BotConfig.getOwnerID()) || event.getAuthor().getId().equals(alice)) {
            switch (message) {
                case (prefix + "TOGGLEMSGSTK") -> {
                    if (BotConfig.isMessageStalkerIsEnabled()) {
                        BotConfig.setMessageStalker(false);
                        event.getChannel().sendMessage("Stalker Disabled").queue();
                    }
                    else {
                        BotConfig.setMessageStalker(true);
                        event.getChannel().sendMessage("Stalker Enabled").queue();
                    }
                }
                case (prefix + "TOGGLEMSGLOG") -> {
                    if (BotConfig.isMessageLoggerIsEnabled()) {
                        BotConfig.setMessageLogger(false);
                        event.getChannel().sendMessage("Logger Disabled").queue();
                    }
                    else {
                        BotConfig.setMessageLogger(true);
                        event.getChannel().sendMessage("Logger Enabled").queue();
                    }
                }
                case (prefix + "TOGGLEMSGRES") -> {
                    if (BotConfig.isMessageResponderIsEnabled()) {
                        BotConfig.setMessageResponder(false);
                        event.getChannel().sendMessage("Responder Disabled").queue();
                    }
                    else {
                        BotConfig.setMessageResponder(true);
                        event.getChannel().sendMessage("Responder Enabled").queue();
                    }
                }
                case (prefix + "TOGGLEPUBCON") -> {
                    if (BotConfig.isPublicConfessionIsEnabled()) {
                        BotConfig.setPublicConfession(false);
                        event.getChannel().sendMessage("Public Confessions Disabled").queue();
                    }
                    else {
                        BotConfig.setPublicConfession(true);
                        event.getChannel().sendMessage("Public Confessions Enabled").queue();
                    }
                }
                case (prefix + "SHOWSTATUS") -> event.getChannel().sendMessageEmbeds(showStatusEmbedBuilder()).queue();
            }
        }
    }
    public static MessageEmbed showStatusEmbedBuilder(){
        EmbedBuilder embBuilder = new EmbedBuilder();
        embBuilder.setTitle("Module Status");
        embBuilder.setDescription("Message Responder " + BotConfig.isMessageResponderIsEnabled() + "\n" + "Message Logger " + BotConfig.isMessageLoggerIsEnabled() + "\n" +
                "Public Confessions " + BotConfig.isPublicConfessionIsEnabled() + "\n" + "Message Stalker " + BotConfig.isMessageStalkerIsEnabled() + "\n" + "Stalk Victim 1 "
        + BotConfig.getStalkID());
        return embBuilder.build();
    }
}
