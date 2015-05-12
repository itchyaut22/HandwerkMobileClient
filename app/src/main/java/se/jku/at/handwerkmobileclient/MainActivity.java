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
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceList;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.rest.impl.RestHelper;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    public static final String version = "v3";

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
    }

    @Click(R.id.button1)
    public void Button1Click() {
        final HandwerkResource handwerkResource = new HandwerkResourceImpl();

        ManufacturerList manufacturerList = handwerkResource.getAllManufacturers();
        if (manufacturerList != null) {
            for (Manufacturer m : manufacturerList.getList()) {
                Log.d(MainActivity.class.getName(), m.toString());
            }
        }

        ServiceList serviceList = handwerkResource.getAllServices();
        if (serviceList != null) {
            for (Service s : serviceList.getList()) {
                Log.d(MainActivity.class.getName(), s.toString());
            }
        }
    }



}
