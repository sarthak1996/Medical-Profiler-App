package com.example.sarthak.medicalprofiler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int tabIcons[]={
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_alarm_white_24dp,
            R.drawable.ic_insert_chart_white_24dp

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /*Creating tabs*/
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentClass tabs_create=new FragmentClass();
        tabs_create.setupViewPager(viewPager, getSupportFragmentManager());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<3;i++) {
            tabs_create.setTabIcons(tabLayout,i,tabIcons[i]);
        }

        SharedPreferences settings = getSharedPreferences("Med", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(Config.rbc);
        editor.remove(Config.blood_pressure);
        editor.remove(Config.body_temperature);
        editor.remove(Config.hemoglobin);
        editor.remove(Config.pulse);
        editor.remove(Config.wbc);
        editor.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this).
                    setTitle("About Medi Pro")
                    .setMessage("I was born for the sake of the medical electronics project assigned to my masters by Prof. Amit Sengupta.\n" +
                            "I hope that my masters keep upgrading me and hope that they are awarded with nice marks because of me.:)");
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
