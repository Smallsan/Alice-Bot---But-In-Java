package Features;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;


public class CryptoAPI {
    private static final String API_KEY = ""; //Coin Market Cap Api Key
    private static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    public static String getCryptoPrice(String cryptoSymbol){
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
            try {

                URI uri = new URIBuilder(API_URL)
                        .addParameter("symbol", cryptoSymbol)
                        .build();

                HttpGet request = new HttpGet(uri);
                request.setHeader("X-CMC_PRO_API_KEY", API_KEY);

                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request);

                JSONObject jsonResponse = new JSONObject(EntityUtils.toString(response.getEntity()));

                JSONObject data = jsonResponse.getJSONObject("data");
                JSONObject quote = data.getJSONObject(cryptoSymbol).getJSONObject("quote");
                double price = quote.getJSONObject("USD").getDouble("price");
                String formattedPrice = decimalFormat.format(price);
                response.close();
                httpClient.close();


                if (formattedPrice.equals("0")) {
                    decimalFormat.applyPattern("0.################");
                    return ("The Current Price of 1 " + cryptoSymbol + " is $" + decimalFormat.format(price));
                }

                return ("The Current Price of 1 " + cryptoSymbol + " is $" + formattedPrice);

            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                return (cryptoSymbol + " Not Found");
            }
        return ("Failed To Get Crypto Pair Price");
        }
    }



