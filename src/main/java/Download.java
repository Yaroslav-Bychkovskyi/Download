import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Download {

  public static final int KB_SIZE = 1024;
  public static final long MB_SIZE = KB_SIZE * 1024;
  public static final long GB_SIZE = MB_SIZE * 1024;
  public static final long TB_SIZE = GB_SIZE * 1024;

  public static void main(String[] args) throws IOException {

    String input = args[1];
    Download download = new Download();
    final byte[] s = download.readURL(new URL(args[0]));
    writer(input, s);


  }

  private byte[] handleResponse(HttpResponse httpResponse) throws IOException {
    long size = 0;
    return readStream(httpResponse.getEntity().getContent(), size);
  }

  public byte[] readURL(URL url) {
    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet request = new HttpGet(url.toURI());

      return client.execute(request, this::handleResponse);

    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public byte[] readStream(InputStream buffer, long size) {

    try  {

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      int len = 512 * KB_SIZE;

      byte[] bufferOutput = new byte[len];
      int read = buffer.read(bufferOutput, 0, len);
      System.out.println("Скачано - " + read + " кілобайт");

      long count = 0;

      while (read != -1) {

        byteArrayOutputStream.write(bufferOutput, 0, read);

        count = count + read;
        System.out.println("Скачано - " + formatSize(count));
        read = buffer.read(bufferOutput, 0, len);
      }

      return byteArrayOutputStream.toByteArray();
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

  public String formatSize(long size) {
    if (size > KB_SIZE && size < MB_SIZE) {
      double value = (double) size / KB_SIZE / KB_SIZE;
      String result = String.format("%.2f", value);
      return result + " кілобайт";
    }
    if (size < GB_SIZE) {
      double value = (double) size / KB_SIZE / KB_SIZE;
      String result = String.format("%.2f", value);
      return result + " мегабайт";
    }
    if (size < TB_SIZE) {
      double value = (double) size / KB_SIZE / KB_SIZE / KB_SIZE;
      String result = String.format("%.2f", value);
      return result + " гігабайт";
    }
    return size + " байт";
  }
}
