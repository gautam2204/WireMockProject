import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import java.io.File;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockExample {

  WireMockServer wireMockServer;
  int port;

  public WiremockExample(int port) {
    this.port = port;
      wireMockServer = new WireMockServer(new WireMockConfiguration().port(port).notifier(new ConsoleNotifier(true)));
      wireMockServer.start();
  }

  public void creatrStubforGet() {



    System.out.println("on Port for get = " + wireMockServer.port());
    stubFor(
        get(urlPathMatching("/users"))
            .willReturn(
                aResponse()
                    .withBodyFile("UserList.json")
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")));
  }

  public static void main(String[] args) throws Exception {
    WiremockExample wiremockExample = new WiremockExample(8080);
    wiremockExample.creatrStubforGet();
    wiremockExample.createStubForPost();
  }

  private void createStubForPost() throws Exception {

    File file = new File("src/test/resources/__files/requestBodyforAddData.json");
    String requestBody = readFileAsString(file);
    requestBody.replaceAll("[\r\n]", "");
    System.out.println("on Port = " + wireMockServer.port());
    stubFor(
        post(urlPathMatching("/data/user"))
            .withRequestBody(equalToJson(requestBody))
            .willReturn(
                aResponse()
                    .withBodyFile("responseBodyforAddData.json")
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")));
  }

  public static String readFileAsString(File file) throws Exception {
    return new String(Files.readAllBytes(file.toPath()));
  }
}
