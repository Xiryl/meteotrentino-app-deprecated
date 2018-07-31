package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.databinding.ActivityMainBinding;

public class MainActivity extends SampleActivity {

  // Private Fields
  private ActivityMainBinding binding;
  // End

  @Override
  protected int getLayoutID() {
    return R.layout.activity_main;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}
