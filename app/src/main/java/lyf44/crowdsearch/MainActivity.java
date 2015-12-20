package lyf44.crowdsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.notifications.NotificationsManager;
import java.net.MalformedURLException;


public class MainActivity extends Activity {

    ImageView avator;
    ImageButton lookfor,found;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariables();
    }

    private void setVariables(){
        avator = (ImageView) findViewById(R.id.avatar);
        lookfor = (ImageButton) findViewById(R.id.lookForSomething1);
        found = (ImageButton) findViewById(R.id.foundSomeThing1);

        avator.setOnClickListener(new personalInf());
        lookfor.setOnClickListener(new lookforListen());
        found.setOnClickListener(new foundListen());
    }

    private class personalInf implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Profile.class);
            startActivity(intent);
        }
    }

    private class lookforListen implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,upload_request.class);
            startActivity(intent);
        }
    }
    private class foundListen implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,upload_request_found.class);
            startActivity(intent);
        }
    }
}
