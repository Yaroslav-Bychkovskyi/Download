import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Download {

  public static void main(String[] args) throws IOException {

    String input = args[1];
    Download download = new Download();
    final byte[] s = download.readURL(new URL(args[0]));
    writer(input, s);


  }

  public byte[] readURL(URL url) {
    byte[] buffer;
    try {
      final var inputStream = url.openStream();
      buffer = new byte[inputStream.available()];
      inputStream.read(buffer);
      return buffer;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      throw new RuntimeException(ex);
    }
  }

  public static void writer(String fileName, byte[] s) throws IOException {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      fileOutputStream.write(s);
      fileOutputStream.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }
}
