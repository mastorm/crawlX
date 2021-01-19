import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Webpage {
    private final URL url;
    private boolean isDownloaded = false;
    private static final String URL_MATCHING_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public Webpage(URL url) {
        this.url = url;
    }

    private String response;

    public boolean download() {
        try {
            // we are using the old HttpUrlConnection over java 11`s HttpClient for the sake of
            // being able to use streams, which i need to learn for my exam
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // this fires the request
            InputStream responseStream = con.getInputStream();
            byte[] buffer = responseStream.readAllBytes();
            // A more proper way would be to check the content type prop
            // https://www.w3.org/International/articles/http-charset/index.en.html
            // According to the document above, the standard charset is ISO_8859_1
            response = new String(buffer, StandardCharsets.ISO_8859_1);

            isDownloaded = true;
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public URL getUrl() {
        return url;
    }

    public String getResponse() {
        return response;
    }
}
