package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityMessageBinding;
import it.chiarani.meteotrentinoapp.repositories.CustomAlertRepository;

public class MessageActivity extends SampleActivity {

  // #region private fields
  ActivityMessageBinding binding;
  // #endregion
  @Override
  protected int getLayoutID() {
    return R.layout.activity_message;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent this_intent = getIntent();

    if(this_intent.hasExtra("payload")){
      String payload = this_intent.getStringExtra("payload");
      binding.activityMessageTxt.setText(payload);

      CustomAlertRepository repository = new CustomAlertRepository(getApplication());
      repository.deleteAll();
    }
  }
}
