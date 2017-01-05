package attendance.fixnix.com.attendanceapp.attendanceActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import attendance.fixnix.com.attendanceapp.R;

/**
 * Created by akila on 4/1/17.
 */

public class SickLeave extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    public  EditText fromDateEtxt;
    public EditText toDateEtxt;
    private TextView text_noofDay;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    SimpleDateFormat dateFormatter;
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener;
    private long milis1 ;
    private long milis2;
    protected void onCreate(Bundle SavaInstanceState){
        super.onCreate(SavaInstanceState);
        setContentView(R.layout.activity_sickleave);
        toolbar = (Toolbar) findViewById(R.id.toolbar_sick);
        text_noofDay = (TextView) findViewById(R.id.txt_sick_noday);
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
        fromDateEtxt = (EditText) findViewById(R.id.txt_sick_dateFrom);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.txt_sick_dateTo);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate= Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, monthOfYear);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTimeInMillis()));
                milis1 = newDate.getTimeInMillis();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDates = Calendar.getInstance();
                newDates.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDates.getTimeInMillis()));
                milis2 = newDates.getTimeInMillis();


                long diff = milis2 - milis1;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                Log.i("Tag Day", String.valueOf(diffDays));
                Log.i("tag",String.valueOf(diff));
                text_noofDay.setText(String.valueOf(diffDays + "DAYS"));
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

}
