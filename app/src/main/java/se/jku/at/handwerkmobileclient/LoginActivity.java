package se.jku.at.handwerkmobileclient;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.AuthorizationUIController;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.glassfish.jersey.client.JerseyClientBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import se.jku.at.handwerkmobileclient.activities.BaseActivity;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.User;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.security.BCrypt;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById(R.id.login_text_password)
    EditText password;

    @ViewById(R.id.login_text_username)
    EditText username;

    @AfterViews
    void init() {

    }

    @Click(R.id.login_button_login)
    void login() {

        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (name == null || name.equals("")) {
            showAlertDialog("Fehler", "Username darf nicht leer sein!");
            return;
        }

        if (pass == null || pass.equals("")) {
            showAlertDialog("Fehler", "Passwort darf nicht leer sein!");
            return;
        }

        /*try {
            AuthorizationFlow.Builder builder;
            builder = new AuthorizationFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                    AndroidHttp.newCompatibleTransport(),
                    new JacksonFactory(),
                    new GenericUrl("http://192.168.0.20:8080/HandwerkService/api/token"),
                    new ClientParametersAuthentication("oauth2test", "oauth2clientsecret"),
                    "oauth2test",
                    "");
            AuthorizationFlow flow = builder.build();

            AuthorizationUIController controller =
                    new DialogFragmentController(getFragmentManager()) {

                        @Override
                        public String getRedirectUri() throws IOException {
                            return "http://192.168.0.20:8080/HandwerkService/api/redirect";
                        }

                        @Override
                        public boolean isJavascriptEnabledForWebView() {
                            return true;
                        }

                    };

            OAuthManager manager = new OAuthManager(flow, controller);
            OAuthManager.OAuthFuture f = manager.authorizeExplicitly("Hugo123", null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        new RetrieveFeedTask(this).execute(name, pass);

    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;
        private boolean success = false;
        private LoginActivity activity;

        public RetrieveFeedTask(LoginActivity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                activity.startMainActivity();
            }
        }

        @Override
        protected Boolean doInBackground(String... params) {

                try {
                    String username = params[0];
                    String password = BCrypt.hashpw(params[1], BCrypt.gensalt(6));
                    String accessToken = null;

                    if (accessToken == null) {
                        OAuthClientRequest request = OAuthClientRequest
                                .tokenLocation("http://192.168.0.20:8080/HandwerkService/" + "api/token")
                                .setGrantType(GrantType.PASSWORD)
                                .setClientId("oauth2test")
                                .setClientSecret("oauth2clientsecret")
                                .setUsername(username)
                                .setPassword(password)
                                .buildBodyMessage();

                        OAuthClient oAuthClient =
                                new OAuthClient(new URLConnectionClient());
                        OAuthAccessTokenResponse oauthResponse =
                                oAuthClient.accessToken(request);

                        accessToken = oauthResponse.getAccessToken();
                        User.getInstance(username).setPass(password).setToken(accessToken);
                        for (Manufacturer m : new HandwerkResourceImpl().getAllManufacturers().getList()) {
                            if (m.getName().equals(username) && User.instance != null) {
                                User.instance.setManufacturer(m);
                                break;
                            }
                        }
                        if (User.instance.getManufacturer() == null) {
                            //showAlertDialog("Fehler", "Manufacturer == null");
                            return false;
                        }
                    }

                    /*Client client = JerseyClientBuilder.newClient();

                    URL restUrl = new URL("http://192.168.0.20:8080/HandwerkService/" + "api/v4/manufacturers");
                    WebTarget target = client.target(restUrl.toURI());
                    String entity = target.request(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " +
                                    accessToken)
                            .get(String.class);
                    System.out.println("Response = " + entity);*/
                    return true;
                } catch (Exception ex ) {
                    ex.printStackTrace();
                }

                return false;
        }
    }

    private void startMainActivity() {
        MainActivity_.intent(this).start();
    }
}
