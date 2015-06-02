package se.jku.at.handwerkmobileclient.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;

@EActivity(R.layout.activity_service_detail)
public class ServiceDetailActivity extends FragmentActivity {

    @Extra("idExtra")
    int id;

    @ViewById(R.id.service_detail_headline)
    TextView headline;

    @ViewById(R.id.service_detail_price)
    TextView price;

    @ViewById(R.id.service_detail_details)
    TextView details;

    @ViewById(R.id.service_detail_name)
    TextView name;

    @ViewById(R.id.service_detail_address)
    TextView address;

    @ViewById(R.id.service_detail_country)
    TextView country;

    @ViewById(R.id.service_detail_tel)
    TextView tel;

    @ViewById(R.id.service_detail_email)
    TextView email;

    private Service service;
    private Manufacturer manufacturer;

    @AfterViews
    void init() {
        service = new HandwerkResourceImpl().getService(id);

        if (service != null) {
            manufacturer = new HandwerkResourceImpl().getManufacturer(service.getSupplierid());
            headline.setText(service.getHeadline());
            details.setText(service.getDetailInfo());
            price.setText(service.getPrice() + " €");
        }

        if (service != null && manufacturer != null) {
            name.setText(manufacturer.getName());
            address.setText(manufacturer.getAddress() + ", " + manufacturer.getPlz() + " " + manufacturer.getCity());
            country.setText(manufacturer.getCountry());
            tel.setText(manufacturer.getTel());
            email.setText(manufacturer.getEmail());
        }

    }

    @Click(R.id.deleteButton)
    void deleteService(){
        final HandwerkResource res = new HandwerkResourceImpl();
        boolean stat = res.deleteService(id);
        if(stat) {
            showAlertDialog("Delete", "Erfolgreich gelöscht!");
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
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }

}