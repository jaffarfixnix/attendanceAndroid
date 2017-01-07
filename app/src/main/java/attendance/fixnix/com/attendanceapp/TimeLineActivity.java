package attendance.fixnix.com.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import attendance.fixnix.com.attendanceapp.attendanceActivity.EarnedLeave;
import attendance.fixnix.com.attendanceapp.attendanceActivity.SickLeave;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by akila on 3/1/17.
 */

public class TimeLineActivity extends Fragment {
    private Context context;
    private TextView text_sick,txt_earned,txt_lop;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SaveInstanceState){
        View rootViewe = inflater.inflate(R.layout.activity_timeline,container,false);
        context = this.getActivity();

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);
        final String storedusername = pref.getString("username", null);

        text_sick = (TextView)rootViewe.findViewById(R.id.txt_sickleave);
        txt_earned = (TextView)rootViewe.findViewById(R.id.text_earned);
        txt_lop = (TextView)rootViewe.findViewById(R.id.text_lop);

        txt_earned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EarnedLeave.class);
                startActivity(intent);
            }
        });
        text_sick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SickLeave.class);
                startActivity(intent);
            }
        });


        try {
//            new LeaveApi().execute();

        }catch (Exception e){
            e.printStackTrace();
        }
//        TextView txt =(TextView)rootViewe.findViewById(R.id.txtOut_empName);
//        txt.setText(storedusername);
        return rootViewe;
    }
    class LeaveApi extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... params) {
            SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            String empId = pref.getString("tokenid", "47e83dd75b338254d5b7d4150");

//            final String id = getIntent().getStringExtra(storedusername);
            Log.i("TagToken",String.valueOf(empId));
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://52.172.182.61:3000/leaves/v1/leave/2147483647" +empId);
//            Log.i("Tag",String.valueOf(id));
            Log.i("Tag Httpget",httpGet.getURI().toString());
            HttpResponse resp = null;
            try{

                resp = httpClient.execute(httpGet);
                Log.i("tag Httpres ",String.valueOf(resp));

            }
            catch (IOException e){
                e.printStackTrace();
            }
            HttpEntity entityResponse = resp.getEntity();
            Log.i("Tag Entityresp",String.valueOf(entityResponse));
            String value = null;

            try{
                value= EntityUtils.toString(entityResponse);
                Log.i("Tag Entityutil",String.valueOf(value));
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return value;
        }
        protected void onPostExecute(String result){

            try {
                JSONObject jsonobject = new JSONObject(result);
                JSONObject object = jsonobject.getJSONObject("data");

                String sickleave = object.getString("sickLeave");
                String sickleavetype = object.getString("sickLeaveTypeId");
                String earnleave = object.getString("earnedLeave");
                String earnleaveType = object.getString("earnedLeaveTypeId");
                String totalleave = object.getString("totalLeave");

                Log.i("Tag",String.valueOf(object));



            }
            catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

}