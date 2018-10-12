package it.chiarani.meteotrentinoapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.helper.DialogShower;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {

  public Preference pref_key_notifica_mattina;
  public Preference pref_key_privacy;
  public Preference pref_key_feedback;
  public Preference pref_version;
  public Preference pref_preferiti;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pref_key_notifica_mattina = findPreference("pref_key_notifica_mattina");
    pref_key_privacy = findPreference("pref_key_privacy");
    pref_key_feedback = findPreference("pref_key_feedback");
    pref_version = findPreference("pref_key_versione");
    pref_preferiti = findPreference("pref_key_pulisci_preferiti");

    try {
      pref_version.setSummary(this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 0).versionName);
    } catch(PackageManager.NameNotFoundException ex) { }

    pref_key_privacy.setOnPreferenceClickListener(this);
    pref_key_feedback.setOnPreferenceClickListener(this);
    pref_version.setOnPreferenceClickListener(this);
    pref_preferiti.setOnPreferenceClickListener(this);
  }

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    // Load the preferences from an XML resource
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    switch (preference.getKey()) {
      case "pref_key_pulisci_preferiti":
        // preferences
        SharedPreferences getPrefs = PreferenceManager
            .getDefaultSharedPreferences(getActivity().getBaseContext());

        SharedPreferences.Editor e = getPrefs.edit();

        //  Edit preference to make it false because we don't want this to run again
        e.putString("second_pos", "Aggiungi 2° Preferito");
        e.putString("first_pos", "Aggiungi 1° Preferito");

        //  Apply changes
        e.apply();
        Toast.makeText(getContext(), "Ok.", Toast.LENGTH_SHORT).show();
        break;
      case "pref_key_privacy":
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chiarani.it/meteotrentinoapp_privacy_policy.pdf"));
        startActivity(browserIntent);
        break;
      case "pref_key_feedback":
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:fabio@chiarani.it"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[METEOTRENTINO-APP]");
        startActivity(emailIntent);
        break;
      case "pref_key_versione":
        String appV = "";
        try {
          appV = this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 0).versionName;
        } catch(PackageManager.NameNotFoundException ex) { }

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("MeteoTrentino App " + appV)
            .setMessage("-VERSIONE BETA (1.0)\n-VERSIONE BETA (1.1)")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {

              }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
        break;
    }

    return true;
  }
}