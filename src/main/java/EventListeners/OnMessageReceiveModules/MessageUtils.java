package EventListeners.OnMessageReceiveModules;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.time.Instant;
import java.time.ZoneId;

public class MessageUtils {

    public static MessageEmbed logEmbedFormatter(MessageReceivedEvent event) {
        EmbedBuilder embBuilder = new EmbedBuilder();
            embBuilder.clear();
        Instant instant = Instant.now();
        User user = event.getAuthor();
        String messageLink = "[**Jump To Message**]" + "(https://discord.com/channels/" + event.getGuild().getId() + "/" + event.getChannel().getId() + "/" + event.getMessageId()
                + ")";
        String channelLink = "[#" + event.getChannel().getName() + "]" + "(https://discord.com/channels/" + event.getGuild().getId() + "/" + event.getChannel().getId() + ")";
        if (!event.getMessage().getAttachments().isEmpty())
            embBuilder.setImage(event.getMessage().getAttachments().get(0).getUrl());
        embBuilder.setThumbnail(user.getAvatarUrl());
        embBuilder.setTitle(event.getAuthor().getAsTag());
        embBuilder.setFooter("User ID: " + event.getAuthor().getId());
        embBuilder.setDescription("** Message sent in **" + channelLink + "\n" + messageLink + "\n" + event.getMessage().getContentDisplay());
        embBuilder.setTimestamp(instant.atZone(ZoneId.of("")).toInstant()); //Time Zone
        return (embBuilder.build());

    }
}
