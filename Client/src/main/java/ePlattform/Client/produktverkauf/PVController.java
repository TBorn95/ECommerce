package ePlattform.Client.produktverkauf;

import ePlattform.Client.connection.HttpConnection;
import ePlattform.Client.domainObjects.Produkt;
import ePlattform.Client.helperClasses.Gson;
import ePlattform.Client.login.LoggedInUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@FxmlView("PvView.fxml")
public class PVController {

    private FxWeaver weaver;

    public PVController(FxWeaver weaver) {
        this.weaver = weaver;
    }

    @FXML
    private TextField preisTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField beschreibungTf;
    @FXML
    private Button uploadBtn;

    @FXML
    public void uploadProdukt() throws IOException {
        LoggedInUser.getNutzer().setAdressen(null);
        Produkt produkt = new Produkt(nameTf.getText(), beschreibungTf.getText(), Double.parseDouble(preisTf.getText()), null, null, null
                , LoggedInUser.getNutzer());
        String uri = new URIBuilder().setHost("localhost").setScheme("http").setPort(8080).setPath("/produkt").toString();
        produkt = HttpConnection.request(HttpConnection.Type.POST, uri, Gson.gson.toJson(produkt, Produkt.class), HttpConnection.ContentType.JSON, Produkt.class);
    }

}
