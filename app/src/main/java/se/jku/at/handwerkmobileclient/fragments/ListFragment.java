package se.jku.at.handwerkmobileclient.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
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

    @Bean
    ServiceAdapter adapter;

    @AfterViews
    void init() {
        stationList.setAdapter(adapter);
    }

    @ItemClick(R.id.list_view)
    protected void itemClicked(Service service) {
        // show detail view
        Toast.makeText(getActivity(), service.getHeadline(), Toast.LENGTH_SHORT).show();

        ServiceDetailActivity_.intent(getActivity()).id(service.getId()).start();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
