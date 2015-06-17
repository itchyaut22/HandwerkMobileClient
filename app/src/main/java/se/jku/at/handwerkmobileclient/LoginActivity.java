package se.jku.at.handwerkmobileclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.util.List;

import se.jku.at.handwerkmobileclient.activities.BaseActivity;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.User;
import se.jku.at.handwerkmobileclient.model.UserCategory;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.security.BCrypt;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    private static final int ACTIVITY_REQUEST_CODE = 1;

    @ViewById(R.id.login_text_password)
    EditText password;

    @ViewById(R.id.login_text_username)
    EditText username;

    ProgressDialog mProgressDialog;

    @AfterViews
    void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Click(R.id.login_button_register)
    void register_pressed() {
        User.getInstance("guest").setManufacturer(null).setPass(null).setToken(null);
        RegisterActivity_.intent(this).startForResult(ACTIVITY_REQUEST_CODE);
    }

    @Click(R.id.login_button_continue)
    void continue_pressed() {
        User.getInstance("guest").setManufacturer(null).setPass(null).setToken(null);
        startMainActivity();
    }

    @Click(R.id.login_button_login)
    void login_pressed() {

        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (name == null || name.equals("")) {
            showAlertDialog("Fehler", "Username darf nicht leer sein!", false);
            return;
        }

        if (pass == null || pass.equals("")) {
            showAlertDialog("Fehler", "Passwort darf nicht leer sein!", false);
            return;
        }

        new RetrieveFeedTask(this).execute(name, pass);

    }

    @OnActivityResult(ACTIVITY_REQUEST_CODE)
    protected void onAddManufacturerActivityResult(int resultCode, Intent data){

        if(resultCode == 0 && data != null) {
            username.setText(data.getExtras().getString("username"));
        }
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private LoginActivity activity;

        public RetrieveFeedTask(LoginActivity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute()
        {
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.show();
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setCancelable(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            mProgressDialog.dismiss();
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

                    User.instance = null;

                    if (accessToken == null) {
                        OAuthClientRequest request = OAuthClientRequest
                                .tokenLocation("http://ec2.mayerb.net:8080/HandwerkService/" + "api/token")
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
                        List<Manufacturer> list = new HandwerkResourceImpl().getAllManufacturers().getList();
                        for (Manufacturer m : list) {
                            if (m.getName().equals(username) && User.instance != null) {
                                User.instance.setManufacturer(m);
                                break;
                            }
                        }
                        if (User.instance.getManufacturer() == null) {
                            if (username.equals("admin")) {
                                Manufacturer admin = new Manufacturer();
                                admin.setPassword(password);
                                admin.setName(username);
                                admin.setUserCategory(UserCategory.ADMINISTRATOR);
                                User.instance.setManufacturer(admin);
                                return true;
                            }
                            return false;
                        }
                    }
                    return true;
                } catch (Exception ex ) {
                    ex.printStackTrace();
                }

                return false;
        }
    }

    private void startMainActivity() {
        MainActivity_.intent(this).start();
        finish();
    }
}
