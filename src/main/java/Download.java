import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Download {

  public static void main(String[] args) throws IOException {
    String input = "robots.txt";
    Download download = new Download();
    writer(input, download.readURL(new URL("https://www.google.com/")));


  }

  public String readURL(URL url) throws IOException {

    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);

        reader.close();
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }

    return null;
  }

  public static void writer(String fileName, String s) throws IOException {

    FileWriter fileWriter = new FileWriter(fileName);

    fileWriter.write(s);
    fileWriter.close();

  }
}
