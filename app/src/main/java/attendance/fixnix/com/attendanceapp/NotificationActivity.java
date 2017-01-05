package attendance.fixnix.com.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import attendance.fixnix.com.attendanceapp.R;

/**
 * Created by akila on 4/1/17.
 */

public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_notification);
        toolbar = (Toolbar) findViewById(R.id.toolbar_notfication);
        setSupportActionBar(toolbar);
        setTitle("NOTIFICATION");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}
