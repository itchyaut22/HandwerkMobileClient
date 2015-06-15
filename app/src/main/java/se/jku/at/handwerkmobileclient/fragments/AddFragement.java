package se.jku.at.handwerkmobileclient.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.activities.AddManufacturerActivity_;
import se.jku.at.handwerkmobileclient.activities.AddServiceActivity_;
import se.jku.at.handwerkmobileclient.model.User;
import se.jku.at.handwerkmobileclient.model.UserCategory;


/**
 * Created by Martin on 15.05.15.
 */
@EFragment(R.layout.fragment_insert)
public class AddFragement extends Fragment {

    @ViewById(R.id.fragment_insert_manufacturer)
    Button bInsManu;

    @ViewById(R.id.fragment_insert_service)
    Button bInsServ;

    @AfterViews
    public void init() {

        switch (User.instance.getCategory()) {
            case GUEST:
                bInsManu.setEnabled(false);
                bInsManu.setBackgroundColor(Color.GRAY);
                bInsServ.setEnabled(false);
                bInsServ.setBackgroundColor(Color.GRAY);
                break;
            case USER:
                bInsManu.setEnabled(false);
                bInsManu.setBackgroundColor(Color.GRAY);
                break;
            case ADMINISTRATOR:
                bInsServ.setEnabled(false);
                bInsServ.setBackgroundColor(Color.GRAY);
                break;
        }
    }

    @Click(R.id.fragment_insert_manufacturer)
    void buttonAddManufacturer_Click() {
        AddManufacturerActivity_.intent(getActivity()).start();
    }

    @Click(R.id.fragment_insert_service)
    void buttonAddService_Click() {
        AddServiceActivity_.intent(getActivity()).start();
        
    }
}
