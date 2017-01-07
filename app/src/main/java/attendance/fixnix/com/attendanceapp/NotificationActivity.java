package attendance.fixnix.com.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import attendance.fixnix.com.attendanceapp.R;

/**
 * Created by akila on 4/1/17.
 */

public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn_approve, btn_decline;
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_leaveletter);

        toolbar = (Toolbar) findViewById(R.id.toolbar_leaveletter);
        setSupportActionBar(toolbar);
        btn_approve = (Button)findViewById(R.id.button_accept);
        btn_decline = (Button) findViewById(R.id.button_decline);
        setTitle("LEAVE LETTER");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this,DeclineActivity.class);
                startActivity(intent);
            }
        });

    }
}
