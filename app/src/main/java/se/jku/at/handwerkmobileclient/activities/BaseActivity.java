package se.jku.at.handwerkmobileclient.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Martin on 06.06.15.
 */
public abstract class BaseActivity extends FragmentActivity {

    /**
     * Dient dazu einen Alert Dialog anzuzeigen
     *
     * @param ueberschrift, �berschrift des Dialogs
     * @param text,         anzuzeigender Text des Dialogfensters
     */
    protected void showAlertDialog(String ueberschrift, String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(ueberschrift);

        // set dialog message
        alertDialogBuilder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // the dialog box
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }

    protected void showAlertDialog(String ueberschrift, String text, final boolean dismiss) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(ueberschrift);

        // set dialog message
        alertDialogBuilder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // the dialog box
                        dialog.cancel();
                    }
                });

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dismiss)finish();
                else alertDialog.hide();
            }
        });
    }
}
