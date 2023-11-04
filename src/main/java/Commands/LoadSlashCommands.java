package Commands;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class LoadSlashCommands {

    public void upsertCommands(GuildReadyEvent event) {
        Guild guild = event.getGuild();
        guild.upsertCommand("toggle-logger", "Enables/Disables Message Logger").queue();

        guild.upsertCommand("toggle-responder", "Enables/Disables Message Responder").queue();

        guild.upsertCommand("toggle-public-confessions", "Enables/Disables Public Confessions").queue();

        guild.upsertCommand("toggle-stalker", "Enables/Disables Message Stalker").queue();

        guild.upsertCommand("show-status", "Shows module status").queue();

        guild.upsertCommand("yandere", "Throws a yandere line").queue();

        guild.upsertCommand("public-confession", "Confess something out in Public")
                .addOption(OptionType.STRING, "confession", "Confession",true).queue();

        guild.upsertCommand("change-status", "Change Status")
                .addOption(OptionType.STRING, "status", "Change Status (Online, Invisible, Idle, DND",true).queue();

        guild.upsertCommand("change-presence", "Change Presence")
                .addOption(OptionType.STRING, "activity", "Playing, Listening, Watching, Competing",true)
                .addOption(OptionType.STRING, "customstatus", "Custom Status",true).queue();

        guild.upsertCommand("set-logchannel", "Uses this channel as log channel")
                .addOption(OptionType.STRING, "logchannelid", "ChannelID").queue();

        guild.upsertCommand("set-confessionchannel", "Uses this channel as confession Channel")
                .addOption(OptionType.STRING, "confessionchannelid", "ChannelID").queue();

        guild.upsertCommand("stalk", "Stalk someone")
                .addOption(OptionType.STRING, "stalkid", "UserID", true).queue();

        guild.upsertCommand("get-crypto-price", "Gets crypto price in USD")
                .addOption(OptionType.STRING, "crypto", "Crypto Symbol ex.(BTC,ETH)", true).queue();

        guild.upsertCommand("danbooru", "Gets anime image")
                .addOption(OptionType.STRING, "tags", "Image Tags ex.(CatGirl)", true).queue();

    }
}
