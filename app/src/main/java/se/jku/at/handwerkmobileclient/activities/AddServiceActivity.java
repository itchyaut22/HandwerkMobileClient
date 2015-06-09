package se.jku.at.handwerkmobileclient.activities;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import se.jku.at.handwerkmobileclient.BuildConfig;
import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceCategory;
import se.jku.at.handwerkmobileclient.model.User;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.views.InsertItemView;

/**
 * Created by Martin on 15.05.15.
 */
@EActivity(R.layout.activity_insert_service)
public class AddServiceActivity extends BaseActivity {

    TextView manufIdView;

    @ViewById(R.id.activity_insert_serv_headline)
    protected InsertItemView headline;

    @ViewById(R.id.activity_insert_serv_detailInfo)
    protected InsertItemView detailInfo;

    @ViewById(R.id.activity_insert_serv_price)
    protected InsertItemView price;

    @ViewById(R.id.activity_insert_serv_manuf_spinner)
    protected Spinner manuf_spinner;

    @ViewById(R.id.activity_insert_serv_category_spinner)
    protected Spinner cat_spinner;


    @AfterViews
    void init() {

        try {
            final HandwerkResource res = new HandwerkResourceImpl();
            //Get all manufacturers
            final List<Manufacturer> manus = /*res.getAllManufacturers().getList();*/ new ArrayList<>();
            manus.add(User.instance.getManufacturer());
            //Get all categories
            final List<ServiceCategory> categories = res.getServiceCategories().getList();


            // Set Manufacturer Spinner
            ArrayAdapter<Manufacturer> manAdapter = new ArrayAdapter<Manufacturer>(this,
                    android.R.layout.simple_spinner_item, manus);
            manAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            manuf_spinner.setAdapter(manAdapter);

            // Set Category Spinner
            ArrayAdapter<ServiceCategory> catAdapter = new ArrayAdapter<ServiceCategory>(this,
                    android.R.layout.simple_spinner_item, categories);
            catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cat_spinner.setAdapter(catAdapter);

            if (BuildConfig.DEBUG) {
                headline.setValue("HEADLINE");
                detailInfo.setValue("Detail-Information about service!");
                price.setValue("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.activity_insert_serv_bAdd)
    void btn_insert_click() {

        String headl = headline.getValue();
        String detail = detailInfo.getValue();
        String priceString = price.getValue();

        if (headl == null || headl.equals("")) {
            showAlertDialog("Fehler", "Headline darf nicht leer sein!");
            return;
        }

        if (detail == null || detail.equals("")) {
            showAlertDialog("Fehler", "Detail-Info darf nicht leer sein!");
            return;
        }

        if (priceString == null || priceString.equals("")) {
            showAlertDialog("Fehler", "Preis darf nicht leer sein!");
            return;
        }

        Manufacturer man = User.instance.getManufacturer();

        ServiceCategory cat = (ServiceCategory) cat_spinner.getSelectedItem();

        Double priceDouble = Double.parseDouble(priceString);

        try {
            final HandwerkResource res = new HandwerkResourceImpl();
            final Service serv = new Service(cat,headl,detail,man.getId(),priceDouble);

            if (res.addService(serv)) {
                finish();
            } else {
                showAlertDialog("Fehler", "Einfügen nicht erfolgreich!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlertDialog("Fehler", e.getMessage());
        }
    }
}
