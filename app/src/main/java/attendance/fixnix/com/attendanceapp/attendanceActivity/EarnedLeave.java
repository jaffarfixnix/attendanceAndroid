package attendance.fixnix.com.attendanceapp.attendanceActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import attendance.fixnix.com.attendanceapp.R;

/**
 * Created by akila on 3/1/17.
 */

public class EarnedLeave extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    public  EditText fromDateEtxt;
    public EditText toDateEtxt;
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private long milis1 ;
    private long milis2;


    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_earnedleave);
        toolbar = (Toolbar) findViewById(R.id.toolbar_earn);
        fromDateEtxt = (EditText)findViewById(R.id.txt_dateFrom);
        toDateEtxt = (EditText) findViewById(R.id.txt_dateTo);
        Button sub = (Button)findViewById(R.id.button_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        long diff = milis2 - milis1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        Log.i("Tag Day", String.valueOf(diffDays));




    }
    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.txt_dateFrom);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
//        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.txt_dateTo);
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
                fromDateEtxt.setText(dateFormatter.format(newDate1.getTime()));
                milis1 = newDate1.getTimeInMillis();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDates = Calendar.getInstance();
                newDates.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDates.getTime()));
                 milis2 = newDates.getTimeInMillis();



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


