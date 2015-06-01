package se.jku.at.handwerkmobileclient;

import android.app.ActionBar;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.ManufacturerList;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceList;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    public static final String version = "v3";

    private TabsPagerAdapter mAdapter;

    @ViewById(R.id.pager)
    ViewPager viewPager;

    @AfterViews
    public void init() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        final ActionBar actionbar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // create new tabs and and set up the titles of the tabs
        ActionBar.Tab mFindTab = actionbar.newTab().setText(
                getString(R.string.ui_tabname_list));
        ActionBar.Tab mChatTab = actionbar.newTab().setText(
                getString(R.string.ui_tabname_map));

        // bind the fragments to the tabs - set up tabListeners for each tab
        mFindTab.setTabListener(this);
        mChatTab.setTabListener(this);

        // add the tabs to the action bar
        actionbar.addTab(mFindTab);
        actionbar.addTab(mChatTab);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionbar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    public void Button1Click() {
        final HandwerkResource handwerkResource = new HandwerkResourceImpl();

        ManufacturerList manufacturerList = handwerkResource.getAllManufacturers();
        if (manufacturerList != null) {
            for (Manufacturer m : manufacturerList.getList()) {
                Log.d(MainActivity.class.getName(), m.toString());
            }
        }

        ServiceList serviceList = handwerkResource.getAllServices();
        if (serviceList != null) {
            for (Service s : serviceList.getList()) {
                Log.d(MainActivity.class.getName(), s.toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
