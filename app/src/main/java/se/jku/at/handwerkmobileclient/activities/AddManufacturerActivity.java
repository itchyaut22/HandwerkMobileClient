package se.jku.at.handwerkmobileclient.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.BuildConfig;
import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.views.InsertItemView;

/**
 * Created by Martin on 15.05.15.
 */
@EActivity(R.layout.activity_insert_manufacturer)
public class AddManufacturerActivity extends BaseActivity {

    @ViewById(R.id.activity_insert_manuf_name)
    protected InsertItemView tv_name;

    @ViewById(R.id.activity_insert_manuf_address)
    protected InsertItemView tv_address;

    @ViewById(R.id.activity_insert_manuf_plz)
    protected InsertItemView tv_plz;

    @ViewById(R.id.activity_insert_manuf_city)
    protected InsertItemView tv_city;

    @ViewById(R.id.activity_insert_manuf_country)
    protected InsertItemView tv_country;

    @ViewById(R.id.activity_insert_manuf_tel)
    protected InsertItemView tv_tel;

    @ViewById(R.id.activity_insert_manuf_email)
    protected InsertItemView tv_email;

    @ViewById(R.id.activity_insert_manuf_info)
    protected InsertItemView tv_info;


    @AfterViews
    void init() {
        if (BuildConfig.DEBUG) {
            tv_plz.setValue("0");
            tv_name.setValue("Hugo");
            tv_plz.setValue("4040");
            tv_city.setValue("Linz");
            tv_country.setValue("Austria");
            tv_email.setValue("test@test.at");
        }
    }

    @Click(R.id.activity_insert_manuf_bAdd)
    void btn_insert_click() {

        String name = tv_name.getValue();
        String address = tv_address.getValue();
        String city = tv_city.getValue();
        String country = tv_country.getValue();
        String tel = tv_tel.getValue();
        String email = tv_email.getValue();
        String info = tv_info.getValue();

        if (name == null || name.equals("")) {
            showAlertDialog("Fehler", "Name darf nicht leer sein!");
            return;
        }

        if (email == null || email.equals("")) {
            showAlertDialog("Fehler", "Email darf nicht leer sein!");
            return;
        }

        if (city == null || city.equals("")) {
            showAlertDialog("Fehler", "Stadt darf nicht leer sein!");
            return;
        }

        if (country == null || country.equals("")) {
            showAlertDialog("Fehler", "Country darf nicht leer sein!");
            return;
        }

        int plz = -1;
        try {
            plz = Integer.valueOf(tv_plz.getValue());
        } catch (Exception e) {
            showAlertDialog("Fehler", "PLZ ist ungültig!");
            return;
        }


        final HandwerkResource res = new HandwerkResourceImpl();
        final Manufacturer manufacturer = new Manufacturer(name, city, address, plz, country, tel, email, info);
        if (res.addManufacturer(manufacturer)) { // REST Call
            finish(); // Activity schließen
        } else {
            showAlertDialog("Fehler", "Einfügen nicht erfolgreich!");
        }
    }
}
