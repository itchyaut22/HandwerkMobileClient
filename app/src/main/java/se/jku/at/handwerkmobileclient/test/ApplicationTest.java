package se.jku.at.handwerkmobileclient.test;

import android.os.StrictMode;
import android.test.InstrumentationTestCase;

import junit.framework.Assert;

import se.jku.at.handwerkmobileclient.model.ManufacturerList;
import se.jku.at.handwerkmobileclient.model.ServiceList;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;
import se.jku.at.handwerkmobileclient.rest.impl.HandwerkResourceImpl;

/**
 * Created by Martin on 12.05.15.
 */
public class ApplicationTest extends InstrumentationTestCase {

    public ApplicationTest() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void test1() throws Exception {
        final HandwerkResource res = new HandwerkResourceImpl();
        Assert.assertNotNull(res);

        final ManufacturerList manufacturerList = res.getAllManufacturers();
        Assert.assertNotNull(manufacturerList);
        Assert.assertNotNull(manufacturerList.getList());
        Assert.assertTrue(manufacturerList.getList().size() > 0);

        final ServiceList serviceList = res.getAllServices();
        Assert.assertNotNull(serviceList);
        Assert.assertNotNull(serviceList.getList());
        Assert.assertTrue(serviceList.getList().size() > 0);
    }
}
