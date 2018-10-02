package it.chiarani.meteotrentinoapp.fragment;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.Preference;

import it.chiarani.meteotrentinoapp.R;

public class SettingsFragment extends PreferenceFragment {

  public Preference pref_key_notifica_mattina;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    // Load the preferences from an XML resource
    addPreferencesFromResource(R.xml.preferences);

    pref_key_notifica_mattina = findPreference("pref_key_notifica_mattina");
  }
}