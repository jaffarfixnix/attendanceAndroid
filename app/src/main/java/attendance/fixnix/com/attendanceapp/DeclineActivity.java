package attendance.fixnix.com.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by akila on 6/1/17.
 */

public class DeclineActivity extends AppCompatActivity

    {
        private Toolbar toolbar;
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_decline);
        toolbar = (Toolbar) findViewById(R.id.toolbar_decline);
        setSupportActionBar(toolbar);
        setTitle("LEAVE LETTER");
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