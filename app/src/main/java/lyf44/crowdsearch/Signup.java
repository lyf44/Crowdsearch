package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;


public class Signup extends Activity {

    EditText inputEmail;
    EditText inputPassword;
    EditText inputFirstName;
    EditText inputLastName;
    EditText inputPassword2;
    MobileServiceClient mClient;
    MobileServiceTable<USERS1> mUser;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mUser = mClient.getTable(USERS1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Button ok = (Button) findViewById(R.id.button11);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }
    public void signup()
    {
        inputEmail = (EditText) findViewById(R.id.inputemail);
        inputPassword = (EditText) findViewById(R.id.editText7);
        inputPassword2 = (EditText) findViewById(R.id.editText10);
        inputFirstName = (EditText) findViewById(R.id.editText9);
        inputLastName = (EditText) findViewById(R.id.editText11);
        USERS1 user = new USERS1();
        if (inputPassword.getText().toString().equals(inputPassword2.getText().toString()) )
        {
            user.PassWord = inputPassword.getText().toString();
        }
        else
        {
            createAndShowDialog("Passwords do not match", "Error");
            return;
        }
        email = inputEmail.getText().toString();
        user.Email = email;
        user.FirstName = inputFirstName.getText().toString();
        user.LastName = inputLastName.getText().toString();
        try
        {
            mUser.insert(user);
            createAndShowDialog("Succeed","Succeed");
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            //editor.putInt("UserId",userid);
            editor.putString("Email", email);
            editor.commit();
            gotomainactivity();
        }catch (Exception exception) {
            createAndShowDialog(exception, "Error");}

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
    public void gotomainactivity()
    {
        Intent mainactivity = new Intent(this, MainActivity.class);
        mainactivity.putExtra("User", inputEmail.getText().toString());
                startActivity(mainactivity);
        finish();
    }



}