package se.jku.at.handwerkmobileclient.views;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import se.jku.at.handwerkmobileclient.R;
import se.jku.at.handwerkmobileclient.model.Service;

/**
 * Created by Martin on 14.05.15.
 */
@EViewGroup(R.layout.listitem)
public class ListItemView extends LinearLayout {

    @ViewById(R.id.stat_headline)
    TextView tv_headline;

    @ViewById(R.id.stat_details)
    TextView tv_details;

    @ViewById(R.id.item_price)
    TextView tv_price;

    private BaseAdapter adapter;

    public ListItemView(Context context) {
        super(context);
    }

    public ListItemView(Context context, BaseAdapter adapter) {
        this(context);
        this.adapter = adapter;
    }

    public void bind(Service item) {
        tv_headline.setText(item.getHeadline());
        tv_details.setText(item.getDetailInfo());
        tv_price.setText(String.valueOf(item.getPrice()) + " €");
    }

    public TextView getTv_headline() {
        return tv_headline;
    }
}
