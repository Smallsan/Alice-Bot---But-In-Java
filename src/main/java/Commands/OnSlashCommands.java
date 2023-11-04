package Commands;
import Commands.Modules.PublicConfession;
import Commands.Modules.SaySomething;
import Config.BotConfig;
import EventListeners.OnMessageReceiveModules.MessageCommands;
import Features.CryptoAPI;
import Features.DanbooruAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

public class OnSlashCommands extends ListenerAdapter {
    PublicConfession publicConfession;
    DanbooruAPI danbooruAPI;
    public OnSlashCommands(){
        publicConfession = new PublicConfession();
        danbooruAPI = new DanbooruAPI();
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        JDA jda = event.getJDA();
        long smallSan = 218119227142438914L;
        String command = event.getName();
        if (event.getUser().getIdLong() == smallSan) {
            switch (command) {
                case ("toggle-logger") -> {
                    if (BotConfig.isMessageLoggerIsEnabled()){
                        BotConfig.setMessageLogger(false);
                        event.reply("MessageLogger has been disabled").queue();
                    }
                    else {
                        BotConfig.setMessageLogger(true);
                        event.reply("MessageLogger has been enabled").queue();
                    }
                }

                case ("toggle-responder") -> {
                    if (BotConfig.isMessageResponderIsEnabled()){
                        BotConfig.setMessageResponder(false);
                        event.reply("Message responder has been disabled").queue();
                    }
                    else {
                        BotConfig.setMessageResponder(true);
                        event.reply("Message responder has been enabled").queue();
                    }
                }

                case ("toggle-public-confessions") -> {
                    if (BotConfig.isPublicConfessionIsEnabled()){
                        BotConfig.setPublicConfession(false);
                        event.reply("Public confessions Has Been Disabled").queue();
                    }
                    else {
                        BotConfig.setPublicConfession(true);
                        event.reply("Public confessions has been enabled").queue();
                    }
                }

                case ("toggle-stalker") -> {
                    if (BotConfig.isMessageStalkerIsEnabled()) {
                        BotConfig.setMessageStalker(false);
                        event.reply("Stalker Disabled").queue();
                    }
                    else {
                        BotConfig.setMessageStalker(true);
                        event.reply("Stalker Enabled").queue();
                    }
                }

                case ("show-status") -> event.replyEmbeds(MessageCommands.showStatusEmbedBuilder()).queue();

                case ("change-status") -> {
                    String status = getAsString(event.getOption("status")).toUpperCase().trim();
                    OnlineStatus onlineStatus;
                    switch (status) {
                        case ("DND") -> {
                            onlineStatus = OnlineStatus.DO_NOT_DISTURB;
                            BotConfig.setStatus(status);
                        }
                        case ("ONLINE") -> {
                            onlineStatus = OnlineStatus.ONLINE;
                            BotConfig.setStatus(status);
                        }
                        case ("IDLE") -> {
                            onlineStatus = OnlineStatus.IDLE;
                            BotConfig.setStatus(status);
                        }
                        case ("INVISIBLE") -> {
                            onlineStatus = OnlineStatus.INVISIBLE;
                            BotConfig.setStatus(status);
                        }
                        default -> {
                            event.reply("Invalid status").queue();
                            return;
                        }
                    }
                    jda.getPresence().setStatus(onlineStatus);
                    event.reply("Status changed").queue();
                }

                case ("change-presence") -> {
                    String customStatus = getAsString(event.getOption("customstatus"));
                    String activityInput = getAsString(event.getOption("activity")).toUpperCase().trim();
                    Activity activity;
                    switch (activityInput) {
                        case ("PLAYING") ->
                            activity = Activity.playing(customStatus);

                        case ("COMPETING") ->
                            activity = Activity.competing(customStatus);

                        case ("LISTENING") ->
                            activity = Activity.listening(customStatus);

                        case ("WATCHING") ->
                            activity = Activity.watching(customStatus);

                        default -> {
                            event.reply("Invalid activity").queue();
                            return;
                        }
                    }
                    jda.getPresence().setActivity(activity);
                    BotConfig.setActivity(activityInput);
                    BotConfig.setCustomStatus(customStatus);
                    event.reply("Presence changed").queue();
                }

                case ("set-logchannel") -> {
                    OptionMapping option = event.getOption("logchannelid");
                    if (option == null) {
                        BotConfig.setLogChannel(event.getChannel().getId());
                    } else {
                        BotConfig.setLogChannel(option.getAsString());
                    }
                    event.reply("Log Channel Set").queue();
                }

                case ("set-confessionchannel") -> {
                    OptionMapping option = event.getOption("confessionchannelid");
                    if (option == null) {
                        BotConfig.setConfessionChannel(event.getChannel().getId());
                    } else {
                        BotConfig.setConfessionChannel(option.getAsString());
                    }
                    event.reply("Confession Channel Set").queue();
                }

                case ("stalk") -> {
                    String stalkID = getAsString(event.getOption("stalkid"));
                    BotConfig.setStalkID(stalkID);
                    event.reply("StalkID Set").queue();
                }

                case ("get-crypto-price") -> {
                    String crypto = getAsString(event.getOption("crypto")).toUpperCase().trim();
                    event.reply(CryptoAPI.getCryptoPrice(crypto)).queue();
                }

                case ("danbooru") -> {
                    String tags = getAsString(event.getOption("tags")).trim();
                    danbooruAPI.catGirl(event, tags);
                }

            }

            if (event.getName().equals("public-confession")) {
                event.reply("Confession sent").setEphemeral(true).queue();
                String confession = getAsString(event.getOption("confession"));
                publicConfession.onConfessionCommand(confession, event);
            }

            if (event.getName().equals("yandere")) {
                event.reply(SaySomething.SayIt()).queue();
            }
        }
    }

    private String getAsString(OptionMapping optionMapping) {
        String optionMappingString = "";
        try {
            optionMappingString = optionMapping.getAsString();
        }
        catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return optionMappingString;
    }
}
