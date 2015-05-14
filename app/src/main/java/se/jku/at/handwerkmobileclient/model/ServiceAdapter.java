package se.jku.at.handwerkmobileclient.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.views.ListItemView;
import se.jku.at.handwerkmobileclient.views.*;

/**
 * Created by Martin on 14.05.15.
 */
@EBean
public class ServiceAdapter extends BaseAdapter {


    private List<Service> services;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        getData();
    }

    private void getData() {
        services = new HandwerkResourceImpl().getAllServices().getList();
    }

    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Service getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView view;

        if (convertView == null) {
            view = ListItemView_.build(context, this);
        } else {
            view = (ListItemView) convertView;
        }

        view.bind(getItem(position));
        return view;
    }
}
