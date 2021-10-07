import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class Download {

  public static void main(String[] args) throws IOException {

    String input = args[1];
    Download download = new Download();
    final byte[] s = download.readURL(new URL(args[0]));
    writer(input, s);


  }

  public byte[] readURL(URL url) throws IOException {

    try {
      byte[] buffer = IOUtils.toByteArray(url);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(buffer.length);

      int off = 512000;
      int len = 512000;

      byte [] bufferOutput = new byte[buffer.length];

      while (byteArrayOutputStream.size() != -1) {

        byteArrayOutputStream.write(bufferOutput, off, len);


        System.out.println("Скачано - " + Arrays.toString(bufferOutput) + " кілобайт");


      }

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
