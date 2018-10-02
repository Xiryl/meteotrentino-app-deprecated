package it.chiarani.meteotrentinoapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import it.chiarani.meteotrentinoapp.R;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {

  public Preference pref_key_notifica_mattina;
  public Preference pref_key_privacy;
  public Preference pref_key_feedback;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pref_key_notifica_mattina = findPreference("pref_key_notifica_mattina");
    pref_key_privacy = findPreference("pref_key_privacy");
    pref_key_feedback = findPreference("pref_key_feedback");

    pref_key_privacy.setOnPreferenceClickListener(this);
    pref_key_feedback.setOnPreferenceClickListener(this);
  }

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    // Load the preferences from an XML resource
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    switch (preference.getKey()) {
      case "pref_key_privacy":
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chiarani.it/meteotrentinoapp_privacy_policy.pdf"));
        startActivity(browserIntent);
        break;
      case "pref_key_feedback":
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:fabio@chiarani.it"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[METEOTRENTINO-APP]");
        startActivity(emailIntent);
        break;
    }

    return true;
  }
}