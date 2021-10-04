import java.io.*;
import java.net.URL;

public class Download {

  public static void main(String[] args) throws IOException {

    String input = args[1];
    Download download = new Download();
    writer(input, download.readURL(new URL(args[0])));


  }

  public FileInputStream readURL(URL url) throws FileNotFoundException {
    //String line = " ";
    int line;
    StringBuilder stringBuilder = new StringBuilder();

    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(String.valueOf(url));

      while ((line = fileInputStream.read()) != -1) {
        System.out.println(line);
        stringBuilder.append(line).append("\n");

      }
      fileInputStream.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }


    return stringBuilder;
  }

  public static void writer(String fileName, FileInputStream s) throws IOException {

    try {
      byte[] buffer = new byte[s.available()];
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      fileOutputStream.write(buffer);
      fileOutputStream.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }
}
