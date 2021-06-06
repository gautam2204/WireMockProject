import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockExample {

  WireMockServer wireMockServer;

  public WiremockExample(WireMockServer wireMockServer) {
    this.wireMockServer = wireMockServer;
  }

  public void creatrStub() {
    wireMockServer = new WireMockServer(new WireMockConfiguration());

    wireMockServer.start();
    stubFor(
        get(urlPathMatching("/users"))
            .willReturn(
                aResponse()
                    .withBodyFile("UserList.json")
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")));

  }

  public static void main(String[] args) {
      WiremockExample wiremockExample = new WiremockExample(new WireMockServer());
      wiremockExample.creatrStub();


  }
}
