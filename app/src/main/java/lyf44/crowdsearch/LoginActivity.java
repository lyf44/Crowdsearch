package lyf44.crowdsearch;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity /*extends PlusBaseActivity*/ extends Activity {//implements LoaderCallbacks<Cursor>

    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //find buttons
        login = (Button) findViewById(R.id.loginbutton);
        signup = (Button) findViewById(R.id.signupbutton);

        //set listeners
        login.setOnClickListener(new loginListen());
        signup.setOnClickListener(new signupListen());

    }
    private class signupListen implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,Signup.class);
            startActivity(intent);
            finish();
        }
    }

    private class loginListen implements OnClickListener{
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            //1. popup the dialog window (waiting for authorization)
            //2. check the text field (new therad)
            //3. go to main page with data in intent (handler)
        }
    }
}

