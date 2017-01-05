package attendance.fixnix.com.attendanceapp.attendanceActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import attendance.fixnix.com.attendanceapp.LoginActivity;
import attendance.fixnix.com.attendanceapp.R;
import attendance.fixnix.com.attendanceapp.testApi.LeaveReqApi;
import attendance.fixnix.com.attendanceapp.testApi.LoginApi;

/**
 * Created by akila on 3/1/17.
 */

public class EarnedLeave extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    public  EditText fromDateEtxt,toDateEtxt,desEtxt;
    private TextView noDayEtxt;
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private long milis1 ;
    private long milis2;
    MixpanelAPI mixpanel;
    private Context context;


    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_earnedleave);
        context=this.getApplicationContext();
        String projectToken = "4cdf8949e9b0e47ba9676193b27cc706"; // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"
        mixpanel = MixpanelAPI.getInstance(this, projectToken);
        toolbar = (Toolbar) findViewById(R.id.toolbar_earn);
        noDayEtxt= (TextView)findViewById(R.id.edt_earned_noDays);
        desEtxt = (EditText)findViewById(R.id.edt_earned_descrip);
        Button sub = (Button)findViewById(R.id.button_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
                Toast.makeText(EarnedLeave.this,fromDateEtxt.getText().toString() + toDateEtxt.getText().toString() , Toast.LENGTH_SHORT).show();

            }
        });
        setSupportActionBar(toolbar);
        setTitle("EARNED LEAVE");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        findViewsById();

        setDateTimeField();






    }
    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.edt_earned_dateFrom);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
//        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.edt_earned_dateTo);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate1 = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
                newDate1.set(Calendar.YEAR, year);
                newDate1.set(Calendar.MONTH, monthOfYear);
                newDate1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                fromDateEtxt.setText(dateFormatter.format(newDate1.getTimeInMillis()));
                milis1 = newDate1.getTimeInMillis();
                Log.i("Tagmil1",String.valueOf(milis1));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDates = Calendar.getInstance();
                newDates.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDates.getTimeInMillis()));
                 milis2 = newDates.getTimeInMillis();
                Log.i("Tag",String.valueOf(milis2));

                long diff = milis2 - milis1;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                Log.i("Tag Day", String.valueOf(diffDays));
                Log.i("tag",String.valueOf(diff));
                noDayEtxt.setText(String.valueOf(diffDays + "DAYS"));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));




    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//
//
//    }


    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }

    class MyAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            LeaveReqApi JSONOBJECT = new LeaveReqApi();

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            final String empid = pref.getString("emplyeeid", null);

            Log.i("TagTokenid", String.valueOf(empid));

            JSONOBJECT.setId(empid);
            JSONOBJECT.setFromDate(fromDateEtxt.getText().toString());
            JSONOBJECT.setToDate(toDateEtxt.getText().toString());
            JSONOBJECT.setNoOfDays(noDayEtxt.getText().toString());
            JSONOBJECT.setLeaveDescription(desEtxt.getText().toString());

            JSONObject obj = new JSONObject();

            try {
//                obj.put("id", "349649001");
//                obj.put("leaveTypeId", "2");
//                obj.put("noOfDays", "2");
//                obj.put("fromDate", "12-12-2016T10:10:10");
//                obj.put("toDate", "13-12-2016T10:10:10");
//                obj.put("leaveDescription", "Not keeping well");

                obj.put("id", JSONOBJECT.getId());
                obj.put("leaveTypeId", "2");
                obj.put("noOfDays", JSONOBJECT.getNoOfDays());
                obj.put("fromDate", JSONOBJECT.getFromDate());
                obj.put("toDate", JSONOBJECT.getToDate());
                obj.put("leaveDescription", JSONOBJECT.getLeaveDescription());
                Log.i("Tag", String.valueOf(obj));
//                mixpanel.track("LoginActivty-oncreate Called",obj);
//                mixpanel.timeEvent("userlogin");
            } catch (JSONException e) {
                e.printStackTrace();

            }

            mixpanel.track("Leave Req", obj);
            mixpanel.registerSuperProperties(obj);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://52.172.182.61:3000/leaves/v1/leaveRequest");
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

        protected void onPostExecute(String rstl) {


        }

    }


    }


