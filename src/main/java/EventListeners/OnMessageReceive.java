package EventListeners;
import EventListeners.OnMessageReceiveModules.MessageCommands;
import EventListeners.OnMessageReceiveModules.MessageLogger;
import EventListeners.OnMessageReceiveModules.MessageResponder;
import EventListeners.OnMessageReceiveModules.MessageStalker;
import Features.DanbooruAPI;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class OnMessageReceive extends ListenerAdapter {
    private final MessageLogger msgLog;
    private final MessageResponder msgResp;
    private final MessageCommands chtCmd;
    private final MessageStalker msgStk;
    private final DanbooruAPI danbooruAPI;
    public OnMessageReceive(){
        msgLog = new MessageLogger();
        msgResp = new MessageResponder();
        chtCmd = new MessageCommands();
        msgStk = new MessageStalker();
        danbooruAPI = new DanbooruAPI();

    }
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
            if (!event.getAuthor().isBot() && event.isFromGuild()){
                    danbooruAPI.catGirl(event);
                    chtCmd.checkForChtCmds(event);
                    msgLog.logMessage(event);
                    msgResp.respondToMessage(event);
                    msgStk.checkMessageSender(event);
                }
            }
    }



