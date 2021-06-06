import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import com.github.tomakehurst.wiremock.standalone.MappingsSource;

public class RunMappings {
    WireMockServer wireMockServer;
    private int port;
    String filePath;

    public RunMappings(int port,String filePath) {
        this.port = port;
        this.filePath=filePath;
    }


    public void  deployMappingsOnWiremock()
    {
        wireMockServer = new WireMockServer(new WireMockConfiguration().port(port).notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
    System.out.println("WireMock Server up and running with below \nport="+wireMockServer.port()+
            "\ntotal mappings="+wireMockServer.getStubMappings().size());
    }

  public static void main(String[] args) {
    RunMappings runMappings=new RunMappings(8081,"resources");
    runMappings.deployMappingsOnWiremock();
  }
}
