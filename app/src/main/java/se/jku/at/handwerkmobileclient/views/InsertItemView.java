package se.jku.at.handwerkmobileclient.views;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.jku.at.handwerkmobileclient.R;

/**
 *
 * Diese Klasse ist ein LinearLayout und wird mit EViewGroup annotiert.
 * Es beeinhaltet eine TextView als Headline, und ein EditText als Eingabefeld.
 * Verwendet wird diese Klasse fuer das einfuegen eines neuen Handwerkers.
 *
 * @author Martin
 *
 */
@EViewGroup(R.layout.insert_item)
public class InsertItemView extends LinearLayout {

    Drawable originalDrawable;

    @ViewById
    protected TextView stat_ins_headline;

    @ViewById
    protected EditText stat_ins_value;

    private String titleText, hintText;
    private boolean enabled;
    private int inputType;

    /**
     * Constructor
     * @param context
     * @param attrs
     */
    public InsertItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // gesetzte Opionen vom .xml auslesen
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.Options);
        titleText = attributes.getString(R.styleable.Options_titleText);
        hintText = attributes.getString(R.styleable.Options_hintText);
        enabled = attributes.getBoolean(R.styleable.Options_enabled, true);

        if (titleText == null) {
            titleText = "??"; // falls titel nicht gesetzt
        } else {
            titleText += ":";
        }
        if (hintText == null) {
            hintText = "";
        }
        // InputType fuer EditText
        inputType = attributes.getInt(R.styleable.Options_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL);

        attributes.recycle();
    }

    @AfterViews
    protected void init() {
        setKey(titleText);
        setHintText(hintText);
        setEnabled(enabled);
        stat_ins_value.setInputType(inputType);
        stat_ins_value.setEnabled(enabled);
        originalDrawable = stat_ins_value.getBackground();
    }

    /**
     * Text der Ueberschrift
     * @param s Text fuer das Textfeld
     * @return
     */
    public InsertItemView setKey(String s) {
        stat_ins_headline.setText(s);
        return this;
    }

    /**
     * Liefert den Inhalt des Textfeldes
     * @return Text des Textfeldes
     */
    public String getValue() {
        return stat_ins_value.getText().toString();
    }

    /**
     * Text des EditText
     * @param s Text fuer das EditText
     * @return
     */
    public InsertItemView setValue(String s) {
        stat_ins_value.setText(s);
        return this;
    }

    /**
     * Hinweis fuer EditText setzen (ueber xml properties)
     * @param s Hinweis
     * @return
     */
    private InsertItemView setHintText(String s) {
        stat_ins_value.setHint(s);
        return this;
    }

    public EditText getStat_ins_value() {
        return stat_ins_value;
    }

    public void setStat_ins_value(EditText stat_ins_value) {
        this.stat_ins_value = stat_ins_value;
    }

    /**
     * Error Text fuer EditText setzen
     * @param s Text
     * @return
     */
    public InsertItemView setErrorText(String s) {
        stat_ins_value.setError(s);
        return this;
    }

    public InsertItemView deleteErrorText(){
        stat_ins_value.setError(null);
        return this;
    }
}
