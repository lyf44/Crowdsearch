package lyf44.crowdsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.notifications.NotificationsManager;
import java.net.MalformedURLException;


public class MainActivity extends Activity {

    public static MobileServiceClient mClient;
    protected MobileServiceTable<LOST1> mUser;
    private String Useremail;
    private TextView UserEmail;
    private int userid;
    public static final String SENDER_ID = "711200784631";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LOST1 item = new LOST1();
        //java.util.Date now = new java.util.Date();
        //item.UserID = 3;
        //item.Date = now.toString();
        //item.Place = "NorthSpine";
       // item.Item = "Waterbottle";
        //item.Colour = "black";
        //item.RecordIndexL = 3;
        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mUser = mClient.getTable(LOST1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String Useremail = pref.getString("Email","Email");
        UserEmail = (TextView) findViewById(R.id.textView22);
        UserEmail.setText(Useremail);
        NotificationsManager.handleNotifications(this,"711200784631", MyHandler.class);
    }

    public void lookingforsomething(View view){
        Intent Chaitanya = new Intent(this, upload_request.class);
        startActivity(Chaitanya);
    }
    public void foundsomething(View view){
        Intent found = new Intent(this, upload_request_found.class);
        startActivity(found);
    }

    public void menu(View view){
        Intent appmenu = new Intent(this, Appmenu.class);
        startActivity(appmenu);
    }
    private void createAndShowDialog(Exception exception, String title) {
        createAndShowDialog(exception.toString(), title);
    }

    private void createAndShowDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }



}
