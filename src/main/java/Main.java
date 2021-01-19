import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        for(File root : File.listRoots()) {
            System.out.println(root.getAbsolutePath());
        }

    }
}
