package attendance.fixnix.com.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by akila on 6/1/17.
 */

public class CustomListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    ArrayList<Group> ExpListItems = new ArrayList<Group>();
    private String names[] = {
            "HTML",
            "CSS",
            "Java Script",
            "Wordpress"
    };

    private String desc[] = {
            "The Powerful Hypter Text Markup Language 5",
            "Cascading Style Sheets",
            "Code with Java Script",
            "Manage your content with Wordpress"
    };


    private Integer imageid[] = {
            R.drawable.ic_cast_dark,
            R.drawable.ic_cast_dark,
            R.drawable.ic_cast_dark,
            R.drawable.ic_cast_dark
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        CustomList customList = new CustomList(this, names,desc,imageid);

        listView = (ListView) findViewById(R.id.listView);
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

        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == l ){
                    Intent intent = new Intent(CustomListActivity.this, NotificationActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(getApplicationContext(),"You Clicked "+ names[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


}