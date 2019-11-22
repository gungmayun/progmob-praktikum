package com.example.example.sewain;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText edt_email, edt_password;
    Button btn_login;
    DatabaseHelper myDb;
    private String JSON_STRING;
    String email;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_login = (Button) view.findViewById(R.id.btn_login);
        edt_email = (EditText) view.findViewById(R.id.et_email);
        edt_password = (EditText) view.findViewById(R.id.et_password);

        myDb = new DatabaseHelper(requireContext());

        edt_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    loginSharedPreferences();
                    return true;
                }
                return false;
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSharedPreferences();
            }
        });

        return view;
    }

    private void loginSharedPreferences(){
        /* Mereset semua Error dan fokus menjadi default */
        edt_email.setError(null);
        edt_password.setError(null);
        View fokus = null;
        boolean cancel = false;

        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();

        /* Jika form email kosong atau TIDAK memenuhi kriteria di Method cekUser(), maka Set error
         *  di Form Email dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(email)){
            edt_email.setError("This field is required");
            fokus = edt_email;
            cancel = true;
        }else if(!cekUser(email)){
            edt_email.setError("This Email is not found");
            fokus = edt_email;
            cancel = true;
        }

        /* Sama syarat percabangannya dengan Email seperti di atas. Bedanya ini untuk Form Password*/
        if (TextUtils.isEmpty(password)){
            edt_password.setError("This field is required");
            fokus = edt_password;
            cancel = true;
        }else if (!cekPassword(password,email)){
            edt_password.setError("This password is incorrect");
            fokus = edt_password;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    /** Menuju ke Main2Activity dan Set User dan Status sedang login, di Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(requireActivity().getBaseContext(),edt_email.getText().toString());
        Preferences.setLoggedInStatus(requireActivity().getBaseContext(),true);
        startActivity(new Intent(requireActivity().getBaseContext(),Main2Activity.class));
        requireActivity().finish();
    }

//    private void getEmail(){
//        JSONObject jsonObject = null;
//        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
//        try {
//            jsonObject = new JSONObject(JSON_STRING);
//            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
//
//            for(int i = 0; i<result.length(); i++){
//                JSONObject jo = result.getJSONObject(i);
//                email = jo.getString(Konfigurasi.TAG_EMAIL);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password, String user){
        Boolean chk_pass = false;
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tabel_login where username = '" + user +"' and password = '" + password +"'", null);
        if (res.getCount()>0){
            chk_pass = true;
        }
        return password.equals(Preferences.getRegisteredPass(requireActivity().getBaseContext()));
//        return chk_pass;
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        Boolean chk_pass = false;
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tabel_login where username = '" + user + "'", null);
        if (res.getCount()>0){
            chk_pass = true;
        }
//        return chk_pass;
        return user.equals(Preferences.getRegisteredUser(requireActivity().getBaseContext()));
    }

}
