package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceJsonTable;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.microsoft.windowsazure.notifications.NotificationsManager;

import static android.app.PendingIntent.getActivities;
import static android.app.PendingIntent.getActivity;


public class upload_request extends FragmentActivity implements Enterpoint.DialogListener, AdapterView.OnItemSelectedListener {


    EditText inputitem;
    EditText inputlocation;
    Spinner inputcolour;
    Spinner inputbrand;
    EditText inputremark;
    EditText inputpoint;
    private MobileServiceClient mClient;
    private MobileServiceTable<LOST1> mtable;
    private int point;
    private int RecordIndex = 0;
    private String colour;
    private int userid;
    private boolean success = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_request);

        NotificationsManager.handleNotifications(this, "711200784631", MyHandler.class);

        inputitem = (EditText) findViewById(R.id.inputitemyoulost);
        inputlocation = (EditText) findViewById(R.id.editText);
        inputcolour = (Spinner) findViewById(R.id.spinner);
        inputbrand = (Spinner) findViewById(R.id.spinner2);
        inputremark = (EditText) findViewById(R.id.editText2);

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
            mtable = mClient.getTable("LOST1", LOST1.class);
        } catch (MalformedURLException e) {
            createAndShowDialog("No Internet Connection", "Error");
        }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = pref.getInt("UserId",3);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.WHITE);
        colour = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        colour = "red";
    }

    public void upload(View v) {
        ShowDialogwithButton();
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
                gotomainmenu();
            }
        });
        builder.create().show();
    }

    private void ShowDialogwithButton() {
        DialogFragment dialog = new Enterpoint();
        dialog.show(getSupportFragmentManager(), "DialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogview = dialog.getDialog();
        inputpoint = (EditText) dialogview.findViewById(R.id.editText8);
        int a = Integer.parseInt(inputpoint.getText().toString());
        point = a;
        uploadstep2();
    }

    public void gotomainmenu(){
        Intent mainActivity = new Intent(upload_request.this,MainActivity.class);
        startActivity(mainActivity);
    }
    public void menu1(View view){
        Intent appmenu = new Intent(this, Appmenu.class);
        startActivity(appmenu);
    }
    public void uploadstep2()
    {
        final LOST1 item = new LOST1();
        item.Item = inputitem.getText().toString();
        item.Place = inputlocation.getText().toString();
        item.Remark = inputremark.getText().toString();
        item.Colour = colour;
        item.Date = "dummydate";
        item.UserID = userid;
        item.Award_Points = point;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        item.Date =datetime;
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    mtable.insert(item);
                    success = true;
                } catch (Exception exception) {
                }
                return success;
            }

            @Override
            protected void onPostExecute(final Boolean success) {
                if (success) {
                    createAndShowDialog("Request Uploaded", "Notitle");
                }
                else
                {
                    createAndShowDialog("Error, please try again", "Error");
                }
            }
        }.execute();
    }
}

