package com.example.biblio_tech;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.*;
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
        RequestTask rt = new RequestTask();
        rt.execute();
    }

    private class RequestTask extends AsyncTask<Void, Void, ArrayList<Livre>> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
//  lance la requète

        private ArrayList<Livre> requete() {
            ArrayList<Livre> response = new ArrayList<Livre>();
            String jsonRep = "";
            try {
                HttpURLConnection connection = null;
                URL url = new
                        URL("https://www.googleapis.com/books/v1/volumes?q=a&maxResults=10&orderBy=newest&key=AIzaSyBPrb5q2FOsfs0beD1R-Kk8Ue_xjYERm0Q");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne = bufferedReader.readLine();
                while (ligne != null) {
                    jsonRep += ligne;
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
            JSONObject js = new JSONObject();
            JSONArray aut = new JSONArray();
            ArrayList<String> autStr = new ArrayList<String>();
            for (int j = 0; j < ja.length(); j++) {
                autStr = new ArrayList<String>();
                livre = new Livre();
                js = ((JSONObject) ja.get(j)).getJSONObject("volumeInfo");
                livre.setTitre((String) js.get("title"));
                aut = js.getJSONArray("authors");
                for (int i = 0; i < aut.length(); i++) {
                    autStr.add(aut.get(i).toString());
                }
                livre.setAuteur(autStr);
                livre.setImage((String) ((JSONObject) js.get("imageLinks")).get("thumbnail"));
                response.add(livre);
            }
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
            if (result.size() > 1) {
                int j = 0;
                String imageUri = "";
                while (i < result.size()) {
                    titre = new TextView(getApplicationContext());
                    titre.setText(result.get(i).getTitre());
                    titre.setId(j);
                    layout.addView(titre);
                    j += 1;
                    auteur = new TextView(getApplicationContext());
                    String auteurs = "";
                    for (String res : result.get(i).getAuteur()) {
                        auteurs += res + "   ";
                    }
                    auteur.setText(auteurs);
                    auteur.setId(j);
                    layout.addView(auteur);
                    j += 1;
                    image = new ImageView(getApplicationContext());
                    imageUri = result.get(i).getImage();
                    image.setId(j);
                    layout.addView(image);
                    Picasso.get().load(imageUri).into((ImageView) findViewById(j));
                    j += 1;
                    i++;
                }
            }
        }
    }
    public void go(View v) {
        String recherche = (((EditText) (findViewById(R.id.search))).getText().toString());
        Log.d("rat", recherche);
        Intent i = new Intent(this,Recherche.class);
        i.putExtra("recherche",recherche);
        startActivity(i);
    }
}