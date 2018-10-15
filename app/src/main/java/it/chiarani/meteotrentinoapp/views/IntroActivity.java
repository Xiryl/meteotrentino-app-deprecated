package it.chiarani.meteotrentinoapp.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.github.paolorotolo.appintro.ISlidePolicy;
import com.github.paolorotolo.appintro.model.SliderPage;

import it.chiarani.meteotrentinoapp.R;

public class IntroActivity extends AppIntro2 implements ISlideBackgroundColorHolder {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addSlide(AppIntro2Fragment.newInstance("Meteo Trentino", "Benvenuto in Meteo Trentino App!\nTi chiediamo l'accesso al GPS per fornirti i dati meteo nella tua posizione attuale!", R.drawable.img_1, Color.parseColor("#A0C264")));
    addSlide(AppIntro2Fragment.newInstance("Meteo Sempre Aggiornato", "Previsioni, dati radar, umidità, vento, probabilità precipitazioni e quant'altro in questa applicazione..", R.drawable.img_2, Color.parseColor("#51AFDF")));
    addSlide(AppIntro2Fragment.newInstance("Allerte Intelligenti", "Disponiamo di un sistema di notifiche intelligenti in caso di allerte provinciali e metereologiche, rimani sempre aggiornato con la nostra applicazione!", R.drawable.img_3, Color.parseColor("#F4C359")));

    // Hide Skip/Done button.
    showSkipButton(false);
    setProgressButtonEnabled(true);

    // Turn vibration on and set intensity.
    // NOTE: you will probably need to ask VIBRATE permission in Manifest.
    setVibrate(true);
    setVibrateIntensity(30);


    // Ask for CAMERA permission on the second slide
    askForPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); // OR

  }

  @Override
  public void onSkipPressed(Fragment currentFragment) {
    super.onSkipPressed(currentFragment);
    // Do something when users tap on Skip button.
  }

  @Override
  public void onDonePressed(Fragment currentFragment) {
    Intent x = new Intent(this, LoaderActivity.class);
    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    this.startActivity(x);
    this.finish();
  }

  @Override
  public int getDefaultBackgroundColor() {
    // Return the default background color of the slide.
    return Color.parseColor("#000000");
  }

  @Override
  public void setBackgroundColor(@ColorInt int backgroundColor) {
    // Set the background color of the view within your slide to which the transition should be applied.
    if (this != null) {
      this.setBackgroundColor(backgroundColor);
    }
  }
}
