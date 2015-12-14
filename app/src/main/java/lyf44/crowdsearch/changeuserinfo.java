package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;


public class changeuserinfo extends Activity {

    private String email;
    private String firstname;
    private String lastname;
    private String useremail;
    private USERS1 user;
    private String password;
    private String password1;
    private MobileServiceClient mClient;
    private MobileServiceTable<USERS1> mUser;
    private EditText cuiemail;
    private EditText cuifirstname;
    private EditText cuilastname;
    private EditText cuipassword;
    private EditText cuipassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeuserinfo);
        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mUser = mClient.getTable(USERS1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        useremail = pref.getString("Email", "Email");
        cuiemail = (EditText) findViewById(R.id.cuiinputemail);
        cuifirstname = (EditText) findViewById(R.id.cuieditText9);
        cuilastname = (EditText) findViewById(R.id.cuieditText11);
        cuipassword = (EditText) findViewById(R.id.editText12);
        cuipassword1= (EditText) findViewById(R.id.editText13);
        cuiemail.setText(useremail);
        user  = new USERS1();
        new AsyncTask<Void, Void, USERS1>() {
            @Override
            protected USERS1 doInBackground(Void... params) {
                try {
                    MobileServiceList<USERS1> result = mUser.where().field("Email").eq(useremail).execute().get();
                    user = result.get(0);
                } catch (Exception exception) {

                }
                return user;
            }
            protected void onPostExecute(USERS1 user)
            {
                cuifirstname.setText(user.getFirstName());
                cuilastname.setText(user.getLastName());
                cuipassword.setText(user.getPassWord());
            }
        }.execute();
        Button cuifinish = (Button) findViewById(R.id.cuibutton11);
        cuifinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void finish()
    {
        email = cuiemail.getText().toString();
        firstname = cuifirstname.getText().toString();
        lastname = cuilastname.getText().toString();
        password = cuipassword.getText().toString();
        password1 = cuipassword1.getText().toString();
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        if (password.equals(password1))
        {
            user.setPassWord(password);
        }
        else
        {
            createAndShowDialog("Passwords do not match", "Error");
            return;
        }
        try {
            mUser.update(user);
        } catch (Exception exception) {
            createAndShowDialog(exception, "Error");}
        goback();
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

    public void goback()
    {
        Intent returnintent = new Intent(this, Profile.class);
        startActivity(returnintent);
    }




}
