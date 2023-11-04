package EventListeners;

import Config.BotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnReady extends ListenerAdapter {
    JDA jda;
    @Override
    public void onReady(ReadyEvent event) {
        jda = event.getJDA();
        System.out.println(event.getJDA().getSelfUser().getName() + " Is ready");
        setStatus();
        setActivity();
    }

    private void setStatus() {
        String status = BotConfig.getStatus();
        switch (status) {
            case ("DND") ->
                jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

            case ("ONLINE") ->
                jda.getPresence().setStatus(OnlineStatus.ONLINE);

            case ("IDLE") ->
                jda.getPresence().setStatus(OnlineStatus.IDLE);

            case ("INVISIBLE") ->
                jda.getPresence().setStatus(OnlineStatus.INVISIBLE);

        }
    }
    private void setActivity(){
        String activity = BotConfig.getActivity();
        String customStatus = BotConfig.getCustomStatus();
        switch (activity) {
            case ("PLAYING") ->
                jda.getPresence().setActivity(Activity.playing(customStatus));

            case ("COMPETING") ->
                jda.getPresence().setActivity(Activity.competing(customStatus));

            case ("LISTENING") ->
                jda.getPresence().setActivity(Activity.listening(customStatus));

            case ("WATCHING") ->
                jda.getPresence().setActivity(Activity.watching(customStatus));

        }
    }
}



