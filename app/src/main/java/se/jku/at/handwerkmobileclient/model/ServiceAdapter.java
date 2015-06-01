package se.jku.at.handwerkmobileclient.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;
import se.jku.at.handwerkmobileclient.views.ListItemView;
import se.jku.at.handwerkmobileclient.views.ListItemView_;

/**
 * Created by Martin on 14.05.15.
 */
@EBean
public class ServiceAdapter extends BaseAdapter implements Filterable {

    private Filter serviceFilter;
    private List<Service> services;
    private List<Service> services_orig;
    private Comparator<Service> comparator;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        comparator = serviceNameComparator;
        getData();
    }

    private void getData() {

        services = new HandwerkResourceImpl().getAllServices().getList();
        services_orig = new ArrayList<>(services);
        Collections.sort(services, comparator);
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

    public void refresh() {
        getData();
        notifyDataSetChanged();
    }

    public void setComparator(Comparator<Service> c) {
        this.comparator = c;
        refresh();
    }

    public static Comparator<Service> serviceNameComparator = new Comparator<Service>() {
        @Override
        public int compare(Service lhs, Service rhs) {
            return lhs.getHeadline().compareTo(rhs.getHeadline());
        }
    };

    public static Comparator<Service> servicePriceComparator = new Comparator<Service>() {
        @Override
        public int compare(Service lhs, Service rhs) {
            return (int) (lhs.getPrice() - rhs.getPrice());
        }
    };

    @Override
    public Filter getFilter() {
        if (serviceFilter == null) {
            serviceFilter = new ServiceFilter();
        }
        return serviceFilter;
    }

    private class ServiceFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<Service> filterList = new ArrayList<>();
                String constraintStr = constraint.toString().toLowerCase();
                for (int i = 0; i < services.size(); i++) {
                    String headline = services.get(i).getHeadline();
                    String desc = services.get(i).getDetailInfo();

                    if (headline != null && headline.toLowerCase().contains(constraintStr)) {
                        filterList.add(services.get(i));
                    }
                    else if (desc != null && desc.toLowerCase().contains(constraintStr)) {
                        filterList.add(services.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                synchronized (this) {
                    results.count = services_orig.size();
                    results.values = services_orig;
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                services = (List<Service>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
