package EventListeners.OnMessageReceiveModules;
import Config.BotConfig;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageStalker {
    MessageLogger msgLog;
    MessageReceivedEvent event;

    public MessageStalker(){
        msgLog =  new MessageLogger();
    }

    public void checkMessageSender(MessageReceivedEvent event) {
        this.event = event;
        if (BotConfig.isMessageStalkerIsEnabled()) {
            informOwner();
        }
    }
    private void informOwner() {
        User owner = event.getJDA().getUserById(BotConfig.getOwnerID());
        if (owner != null && event.getAuthor().getId().equals(BotConfig.getStalkID())) {
            owner.openPrivateChannel()
                    .queue(privateChannel -> privateChannel.sendMessageEmbeds(MessageUtils.logEmbedFormatter(event)).queue());
        }
    }
}
