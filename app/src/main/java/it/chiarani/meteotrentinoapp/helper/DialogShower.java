package it.chiarani.meteotrentinoapp.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import it.chiarani.meteotrentinoapp.views.ChooseLocationActivity;

public class DialogShower {
  public DialogShower() {}

  /**
   * Show message dialog
   * @param context context
   * @param title dialog title
   * @param description dialog description
   * @param posButton dialog positive button description
   * @param negButton dialog negative button description
   */
  public static void ShowDialog(Context context, Intent intent, String title, String description, String posButton, String negButton) {
    AlertDialog.Builder builder;
    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
    builder.setTitle(title)
        .setMessage(description)
        .setPositiveButton(posButton, (dialog, which) -> {
          if(intent != null)
            context.startActivity(intent);
        })
        .setNegativeButton(negButton, (dialog, which) -> {
          // do nothing
        })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
  }
}
