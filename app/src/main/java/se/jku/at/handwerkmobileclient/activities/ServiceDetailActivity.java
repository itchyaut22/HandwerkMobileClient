package se.jku.at.handwerkmobileclient.activities;

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
public class ServiceDetailActivity extends BaseActivity {

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
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.deleteButton)
    void deleteService() {
        final HandwerkResource res;
        try {
            res = new HandwerkResourceImpl();
            boolean stat = res.deleteService(id);
            if(stat) {
                showAlertDialog("Delete", "Erfolgreich gelöscht!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}