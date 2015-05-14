package se.jku.at.handwerkmobileclient.activities;

import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;

@EActivity(R.layout.activity_service_detail)
public class ServiceDetailActivity extends FragmentActivity {

    @Extra("idExtra")
    int id;

    @ViewById(R.id.service_detail_headline)
    TextView headline;

    @ViewById(R.id.service_detail_address)
    TextView address;

    @ViewById(R.id.service_detail_distanceTo)
    TextView distance;

    private Service service;

    @AfterViews
    void init() {
        service = new HandwerkResourceImpl().getService(id);
        headline.setText(service.getHeadline());
        distance.setText(service.getDetailInfo());
    }
}
