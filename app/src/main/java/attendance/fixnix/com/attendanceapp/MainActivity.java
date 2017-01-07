package attendance.fixnix.com.attendanceapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    static Button notifCount;
    static int mNotifCount = 0;
    String Title = "FIXNIX";
    String Tag ="Sucessfully Created Entity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_main, new TimeLineActivity()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawer.closeDrawers();


                if(menuItem.getItemId()==R.id.nav_logout){
                    Intent intHome = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intHome);
                    finish();
                }


                return false;
            }

        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        View count = menu.findItem(R.id.badge).getActionView();
//        notifCount = (Button) count.findViewById(R.id.notif_count);
//        notifCount.setText(String.valueOf(mNotifCount));


        return true;
    }


    private void setNotifCount(int count){
        mNotifCount = count;
        invalidateOptionsMenu();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
//            String title="Notification !!!";
//            int icon = R.drawable.icon_notifi;
//            long when = System.currentTimeMillis();
//
//            Notification notification = new Notification(icon, title, when);
//
//            NotificationManager mNotificationManager =
//                    (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//            RemoteViews contentView = new RemoteViews
//                    (getPackageName(), R.layout.activity_notification);
//            contentView.setImageViewResource(R.id.image,
//                    R.drawable.icon_notifi);
//            contentView.setTextViewText(R.id.title, title);
//            contentView.setTextViewText(R.id.text,
//                    "This is a custom layout");
//            notification.contentView = contentView;
//
//            Intent notificationIntent = new Intent(this, MainActivity.class);
//            PendingIntent contentIntent = PendingIntent.getActivity(this,0,
//                    notificationIntent, 0);
//            notification.contentIntent = contentIntent;
//
//            notification.flags |=
//                    Notification.FLAG_NO_CLEAR; //Do not clear  the notification
//            notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
//            notification.defaults |= Notification.DEFAULT_VIBRATE;//Vibration
//            notification.defaults |= Notification.DEFAULT_SOUND; // Sound
//
//            mNotificationManager.notify(1, notification);//

//            NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification notify=new Notification.Builder
//                    (getApplicationContext()).setContentTitle(Title).setContentText(Tag).setSmallIcon(R.drawable.icon_notifi).build();
//
//            notify.flags |= Notification.FLAG_AUTO_CANCEL;
//            notif.notify(0, notify);
//            notif.notify();
            Intent intent = new Intent(MainActivity.this, CustomListActivity.class);
            startActivity(intent);
            return true;
        }
//        if (id == R.id.action_logout) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
