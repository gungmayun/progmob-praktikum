package com.example.example.sewain;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ListView.OnItemClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private ListView listView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fab, container, false);
        listView =(ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(),TambahMotor.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onStart(){
        super.onStart();
    }

    private void showMotor(String json){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID);
                String nopol = jo.getString(Konfigurasi.TAG_NOPOL);
                String jenis = jo.getString(Konfigurasi.TAG_JENIS);

                HashMap<String,String> motor = new HashMap<>();

                motor.put(Konfigurasi.TAG_ID,id);
                motor.put(Konfigurasi.TAG_NOPOL,nopol);
                motor.put(Konfigurasi.TAG_JENIS,jenis);
                list.add(motor);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                requireContext(), list, R.layout.list_lihat_motor,
                new String[]{Konfigurasi.TAG_NOPOL,Konfigurasi.TAG_JENIS},
                new int[]{R.id.nopol, R.id.jenis_motor});
        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(requireContext(),"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMotor(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_ALL,Preferences.getLoggedInUser(requireActivity().getBaseContext()));
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(requireContext(), LihatDetailMotor.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String mtrId = map.get(Konfigurasi.TAG_ID).toString();
        intent.putExtra(Konfigurasi.MTR_ID,mtrId);
        startActivity(intent);
    }
}
