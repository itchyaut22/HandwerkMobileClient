package se.jku.at.handwerkmobileclient.rest.impl;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by Martin on 12.05.15.
 */
public class RestHelper {

    private static boolean DEBUG_MODE = false;

    private static final String API_VERSION = "v3";

    private static final String BASEURL_OFFLINE = "http://localhost:8080/HandwerkService/api/" + API_VERSION;
    private static final String BASEURL_ONLINE = "http://itchyaut22.ddns.net:8080/HandwerkService/api/" + API_VERSION;

    private static Client client;

    protected static WebTarget getWebTarget() {
        if (client == null) {
            try {
                client = ClientBuilder.newClient().register(AndroidFriendlyFeature.class);
            } catch (Exception e) {}
        }
        return client.target((DEBUG_MODE ? BASEURL_OFFLINE : BASEURL_ONLINE));
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
