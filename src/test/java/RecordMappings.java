import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class RecordMappings {
    WireMockServer wireMockServer;
    public void createRecording()
    {
         wireMockServer = new WireMockServer(8081);
        wireMockServer.start();

        wireMockServer.startRecording("https://reqres.in");
        test();
        test1();
        printMappings();
        wireMockServer.stop();

    }

    private void test1() {
        Response response = RestAssured.given()
                .baseUri("http://localhost:8081")
                .get("/api/users/2")
                .then()
                .extract()
                .response();
    }

    private void printMappings() {
        SnapshotRecordResult snapshotRecordResult=wireMockServer.stopRecording();
        List<StubMapping> mappings = snapshotRecordResult.getStubMappings();
    mappings.forEach(System.out::println);
    }

    private void test() {
        Response response = RestAssured.given()
                .baseUri("http://localhost:8081")
                .queryParam("page", "2")
                .get("/api/users")
                .then()
                .extract()
                .response();

    }

    public static void main(String[] args) {
    RecordMappings recordMappings= new RecordMappings();
    recordMappings.createRecording();
  }
}
