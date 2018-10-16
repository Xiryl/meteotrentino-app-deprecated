package it.chiarani.meteotrentinoapp.helper;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import it.chiarani.meteotrentinoapp.R;

/**
 * Own custom dialog
 */
public class CustomDialog extends Dialog implements android.view.View.OnClickListener {

  // #region private fields
  private Activity activity;
  private Dialog dialog;
  private String text;
  // #endregion

  public CustomDialog(Activity activity, String text) {
    super(activity);
    this.activity = activity;
    this.text = text;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.item_dialog);
    setCanceledOnTouchOutside(false);

    AppCompatButton btn_yes = findViewById(R.id.item_dialog_btn_ok);
    btn_yes.setOnClickListener(this);

    TextView txt = findViewById(R.id.item_dialog_txt);
    txt.setText(text);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.item_dialog_btn_ok:
        dismiss();
        break;
      default:
        break;
    }
    dismiss();
  }
}