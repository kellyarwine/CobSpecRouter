import http.io.Io;
import http.io.SystemIo;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException {
    Io io = new SystemIo();
    new Menu().display(io);
  }
}
