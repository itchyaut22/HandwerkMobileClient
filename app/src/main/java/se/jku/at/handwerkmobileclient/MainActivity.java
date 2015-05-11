package se.jku.at.handwerkmobileclient;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.ManufacturerList;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    public static final String version = "v3";

    private Client client;

    @ViewById(R.id.button1)
    Button button1;

    private static String getBaseURIOnline() {
        return "http://itchyaut22.ddns.net:8080/HandwerkService/api";
    }

    @AfterInject
    public void test1() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        client = ClientBuilder.newClient().register(AndroidFriendlyFeature.class);
    }

    @Click(R.id.button1)
    public void Button1Click() {
        WebTarget service = client.target(getBaseURIOnline());
        service = service.path(version).path("manufacturers");
        Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String json = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        ManufacturerList list = new ManufacturerList();
        try {
            list = mapper.readValue(json, ManufacturerList.class);
            Log.d(MainActivity.class.getName(), json);
            for (Manufacturer m : list.getList()) {
                Log.d(MainActivity.class.getName(), m.toString());
            }
        } catch (Exception e) {
        }
    }

    public static class AndroidFriendlyFeature implements Feature {

        @Override
        public boolean configure(FeatureContext context) {
            context.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    addUnbindFilter(new Filter() {
                        @Override
                        public boolean matches(Descriptor d) {
                            String implClass = d.getImplementation();
                            return implClass.startsWith(
                                    "org.glassfish.jersey.message.internal.DataSource")
                                    || implClass.startsWith(
                                    "org.glassfish.jersey.message.internal.RenderedImage");
                        }
                    });
                }
            });
            return true;
        }
    }

}
