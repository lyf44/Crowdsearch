package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class Profile extends FragmentActivity implements Enterpassword.DialogListener {

    private MobileServiceClient mClient;
    private MobileServiceTable<USERS1> mUser;
    private String useremail;
    private ArrayList<USERS1> result;
    private String firstname;
    private String lastname;
    private String Name;
    private String password;
    private EditText email;
    private EditText name;
    //private DialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mUser = mClient.getTable(USERS1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        name = (EditText) findViewById(R.id.editText5);
        email= (EditText) findViewById(R.id.editText6);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        useremail = pref.getString("Email","Email");
        new AsyncTask<Void, Void, String>() {
            private String a;
            @Override
            protected String doInBackground(Void... params) {
                try {
                    result = mUser.where().field("Email").eq(useremail).execute().get();
                    firstname = result.get(0).getFirstName();
                    lastname = result.get(0).getLastName();
                    a = firstname + " " + lastname;
                } catch (Exception exception) {
                    a = "Error";
                }
                return a;
            }
            @Override
            protected void onPostExecute(String a)
            {
                Name = a;
                name.setText(Name);
                email.setText(useremail);
            }
        }.execute();


    }

    public void accountsettings(View v)
    {
        DialogFragment dialog = new Enterpassword();
        dialog.show(getSupportFragmentManager(), "DialogFragment");
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        EditText inputpassword = (EditText) dialogview.findViewById(R.id.editText8password);
        password = inputpassword.getText().toString();
        if (password.equals(result.get(0).getPassWord()))
        {
            changeuserinfo();
        }
        else
        {
            createAndShowDialog("This password is not correct", "Error");
        }
    }

    public void changeuserinfo()
    {
        Intent changeuserinfo = new Intent(Profile.this,changeuserinfo.class);
        startActivity(changeuserinfo);
    }

    public void menu3(View v)
    {
        Intent menu = new Intent (this, Appmenu.class);
        startActivity(menu);
    }
}