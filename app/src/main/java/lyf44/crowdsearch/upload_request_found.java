package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.microsoft.windowsazure.notifications.NotificationsManager;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class upload_request_found extends Activity implements AdapterView.OnItemSelectedListener {

    EditText inputitem;
    EditText inputlocation;
    Spinner inputcolour;
    Spinner inputbrand;
    EditText inputremark;
    private MobileServiceClient mClient;
    private MobileServiceTable<FOUND1> mtable;
    private int RecordIndex;
    private String colour;
    private boolean success;
    private int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_request_found);
        NotificationsManager.handleNotifications(this, "711200784631", MyHandler.class);
        inputitem = (EditText) findViewById(R.id.inputitemyoufound);
        inputlocation = (EditText) findViewById(R.id.editTextf);
        inputcolour = (Spinner) findViewById(R.id.spinnerf);
        inputbrand = (Spinner) findViewById(R.id.spinner2f);
        inputremark = (EditText) findViewById(R.id.editText2f);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colour_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        inputcolour.setAdapter(adapter);
        inputcolour.setOnItemSelectedListener(this);

            try {
                mClient = new MobileServiceClient(
                        "https://crowdsearch11.azure-mobile.net/",
                        "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                        this);
                mtable = mClient.getTable(FOUND1.class);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = pref.getInt("UserId",3);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.WHITE);
        colour = parent.getItemAtPosition(pos).toString();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        colour = "red";
    }

    public void uploadf(View v)
    {
        /*try {
            MobileServiceList<FOUND1> result = mtable.orderBy("RecordIndexF", QueryOrder.Descending).execute().get();
            RecordIndex = result.get(0).RecordIndexF;
        }catch(Exception e)
        {}*/
        final FOUND1 item = new FOUND1();
        item.Item = inputitem.getText().toString();
        item.Place= inputlocation.getText().toString();
        item.Remark = inputremark.getText().toString();
        item.Colour = colour;
        item.UserID = userid;
        item.Date = "dummydate";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        item.Date =datetime;

        //item.RecordIndexF = RecordIndex+1;

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try{
                        mtable.insert(item);
                        success = true;
                    }catch(Exception exception){}
                return success;
            }
            @Override
             protected void onPostExecute(final Boolean success){
                if (success) {
                    createAndShowDialog("Request Uploaded", "Notitle");
                    gotomainmenu();
                } else {
                    createAndShowDialog("Error, please try again", "Error");
                }
            }
        }.execute();
    }

    private void createAndShowDialog(Exception exception, String title) {
        createAndShowDialog(exception.toString(), title);
    }

    private void createAndShowDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        if (!title.equals("Notitle")) {
            builder.setTitle(title);
        }
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    public void menu2(View view){
        Intent appmenu = new Intent(this, Appmenu.class);
        startActivity(appmenu);
    }
    public void gotomainmenu()
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

}
