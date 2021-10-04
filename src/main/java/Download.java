import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Download {

  public static void main(String[] args) throws IOException {

    String input = args[1];
    Download download = new Download();
    writer(input, download.readURL(new URL(args[0])));


  }

  public String readURL(URL url) {
    String line = " ";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

      while ((line = reader.readLine()) != null) {
        System.out.println(line);
        stringBuilder.append(line).append("\n");

      }
      reader.close();
    } catch (Exception ex) {
      System.out.println(ex);
    }


    return new String(stringBuilder);
  }

  public static void writer(String fileName, String s) throws IOException {

    FileWriter fileWriter = new FileWriter(fileName);

    fileWriter.write(s);
    fileWriter.close();

  }
}
