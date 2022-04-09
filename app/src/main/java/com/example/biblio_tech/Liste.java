package com.example.biblio_tech;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Liste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
    }
    private class RequestTask extends AsyncTask<Void, Void, ArrayList<Livre>> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
//  lance la requète

        private ArrayList<Livre> requete() {
            ArrayList<Livre> response = new ArrayList<Livre>();
            String jsonRep ="";
            try {
                HttpURLConnection connection = null;
                URL url = new
                        URL("https://www.googleapis.com/books/v1/volumes?q=maxResults=2&orderBy=newest&key=AIzaSyBPrb5q2FOsfs0beD1R-Kk8Ue_xjYERm0Q");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne = bufferedReader.readLine() ;
                while (ligne!= null){
                    jsonRep+=ligne;
                    ligne = bufferedReader.readLine();
                }
                JSONObject toDecode = new JSONObject(jsonRep);
                response = decodeJSON(toDecode);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        private ArrayList<Livre> decodeJSON(JSONObject jso) throws Exception {
            JSONArray ja = jso.getJSONArray("items");
            ArrayList<Livre> response = new ArrayList<Livre>();
            Livre livre = new Livre();
            for(int i=4; i<ja.length(); i+=8) {
                livre.setTitre((String) ((JSONObject)ja.get(i)).get("title"));
                livre.setAuteur((String) ((JSONObject)ja.get(i)).get("authors"));
                livre.setImage((String) ((JSONObject) ((JSONObject)ja.get(i)).get("imageLinks")).get("smallThumbnail"));
                response.add(livre);
            }
            Log.d("rep",response.toString());
            return response;
        }

        @Override
        protected ArrayList<Livre> doInBackground(Void... voids) {
            ArrayList<Livre> response = requete();
            return response;
        }

        protected void onPostExecute(ArrayList<Livre> result) {
            TextView titre;
            TextView auteur;
            ImageView image;
            int i = 0;
            LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLivre);
            if (result.size()>1){
                while(i<result.size()){
                    titre = new TextView(getApplicationContext());
                    titre.setText(result.get(i).getTitre());
                    titre.setId(i);
                    layout.addView(titre);
                    i+=1;
                    auteur = new TextView(getApplicationContext());
                    auteur.setText(result.get(i).getAuteur());
                    auteur.setId(i);
                    layout.addView(auteur);
                    i+=1;
                    image = new ImageView(getApplicationContext());
                    String imageUri = result.get(i).getImage();
                    Picasso.get().load(imageUri).into(image);
                    image.setId(i);
                    layout.addView(image);
                }
            }
        }
    }
}