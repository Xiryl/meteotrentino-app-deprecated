package it.chiarani.meteotrentinoapp.helper;

import android.util.Log;

public class Converter {

  private static final String CLASS_TAG = "CONVERTER";

  /**
   * def. constructor
   */
  public Converter() {

  }

  /**
   * Get icon id from url
   */
  public static int convertIconUriToInt(String uri) {
    int icon = -1;

    try {
      String substr  = uri.split("_")[1];
      String numbers = substr.substring(0,3);
      icon           = Integer.parseInt(numbers);
    }
    catch (Exception ex) {
      Log.e(CLASS_TAG, "Errore nella conversione da uri a int (icona): " + ex);
    }

    return icon;
  }

}
