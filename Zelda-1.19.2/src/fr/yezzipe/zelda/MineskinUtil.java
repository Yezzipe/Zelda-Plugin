package fr.yezzipe.zelda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MineskinUtil {
    public static String[] getSkin(String skin) {
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://5.135.160.189:8000/get?skin=" + skin))
		.timeout(Duration.of(2L, ChronoUnit.SECONDS)).GET().build();
	try {
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	    JsonObject o = (JsonObject) (new Gson()).fromJson(response.body(), JsonObject.class);
	    JsonElement value = o.get("value");
	    JsonElement signature = o.get("signature");
	    String[] values = { (value != null) ? value.getAsString() : null,
		    (signature != null) ? signature.getAsString() : null };
	    return values;
	} catch (IOException e) {
	    e.printStackTrace();
	    String[] values = new String[2];
	    return values;
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    String[] values = new String[2];
	    return values;
	}
    }
}
