package de.axelspringer.ideas.flipdots.magic.metrics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.axelspringer.ideas.flipdots.FlipdotProperties;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Datadog {

    private final static Logger LOG = LoggerFactory.getLogger(Datadog.class);

    private final static String QUERY = "https://app.datadoghq.com/api/v1/query?api_key=%s&application_key=%s&query=%s&from=%s&to=%s";


    public long numberOfLogins() {

        String apiKey = FlipdotProperties.INSTANCE.getDataDogApiKey();
        String appKey = FlipdotProperties.INSTANCE.getDataDogAppKey();
        String query = FlipdotProperties.INSTANCE.getDataDogQueryLogins();
        String from = "" + LocalDateTime.now().toLocalDate().atStartOfDay().toEpochSecond(ZoneId.of("Europe/Berlin").getRules().getOffset(Instant.now()));
        String to = "" + Instant.now().toEpochMilli() / 1000;

        String url = String.format(QUERY, apiKey, appKey, query, from, to);
        return getLastTimeseriesValue(url);

    }


    public long numberOfSignups() {

        String apiKey = FlipdotProperties.INSTANCE.getDataDogApiKey();
        String appKey = FlipdotProperties.INSTANCE.getDataDogAppKey();
        String query = FlipdotProperties.INSTANCE.getDataDogQuerySignups();
        String from = "" + LocalDateTime.now().toLocalDate().atStartOfDay().toEpochSecond(ZoneId.of("Europe/Berlin").getRules().getOffset(Instant.now()));
        String to = "" + Instant.now().toEpochMilli() / 1000;

        String url = String.format(QUERY, apiKey, appKey, query, from, to);
        return getLastTimeseriesValue(url);

    }

    private long getLastTimeseriesValue(String url) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String resultString = EntityUtils.toString(entity);
                if (response.getStatusLine().getStatusCode() > 200) {
                    LOG.warn(resultString);
                    throw new RuntimeException("Could not read answer from TEAM MOOD.");
                } else {
                    JsonParser jsonParser = new JsonParser();
                    JsonArray pointList = jsonParser.parse(resultString)
                            .getAsJsonObject().get("series")
                            .getAsJsonArray().get(0)
                            .getAsJsonObject().get("pointlist")
                            .getAsJsonArray();

                    JsonElement lastPoint = pointList.get(pointList.size() - 1);
                    return lastPoint.getAsJsonArray().get(1).getAsLong();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
