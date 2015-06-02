package se.jku.at.handwerkmobileclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import se.jku.at.handwerkmobileclient.fragments.*;

/**
 * @Brief Der PagerAdapter handelt die einzelnen Fragments
 *
 * Als Fragments werden die durch die Annotations generierten Fragments verwendet.
 *
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    // alle verfuegbaren Fragments
    private Fragment listFragment, addFragment;

    public TabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    /**
     * Methode zum Wechseln zwischen den einzelnen Fragmenten.
     * @param index. Gibt an, zu welchem Fragment gewechselt wird.
     * 0 ... List Fragment
     * 1 ... Add Fragment
     */
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                if (listFragment == null) {
                    listFragment = new ListFragment_();
                }
                return listFragment;
            case 1:
                if (addFragment == null) {
                    addFragment = new AddFragement_();
                }
                return addFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}

