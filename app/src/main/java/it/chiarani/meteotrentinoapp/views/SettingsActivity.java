package it.chiarani.meteotrentinoapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;

import java.security.PublicKey;

import it.chiarani.meteotrentinoapp.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Display the fragment as the main content.
    getSupportFragmentManager().beginTransaction()
        .replace(android.R.id.content, new SettingsFragment())
        .commit();
  }
}