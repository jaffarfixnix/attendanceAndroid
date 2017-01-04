package attendance.fixnix.com.attendanceapp.attendanceActivity;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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


    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_earleave);
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
                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, monthOfYear);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDates = Calendar.getInstance();
                newDates.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDates.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));




//        long diff = newCalendar.getTimeInMillis() - newCalendar.getTimeInMillis();
//        long days = diff / (24 * 60 * 60 * 1000);
//        Log.i("Tag", String.valueOf(days));
//        String strThatDay = fromDateEtxt.getText().toString();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        Date d = null;
//        try {
//            d = formatter.parse(strThatDay);//catch exception
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Calendar thatDay = Calendar.getInstance();
//        thatDay.setTime(d);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    public int getDaysDifference(Date fromDateEtxt, Date toDateEtxt)
    {
        if(fromDateEtxt==null||toDateEtxt==null)
            return 0;

        return (int)( (fromDateEtxt.getTime() - toDateEtxt.getTime()) / (1000 * 60 * 60 * 24));
    }
    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }


}


