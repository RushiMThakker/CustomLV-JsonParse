package astoundtechnology.customlvjsonparse;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomAdapter;
import service.WebService;
import model.Country;

import org.json.JSONArray;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    private List<Country> countryList = new ArrayList<Country>();
    private String URL = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    private ProgressDialog mProgressDialog;
    private ListView listView;
    private GetCountryList getCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.lV_country);
        getCountryList = new GetCountryList();
        getCountryList.execute();
    }

    private class GetCountryList extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Home.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                WebService json_call = new WebService();
                String jsonResponse = json_call.getJSONFROMURL(URL);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray countryArray = jsonObject.getJSONArray("worldpopulation");
                for (int i = 0; i < countryArray.length(); ++i) {
                    JSONObject country = countryArray.getJSONObject(i);
                    Country current_country = new Country();
                    current_country.setName(country.getString("country"));
                    current_country.setFlagUrl(country.getString("flag"));
                    current_country.setPopulation(country.getString("population"));
                    countryList.add(current_country);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mProgressDialog.dismiss();
            setData();
        }
    }

    public void setData() {
        CustomAdapter customAdapter = new CustomAdapter(countryList, Home.this);
        listView.setAdapter(customAdapter);
    }
}
