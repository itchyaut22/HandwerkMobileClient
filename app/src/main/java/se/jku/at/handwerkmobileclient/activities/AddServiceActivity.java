package se.jku.at.handwerkmobileclient.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceCategory;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.views.InsertItemView;

/**
 * Created by Martin on 15.05.15.
 */
@EActivity(R.layout.activity_insert_service)
public class AddServiceActivity extends FragmentActivity {

    TextView manufIdView;

    @ViewById(R.id.activity_insert_serv_headline)
    protected InsertItemView headline;

    @ViewById(R.id.activity_insert_serv_detailInfo)
    protected InsertItemView detailInfo;

    @ViewById(R.id.activity_insert_serv_price)
    protected InsertItemView price;

    @AfterViews
    void init() {
        final HandwerkResource res = new HandwerkResourceImpl();
        //Get all manufacturers
        final List<Manufacturer> manus = res.getAllManufacturers().getList();
        //Get all categories
        final List<ServiceCategory> categories = res.getServiceCategories().getList();

        // Set Manufacturer Spinner
        Spinner spinnerMan = (Spinner) findViewById(R.id.activity_insert_serv_manuf_spinner);
        ArrayAdapter<Manufacturer> manAdapter = new ArrayAdapter<Manufacturer>(this,
                android.R.layout.simple_spinner_item, manus);
        manAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMan.setAdapter(manAdapter);

        // Set Category Spinner
        Spinner spinnerCat = (Spinner) findViewById(R.id.activity_insert_serv_category_spinner);
        ArrayAdapter<ServiceCategory> catAdapter = new ArrayAdapter<ServiceCategory>(this,
                android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(catAdapter);

        headline.setValue("HEADLINE");
        detailInfo.setValue("Detail-Information about service!");
        price.setValue("0");
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

        Manufacturer man = (Manufacturer) ((Spinner) findViewById(R.id.activity_insert_serv_manuf_spinner)).getSelectedItem();

        ServiceCategory cat = (ServiceCategory) ((Spinner) findViewById(R.id.activity_insert_serv_category_spinner)).getSelectedItem();

        Double priceDouble = Double.parseDouble(priceString);

        final HandwerkResource res = new HandwerkResourceImpl();

        final Service serv = new Service(cat,headl,detail,man.getId(),priceDouble);

        if (res.addService(serv)) {
            finish();
        } else {
            showAlertDialog("Fehler", "Einfügen nicht erfolgreich!");
        }

    }


    /**
     * Dient dazu einen Alert Dialog anzuzeigen
     *
     * @param ueberschrift, �berschrift des Dialogs
     * @param text,         anzuzeigender Text des Dialogfensters
     */
    void showAlertDialog(String ueberschrift, String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(ueberschrift);

        // set dialog message
        alertDialogBuilder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // the dialog box
                        dialog.cancel();
                    }
                })
       /*
        * .setNegativeButton("No",new DialogInterface.OnClickListener() {
        * public void onClick(DialogInterface dialog,int id) { // if this
        * button is clicked, just close // the dialog box and do nothing
        * dialog.cancel(); } })
        */;

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
