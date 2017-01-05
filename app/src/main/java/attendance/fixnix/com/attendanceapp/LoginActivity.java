package attendance.fixnix.com.attendanceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import attendance.fixnix.com.attendanceapp.testApi.LoginApi;
import com.mixpanel.android.mpmetrics.MixpanelAPI;



public class LoginActivity extends AppCompatActivity {
    public EditText edit_userId, edit_userPassword;
    public Button butn_signIn;
    public TextInputLayout text_userId,text_userPassword;
    MixpanelAPI mixpanel;
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_login);
        String projectToken = "4cdf8949e9b0e47ba9676193b27cc706"; // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"
       mixpanel = MixpanelAPI.getInstance(this, projectToken);

        edit_userId = (EditText)findViewById(R.id.edt_userid);
        edit_userPassword=(EditText)findViewById(R.id.edt_password);
        text_userId = (TextInputLayout)findViewById(R.id.txt_userid);

        text_userPassword = (TextInputLayout)findViewById(R.id.text_password);
        butn_signIn = (Button)findViewById(R.id.btn_signin);
//        edit_userId.addTextChangedListener(new MyTextWatcher(edit_userId));
//        edit_userPassword.addTextChangedListener(new MyTextWatcher(edit_userPassword));

        butn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_userId.getText().toString().length() < 1 || edit_userPassword.getText().toString().length() < 1) {
                    Toast.makeText(LoginActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();

                    // out of range
//                    Toast.makeText(this, getString(R.string.string_to), Toast.LENGTH_LONG).show();
                } else {
                    new MyAsyncTask().execute();

                    Toast.makeText(LoginActivity.this, "SucessFully Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        mixpanel.flush();
        super.onDestroy();
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edt_userid:
                    if (edit_userId.getText().length() == 0) {
                        text_userId.setErrorEnabled(true);
                        text_userId.setError("Enter name");

                    } else {
                        text_userId.setErrorEnabled(false);
                    }

                    break;
                case R.id.edt_password:
                    if (edit_userPassword.getText().length() == 0) {
                        text_userPassword.setErrorEnabled(true);
                        text_userPassword.setError("Enter name");
                    } else {
                        text_userPassword.setErrorEnabled(false);
                    }
                    break;
            }
        }
    }

    class MyAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            LoginApi JSONOBJECT = new LoginApi();
            JSONOBJECT.setId(edit_userId.getText().toString());
            JSONOBJECT.setPassword(edit_userPassword.getText().toString());
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", JSONOBJECT.getId());
                obj.put("password", JSONOBJECT.getPassword());
                obj.put("accessType", "Mobile");
                Log.i("Tag", String.valueOf(obj));

                String empid = obj.getString("id");

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("emplyeeid", empid);
                editor.commit();

                Log.i("Tage", String.valueOf(empid));

//                mixpanel.track("LoginActivty-oncreate Called",obj);
//                mixpanel.timeEvent("userlogin");
            } catch (JSONException e) {
                e.printStackTrace();

            }
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            final String empid = pref.getString("emplyeeid", null);
            mixpanel.track("Employe Login", obj);
            mixpanel.registerSuperProperties(obj);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://52.172.182.61:3000/users/v1/login");
            Log.i("Requestd Connection", httpPost.getURI().toString());
            StringEntity se = null;
            try {
                se = new StringEntity(obj.toString());
                Log.i("Tag", String.valueOf(se));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("content-type", "application/json");
            int a;
            int responseCode = 0;
            String responseBody = null;
            try {
                //a
                HttpResponse resp = httpClient.execute(httpPost);
                //responseCode = resp.getStatusLine().getStatusCode();
                responseBody = EntityUtils.toString(resp.getEntity());
                Log.v("VANTEST", responseBody.toString());

                return responseBody.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseBody;
        }
        protected void onPostExecute(String rstl){

            Log.i("TsagToken","sessionToken" +rstl);

            if(rstl !=  null){
                JSONObject jsonobject = null;
                try{
                    jsonobject  = new JSONObject(rstl);
                    JSONObject object = jsonobject.getJSONObject(String.valueOf(jsonobject));
                    try {
                        String token = object.getString("sessionToken");
                        Log.i("Tag token", String.valueOf(token));

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("tokenid",token);
                        editor.commit();

                    }catch (Exception e){
                        e.printStackTrace();
                    }


//                editor.putString("iduser",name);


                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            else {

            }
            Toast.makeText(LoginActivity.this, rstl, Toast.LENGTH_SHORT).show();

        }

    }
}
