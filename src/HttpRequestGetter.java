import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public final class HttpRequestGetter {
    public static String get(String url) {
        var httpClient = HttpClient.newHttpClient();
        var uri = URI.create(url);
        var httpRequest = HttpRequest.newBuilder(uri).GET().build();
        try {
            var responseBody = httpClient.send(httpRequest, BodyHandlers.ofString()).body();
            return responseBody;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
