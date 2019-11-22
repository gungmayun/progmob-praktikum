package com.example.example.sewain;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

//    private static final int PERMISSION_REQUEST_CODE = 100;

    Button btnRegister;
    EditText edtNama, edtEmail, edtPassword, edtRePassword;
    DatabaseHelper myDb;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        myDb = new DatabaseHelper(getActivity());
        btnRegister = (Button) view.findViewById(R.id.btn_register);

//        btnRegister.setOnClickListener(this);

        edtNama = (EditText) view.findViewById(R.id.et_name);
        edtEmail = (EditText) view.findViewById(R.id.et_email);
        edtPassword = (EditText) view.findViewById(R.id.et_password);
        edtRePassword = (EditText) view.findViewById(R.id.et_repassword);
        edtRePassword.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    registerSharedPreferences();
                    return true;
                }
                return false;
            }
        });
        myDb = new DatabaseHelper(requireContext());
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSharedPreferences();
                addMitra();
//                registerExternalStorage();
            }
        });
        return view;
    }
    private void addMitra() {

        final String nama = edtNama.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        class AddMitra extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(requireContext(), "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... view) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_MTA_NAMA, nama);
                params.put(Konfigurasi.KEY_MTA_EMAIL, email);
                params.put(Konfigurasi.KEY_MTA_PASSWORD, password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_MTA, params);
                return res;
            }
        }
        AddMitra am = new AddMitra();
        am.execute();
    }
//    @Override
//    public void onClick(View v) {
//        if (v == btnRegister) {
//            addMitra();
//        }
//    }


//    public void AddData() {
//
//        btnRegister.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean registerSharedPreferences = myDb.insertUsername(edtNama.getText().toString(),
//                                edtEmail.getText().toString(),
//                                edtPassword.getText().toString(),
//                                edtRePassword.getText().toString() );
//                        if(registerSharedPreferences == true) {
//                            Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                            Toast.makeText(getActivity(),"Data Not Inserted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    /** Men-check inputan User dan Password dan Memberikan akses ke Main2Activity */
    private void registerSharedPreferences(){
        /* Mereset semua Error dan fokus menjadi default */
        edtNama.setError(null);
        edtEmail.setError(null);
        edtPassword.setError(null);
        edtRePassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form Nama Lengkap, Email, Password, Repassword dengan variable baru bertipe String*/
        String repassword = edtRePassword.getText().toString();
        String user = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String name = edtNama.getText().toString();

        /* Jika form name kosong, maka Set error di Form-
         * name dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(name)){
            edtNama.setError("This field is required");
            fokus = edtNama;
            cancel = true;
        }

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser(), maka Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)){
            edtEmail.setError("This field is required");
            fokus = edtEmail;
            cancel = true;
        }else if(cekUser(user)){
            edtEmail.setError("This Username is already exist");
            fokus = edtEmail;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword, maka
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)){
            edtPassword.setError("This field is required");
            fokus = edtPassword;
            cancel = true;
        }else if (!cekPassword(password,repassword)){
            edtPassword.setError("This password is incorrect");
            fokus = edtPassword;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  muncul Toast dan menghapus isi edittext dan Set Name, User, dan Password untuk data yang terdaftar */
        if (cancel){
            fokus.requestFocus();
        }else{
            boolean registerSharedPreferences = myDb.insertUsername(user, password,name,repassword);
            if(registerSharedPreferences) {
                Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getActivity(), "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
            Preferences.setKeyNameTeregister(requireActivity().getBaseContext(),name);
            Preferences.setRegisteredUser(requireActivity().getBaseContext(),user);
            Preferences.setRegisteredPass(requireActivity().getBaseContext(),password);

//            edtNama.getText().clear();
//            edtEmail.getText().clear();
//            edtPassword.getText().clear();
//            edtRePassword.getText().clear();

//            Toast.makeText(requireActivity(), "Registrasi berhasil!", Toast.LENGTH_LONG).show();
//            requireActivity().finish();
        }
    }

    /** True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(requireContext()));
    }

//    private void registerExternalStorage(){
//        if (!edtNama.getText().toString().isEmpty()) {
//            String state = Environment.getExternalStorageState();
//            if (Environment.MEDIA_MOUNTED.equals(state)) {
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (checkPermission()) {
//                        File sdcard = Environment.getExternalStorageDirectory();
//                        File dir = new File(sdcard.getAbsolutePath() + "/text/");
//                        dir.mkdir();
//                        File file = new File(dir, "sample.txt");
//                        FileOutputStream os = null;
//                        String nama = edtNama.getText().toString() + "\n";
//                        try {
//                            os = new FileOutputStream(file);
//                            os.write(nama.getBytes());
//                            os.close();
//                            Toast.makeText(getActivity(), "file saved in" + dir + file, Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        requestPermission(); // Code for permission
//                    }
//                } else {
//
//                    File sdcard = Environment.getExternalStorageDirectory();
//                    File dir = new File(sdcard.getAbsolutePath() + "/text/");
//                    dir.mkdir();
//                    File file = new File(dir, "sample.txt");
//                    FileOutputStream os = null;
//                    String nama = edtNama.getText().toString() + "\n";
//                    try {
//                        os = new FileOutputStream(file);
//                        os.write(nama.getBytes());
//                        os.close();
//                        Toast.makeText(getActivity(), "file saved in" + dir + file, Toast.LENGTH_LONG).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }else{
//            Toast.makeText(getActivity(), "Masih ada kolom yang kosong mba/mas", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(getActivity(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("VALUE", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("VALUE", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }
}

