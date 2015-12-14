package lyf44.crowdsearch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.microsoft.windowsazure.notifications.NotificationsManager;


public class Appmenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmenu);

        Button mlostsomething = (Button) findViewById(R.id.button2);
        mlostsomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotouploadrequest();
            }
        });

        Button mfoundsomething = (Button) findViewById(R.id.button17);
        mfoundsomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotouploadrequest1();
            }
        });
        NotificationsManager.handleNotifications(this, "711200784631", MyHandler.class);
    }

    public void viewrequest(View view){
        Intent viewrequest = new Intent(this, Viewreqest1.class);
        startActivity(viewrequest);
    }

    public void gotouploadrequest()
    {
        Intent Chaitanya = new Intent(this, upload_request.class);
        startActivity(Chaitanya);
    }

    public void gotouploadrequest1()
    {
        Intent found = new Intent(this, upload_request_found.class);
        startActivity(found);
    }

    public void signout(View v)
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("UserId", 2033);
        editor.putString("Email", "noEmail");
        editor.commit();
        Intent signout = new Intent(this,LoginActivity.class);
        startActivity(signout);
    }

    public void profile(View v)
    {
        Intent profile = new Intent(this,Profile.class);
        startActivity(profile);
    }
}
