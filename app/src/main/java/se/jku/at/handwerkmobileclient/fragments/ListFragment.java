package se.jku.at.handwerkmobileclient.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.activities.ServiceDetailActivity_;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceAdapter;

/**
 * Created by Martin on 14.05.15.
 */
@EFragment(R.layout.fragment_list)
public class ListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @ViewById(R.id.list_view)
    ListView stationList;

    @ViewById(R.id.spinner1)
    Spinner spinner;

    @ViewById(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bean
    ServiceAdapter adapter;

    @AfterViews
    void init() {
        stationList.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);
    }

    @TextChange(R.id.inputSearch)
    protected void onTextChangesOnInputSearch(CharSequence text, int before, int start, int count) {
        adapter.getFilter().filter(text);
    }

    private void refreshContent() {
        adapter.refresh();
        swipeRefreshLayout.setRefreshing(false);
        stationList.smoothScrollToPosition(0);
    }

    @ItemClick(R.id.list_view)
    protected void itemClicked(Service service) {
        // show detail view
        //Toast.makeText(getActivity(), service.getHeadline(), Toast.LENGTH_SHORT).show();

        ServiceDetailActivity_.intent(getActivity()).id(service.getId()).start();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setComparatorForAdapter(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setComparatorForAdapter(int id) {
        switch(id) {
            case 0:
                adapter.setComparator(ServiceAdapter.serviceNameComparator);
                break;
            case 1:
                adapter.setComparator(ServiceAdapter.servicePriceComparator);
                break;
        }
    }
}
