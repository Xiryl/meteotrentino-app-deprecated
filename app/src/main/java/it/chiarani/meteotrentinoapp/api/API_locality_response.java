package it.chiarani.meteotrentinoapp.api;

import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.models.Locality;

/**
 * Interface used from API_locality for callback for asynctask
 */

public interface API_locality_response {
  void processFinish(ArrayList<Locality> output);
}