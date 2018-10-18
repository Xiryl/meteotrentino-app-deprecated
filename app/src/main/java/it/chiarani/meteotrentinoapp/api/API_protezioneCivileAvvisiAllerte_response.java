package it.chiarani.meteotrentinoapp.api;

import java.util.ArrayList;

/**
 * Interface used from API_protezioneCivileAvvisiAllerte for callback for asynctask
 */
public interface API_protezioneCivileAvvisiAllerte_response {
  void processFinish(ArrayList<String> data);
}
