package EventListeners.OnMessageReceiveModules;
import Config.BotConfig;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageResponder{
    public void respondToMessage(MessageReceivedEvent event) {
        if (BotConfig.isMessageResponderIsEnabled()) {
            Emoji nekoHeart = event.getGuild().getEmojiById(""); //Custom Emoji IDs
            Emoji nekoPrison = event.getGuild().getEmojiById("");
                if (event.getMember() != null) {
                    if (event.getMessage().getContentDisplay().toLowerCase().contains("alice") && nekoHeart != null) {
                        event.getMessage().addReaction(nekoHeart).queue();
                    }
                    if (event.getMessage().getContentDisplay().toLowerCase().contains("small") && nekoPrison != null) {
                        event.getMessage().addReaction(nekoPrison).queue();
                    }
                    if (event.getMember().isOwner()) {
                        if (event.getMessage().getContentDisplay().equalsIgnoreCase("How much money do i have left")) {
                            event.getChannel().sendMessage("You're broke get a job!!").queue();
                        }
                        if (event.getMessage().getContentDisplay().equalsIgnoreCase("Say it")) {
                            event.getChannel().sendMessage("Go die!").queue();
                        }
                    }
                }
            }
        }
    }


