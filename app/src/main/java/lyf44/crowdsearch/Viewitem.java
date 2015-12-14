package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class Viewitem extends Activity {

    private MobileServiceClient mClient;
    private MobileServiceTable<USERS1> mUser;
    private int userid;
    private String email="luyunfan44@gmail.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewitem);

        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mUser = mClient.getTable(USERS1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        TextView itemname = (TextView) findViewById(R.id.textView26);
        TextView itemlocation = (TextView) findViewById(R.id.textView28);
        String name = getIntent().getStringExtra("name");
        String location = getIntent().getStringExtra("location");
        userid = getIntent().getIntExtra("userid", 0);
        itemname.setText(name);
        itemlocation.setText(location);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    ArrayList<USERS1> result = mUser.where().field("UserID").eq(userid).execute().get();
                    String a = result.get(0).getEmail();
                    email = a;
                } catch (Exception exception) {
                    email = "luyunfan44@gmail.com";
                }
                return null;
            }
        }.execute();

    }

    public void message(View v)
    {
        composeEmail(email, "Crowdsearch Message");
        finish();
    }
    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent.createChooser(intent, "Send Email"));
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
