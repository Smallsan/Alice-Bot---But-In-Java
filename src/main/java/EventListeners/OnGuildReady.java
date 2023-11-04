package EventListeners;
import Commands.LoadSlashCommands;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class OnGuildReady extends ListenerAdapter {
    private final LoadSlashCommands lsc;
    public OnGuildReady(){
        lsc = new LoadSlashCommands();
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        lsc.upsertCommands(event);
    }
}
