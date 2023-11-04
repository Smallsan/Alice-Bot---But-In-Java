package Features;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DanbooruAPI {
    public void catGirl(MessageReceivedEvent event) {
            TextChannel channel = event.getGuildChannel().asTextChannel();
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!catgirl")) {
                try {
                    URL url = new URL("https://danbooru.donmai.us/posts.json?tags=catgirl+-rating:explicit&random=true");
                    getConnection(channel, url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void catGirl(SlashCommandInteractionEvent event, String tag) {
            TextChannel channel = event.getGuildChannel().asTextChannel();
                try {
                    URL url = new URL("https://danbooru.donmai.us/posts.json?tags=" + URLEncoder.encode(tag, StandardCharsets.UTF_8) + "+-rating:explicit&random=true");
                    getConnection(channel, url);
                    event.reply("Sending " + tag).queue();
                }
                catch (IOException e) {
                    e.printStackTrace();
            }
                catch (JSONException e) {
                    event.reply("Cannot Find Tags").setEphemeral(true).queue();
                }

        }

    private void getConnection(TextChannel channel, URL url) throws IOException, JSONException  {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String imageUrl = jsonObject.getString("file_url");
                channel.sendMessage(imageUrl).queue();
            }
        } else {
            System.out.println("HTTP request failed with response code: " + responseCode);
            throw new JSONException("Invalid Tag");
        }
    }
}
