package it.chiarani.meteotrentinoapp.helper;

import android.util.Log;

import java.util.regex.Pattern;

public class Converter {

  private static final String TAG_CONVERTER = "CONVERTER";

  /**
   * def. constructor
   */
  public Converter() {

  }

  public static int convertIconUriToInt(String uri) {
    int icon = -1;

    try {
      String substr = uri.split("_")[1];
      String numbers = substr.substring(0,3);
      icon = Integer.parseInt(numbers);
    }
    catch (Exception ex) {
      Log.e(TAG_CONVERTER, "Errore nella conversione da uri a int (icona): " + ex);
    }

    return icon;
  }

}
