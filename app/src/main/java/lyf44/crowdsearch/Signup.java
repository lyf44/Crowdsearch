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

    EditText email,password1,password2,firstname,lastname;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setVariables();

        ok.setOnClickListener(new registListen());
    }
    private void setVariables(){
        email = (EditText) findViewById(R.id.inputemail);
        password1 = (EditText) findViewById(R.id.inputPass);
        password2 = (EditText) findViewById(R.id.confirmPass);
        firstname = (EditText) findViewById(R.id.inputFirstName);
        lastname = (EditText) findViewById(R.id.inputLastName);
        ok = (Button) findViewById(R.id.button11);
    }

    private class registListen implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 1.show dialog (waiting)
            // 2.varify username and email (new therad)
            // 3.start main page (handler)

            //for testing, directly open the page
            Intent intent = new Intent();
            intent.setClass(Signup.this,MainActivity.class);
            startActivity(intent);
        }
    }
}