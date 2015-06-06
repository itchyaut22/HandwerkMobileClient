package se.jku.at.handwerkmobileclient.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.activities.AddManufacturerActivity_;
import se.jku.at.handwerkmobileclient.activities.AddServiceActivity_;


/**
 * Created by Martin on 15.05.15.
 */
@EFragment(R.layout.fragment_insert)
public class AddFragement extends Fragment {

    @Click(R.id.fragment_insert_manufacturer)
    void buttonAddManufacturer_Click() {
        AddManufacturerActivity_.intent(getActivity()).start();
    }

    @Click(R.id.fragment_insert_service)
    void buttonAddService_Click() {
        AddServiceActivity_.intent(getActivity()).start();
        
    }
}
