import http.SystemRouter;
import http.io.Io;
import http.server.ServerRunnable;
import router.Router;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Menu {
  private static String DEFAULT_PORT = "5000";
  private static String DEFAULT_PUBLIC_DIRECTORY_PATH = "public/";
  private static String DEFAULT_ENV = "production";
  private static String DEFAULT_ROUTES_FILE_PATH = "routes.csv";
  private static String DEFAULT_HTACCESS_FILE_PATH = ".htaccess";
  private static String DEFAULT_WORKING_DIRECTORY_PATH = new File(new File(System.getProperty("user.dir")).getParent(), "cob_spec").toString();
  private static String DEFAULT_MOCK_REQUESTS_FILE_PATH = "";
  private Io io;
  private SystemRouter router;
  public ServerRunnable serverRunnable;
  public HashMap<String, String> serverConfig;

  public void display(Io io) throws IOException, InterruptedException {
    this.io = io;
    router = new Router();

    io.out("Ninja Server Menu");
    io.out("----------------------");
    io.out("Type \"help\" to see a list of available commands.");

    while (true) {
      String input = io.in();

      if (input.startsWith("start")) {
        if (setAndValidateServerConfig(input))
        start();
      }
      else if (input.startsWith("status"))
        status();
      else if (input.startsWith("stop")) {
        stop();
      }
      else if (input.startsWith("exit")) {
        stop();
        break;
      }
      else
        displayHelpText();
    }
  }

  public void start() throws IOException {
    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService serverThreadPool = Executors.newFixedThreadPool(cores);
    serverRunnable = new ServerRunnable(serverConfig, router);
    serverThreadPool.submit(serverRunnable);
    waitForServerStartup();
    serverThreadPool.shutdown();
  }

  public void status() throws IOException {
    if (serverRunnable == null || serverRunnable.httpServerSocket == null || serverRunnable.httpServerSocket.isClosed())
      io.out("Ninja Server is not running.");
    else
      io.out("Ninja Server is running on port " + serverConfig.get("port") + ".");
  }

  public void stop() throws IOException {
    if (serverRunnable != null && serverRunnable.httpServerSocket != null && serverRunnable.httpServerSocket.isClosed() == false) {
      io.out("Ninja Server has been shut down.");
      serverRunnable.closeServerSocket();
    }
    else
      io.out("Ninja Server is not currently running.");
  }

  private void waitForServerStartup() {
    while (!serverRunnable.isReady) {
    }
  }

  private boolean setAndValidateServerConfig(String serverConfigString) throws IOException {
    setServerConfigHashMap(serverConfigString);
    return (validateServerConfig());
  }

  private void setServerConfigHashMap(String serverConfigString) {
    serverConfig = new HashMap<String, String>();
    String[] serverConfigArray = serverConfigString.split(" ");
    setPort(serverConfigArray);
    setPublicDirectory(serverConfigArray);
    setEnv(serverConfigArray);
    setRoutesFile(serverConfigArray);
    setHtAccessFile(serverConfigArray);
    setWorkingDirectory(serverConfigArray);
    setMockRequestsFile(serverConfigArray);
  }

  private boolean validateServerConfig() throws IOException {
    return
        validatePortAndCheckAvailability() &&
            validateEnv() &&
            validateWorkingDirectoryPath() &&
            validatePublicDirectoryPath() &&
            validateRoutesFilePath() &&
            validateHtAccessFilePath() &&
            validateMockRequestsFilePath();
  }

  public void setPort(String[] serverConfigArray) {
    int portIndex = Arrays.asList(serverConfigArray).indexOf("-p");
    String port = (portIndex == -1) ? DEFAULT_PORT : serverConfigArray[portIndex + 1];
    serverConfig.put("port", port);
  }

  public void setPublicDirectory(String[] serverConfigArray) {
    int publicDirectoryPathIndex = Arrays.asList(serverConfigArray).indexOf("-d");
    String publicDirectoryPath = (publicDirectoryPathIndex == -1) ? DEFAULT_PUBLIC_DIRECTORY_PATH : serverConfigArray[publicDirectoryPathIndex + 1];
    serverConfig.put("publicDirectoryPath", publicDirectoryPath);
  }

  public void setEnv(String[] serverConfigArray) {
    int envIndex = Arrays.asList(serverConfigArray).indexOf("-e");
    String env = (envIndex == -1) ? DEFAULT_ENV : serverConfigArray[envIndex + 1];
    serverConfig.put("env", env);
  }

  public void setRoutesFile(String[] serverConfigArray) {
    int routesFileIndex = Arrays.asList(serverConfigArray).indexOf("-r");
    String routesFilePath = (routesFileIndex == -1) ? DEFAULT_ROUTES_FILE_PATH : serverConfigArray[routesFileIndex + 1];
    serverConfig.put("routesFilePath", routesFilePath);
  }

  public void setHtAccessFile(String[] serverConfigArray) {
    int htAccessFileIndex = Arrays.asList(serverConfigArray).indexOf("-h");
    String htAccessFilePath = (htAccessFileIndex == -1) ? DEFAULT_HTACCESS_FILE_PATH : serverConfigArray[htAccessFileIndex + 1];
    serverConfig.put("htAccessFilePath", htAccessFilePath);
  }

  public void setWorkingDirectory(String[] serverConfigArray) {
    int workingDirectoryPathIndex = Arrays.asList(serverConfigArray).indexOf("-w");
    String workingDirectoryPath = (workingDirectoryPathIndex == -1) ? DEFAULT_WORKING_DIRECTORY_PATH : serverConfigArray[workingDirectoryPathIndex + 1];
    serverConfig.put("workingDirectoryPath", workingDirectoryPath);
  }

  private void setMockRequestsFile(String[] serverConfigArray) {
    int mockRequestsFileIndex = Arrays.asList(serverConfigArray).indexOf("-m");
    String mockRequestsFilePath = (mockRequestsFileIndex == -1) ? DEFAULT_MOCK_REQUESTS_FILE_PATH : serverConfigArray[mockRequestsFileIndex + 1];
    serverConfig.put("mockRequestsFilePath", mockRequestsFilePath);
  }

  private void displayHelpText() throws IOException {
    io.out("");
    io.out("Ninja Server Help Menu for CobSpec");
    io.out("-------------------------");
    io.out("Available Commands:");
    io.out(" start           Starts the server with CobSpec configurations.");
    io.out(" status          Lists the status of the server.");
    io.out(" stop            Stops the server.");
    io.out(" exit            Exits the application.");
    io.out(" help            Provides instructions and detailed information for each command.");
    io.out("");
    io.out("Starting the Server:");
    io.out(" start           Starts the server.  The application takes six optional parameters:");
    io.out("                 an environment setting; \"test\" or \"production\" (denoted by the \"-e\" flag)");
    io.out("                 a port number (denoted by the \"-p\" flag)");
    io.out("                 the absolute path to the working directory (denoted by the \"-w\" flag)");
    io.out("                 the relative path to the public directory (denoted by the \"-d\" flag)");
    io.out("                 the Routes .csv filename; file must exist in the root working directory (denoted by the \"-r\" flag)");
    io.out("                 the .htaccess .csv filename; file must exist in the root working directory (denoted by the \"-h\" flag)");
    io.out("                 the mock request .tsv filename; file must exist in the root working directory (denoted by the \"-m\" flag)");
    io.out("                 can hold one or more mock requests; used for unit-testing purposes");
    io.out("Default Server Configurations:");
    io.out(" start           [=<-e " + DEFAULT_ENV + ">]\n" +
        "                 [=<-p " + DEFAULT_PORT + ">]\n" +
        "                 [=<-w " + DEFAULT_WORKING_DIRECTORY_PATH + ">]\n" +
        "                 [=<-d " + DEFAULT_PUBLIC_DIRECTORY_PATH + ">]\n" +
        "                 [=<-r " + DEFAULT_ROUTES_FILE_PATH + ">]\n" +
        "                 [=<-h " + DEFAULT_HTACCESS_FILE_PATH + ">]\n" +
        "                 [=<-m " + DEFAULT_MOCK_REQUESTS_FILE_PATH + ">]");
  }

  private boolean validatePortAndCheckAvailability() throws IOException {
    try {
      int port = Integer.parseInt(serverConfig.get("port"));
      ServerSocket serverSocket = new ServerSocket(port);
      serverSocket.close();
      return true;
    }
    catch (IOException e) {
      io.out("Port " + serverConfig.get("port") + " is already in use.  Please try again.");
      return false;
    }
    catch (IllegalArgumentException e) {
      io.out("Port " + serverConfig.get("port") + " is not a valid port.  Please try again.");
      return false;
    }
  }

  private boolean validateEnv() throws IOException {
    String env = serverConfig.get("env");
    if (env.equals("production") || env.equals("test"))
      return true;
    else {
      io.out("Invalid \"env\" setting.  Please try again.");
      return false;
    }
  }

  private boolean validateWorkingDirectoryPath() throws IOException {
    String workingDirectoryPath = serverConfig.get("workingDirectoryPath");
    if (new File(workingDirectoryPath).exists())
      return true;
    else {
      io.out("The working directory does not exist.  Please try again.");
      return false;
    }
  }

  private boolean validatePublicDirectoryPath() throws IOException {
    String workingDirectoryPath = serverConfig.get("workingDirectoryPath");
    String publicDirectoryPath = serverConfig.get("publicDirectoryPath");
    if (new File(workingDirectoryPath, publicDirectoryPath).exists())
      return true;
    else {
      io.out("The public directory does not exist.  Please try again.");
      return false;
    }
  }

  private boolean validateRoutesFilePath() throws IOException {
    String workingDirectoryPath = serverConfig.get("workingDirectoryPath");
    String routesFilePath = serverConfig.get("routesFilePath");
    if (new File(workingDirectoryPath, routesFilePath).exists())
      return true;
    else {
      io.out("The routes file does not exist.  Please try again.");
      return false;
    }
  }

  private boolean validateHtAccessFilePath() throws IOException {
    String workingDirectoryPath = serverConfig.get("workingDirectoryPath");
    String htAccessFilePath = serverConfig.get("htAccessFilePath");
    if (new File(workingDirectoryPath, htAccessFilePath).exists())
      return true;
    else {
      io.out("The .htaccess file does not exist.  Please try again.");
      return false;
    }
  }

  private boolean validateMockRequestsFilePath() throws IOException {
    String workingDirectoryPath = serverConfig.get("workingDirectoryPath");
    String mockRequestsFilePath = serverConfig.get("mockRequestsFilePath");
    String env = serverConfig.get("env");
    if (new File(workingDirectoryPath, mockRequestsFilePath).exists() && !mockRequestsFilePath.equals(""))
      return true;
    else if (mockRequestsFilePath.equals("") && env.equals("production"))
      return true;
    else {
      io.out("The mock requests file does not exist.  Please try again.");
      return false;
    }
  }
}