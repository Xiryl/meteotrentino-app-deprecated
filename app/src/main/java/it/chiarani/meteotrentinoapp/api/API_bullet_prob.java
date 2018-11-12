package it.chiarani.meteotrentinoapp.api;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;
import it.chiarani.meteotrentinoapp.models.BulletProbFascia;
import it.chiarani.meteotrentinoapp.models.BulletProbFenomeno;
import it.chiarani.meteotrentinoapp.models.BulletProbFull;
import it.chiarani.meteotrentinoapp.models.BulletProbGiorno;
import it.chiarani.meteotrentinoapp.repositories.LocationRepository;

public class API_bullet_prob extends AsyncTask<String, Integer, Integer>{

    // #region private fields
    private final static String   CLASS_TAG        = API_bullet_prob.class.getSimpleName();
    private final static String   URL_API          = "https://www.meteotrentino.it/protcivtn-meteo/api/front/previsioneOpenDataProbabilistico ";
    private API_bullet_prob_response delegate;
    private Context               context;
    private AlertDialog           builder;
    private Application           app;
    private BulletProbFull mBullettin;
    // #endregion

    /**
     * Main constructor
     * @param mContext app context
     * @param res callback interface for get content async
     */
    public API_bullet_prob(Application app, Context mContext, API_bullet_prob_response res) {
        this.app      = app;
        this.context  = mContext;
        this.delegate = res;
    }

    /**
     * Before execute the task create a dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Scarico il bollettino probabilistico..").create();
        alert.setCancelable(false);
        builder = alert.show();
    }

    /**
     * After execute the task call the interface callback
     * and send the data
     */
    @Override
    protected void onPostExecute(Integer integer) {
        builder.dismiss();
        delegate.processFinish(mBullettin);
    }

    /**
     * Execute in bakground the task:
     * Create a GET and call a REST API for get all locality and parse the JSON
     */
    @Override
    protected Integer doInBackground(String... s) {
        LocationRepository repository = new LocationRepository(app);
        HttpURLConnection  connection = null;
        BufferedReader     reader     = null;
        mBullettin = new BulletProbFull();

        try {
            URL url = new URL(URL_API);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            publishProgress(1);
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            String data   = buffer.toString();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray arr_giorni = jsonObject.getJSONArray("giorni");


            List<BulletProbGiorno> tmpBpg = new ArrayList<>();
            // Cycle all localities
            for (int i = 0; i < arr_giorni.length() ; i++) {
                publishProgress(i*10) ; // Update %

                BulletProbGiorno bpg = new BulletProbGiorno();
                bpg.setNomeGiorno(arr_giorni.getJSONObject(i).optString("nomeGiorno"));
                bpg.setDataGiorno(arr_giorni.getJSONObject(i).optString("giorno"));


                JSONArray arr_fasce = arr_giorni.getJSONObject(i).getJSONArray("fasce");

                List<BulletProbFascia> tmpBpf = new ArrayList<>();
                for (int j = 0; j < arr_fasce.length() ; j++) {
                    BulletProbFascia bpf = new BulletProbFascia();
                    bpf.setFascia(arr_fasce.getJSONObject(j).optString("fascia"));
                    bpf.setFasciaPer(arr_fasce.getJSONObject(j).optString("fasciaOre"));

                    List<BulletProbFenomeno> tmpBpfen = new ArrayList<>();
                    JSONArray arr_fen = arr_fasce.getJSONObject(j).getJSONArray("fenomeni");
                    for (int k = 0; k < arr_fen.length()  ; k++) {
                        BulletProbFenomeno bpfen = new BulletProbFenomeno();
                        bpfen.setFenomeno(arr_fen.getJSONObject(k).optString("fenomeno"));
                        bpfen.setProbabilita(arr_fen.getJSONObject(k).optString("probabilita"));
                        bpfen.setValore(arr_fen.getJSONObject(k).optInt("valore"));

                        tmpBpfen.add(bpfen);
                    }
                    bpf.setFenomeni(tmpBpfen);

                    tmpBpf.add(bpf);
                }
                bpg.setFasce(tmpBpf);

                tmpBpg.add(bpg);
            }

            mBullettin.setGiorni(tmpBpg);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(CLASS_TAG, "Errore Exception: "+  e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(CLASS_TAG, "Errore IOException: "+  e.toString());
            }
        }
        return 1;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        TextView messageView = builder.findViewById(android.R.id.message);
        messageView.setText(String.format("%s%%", values[0] ));
    }
}
