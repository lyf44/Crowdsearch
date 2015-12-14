package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Viewrequest extends Activity {

    private MobileServiceClient mClient;
    private MobileServiceTable<LOST1> mTable;
    //private RequestAdapter1 mAdapter1;
    private RequestAdapter mAdapter;
    private ArrayList<LOST1> arraylist;
    private String dummy;
    private getrequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrequest);

        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",
                    this);
            mTable = mClient.getTable("LOST",LOST1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ImageButton mbutton = (ImageButton) findViewById(R.id.imageButton3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomenu();
            }
        });

        //arraylist = new ArrayList<LOST1>();
        //setListData();

        ListView Request = (ListView) findViewById(R.id.listView);
        mAdapter = new RequestAdapter(this, R.layout.listview);
        Request.setAdapter(mAdapter);
        request = new getrequest();
        request.execute();
        //createAndShowDialog("Succeed", "Succeed");

    }

    private class getrequest extends AsyncTask<Void, Void, LOST1> {

        private LOST1 item;
        private List<LOST1> result;
        @Override
        protected LOST1 doInBackground(Void... params){
            try {
                result = mTable.execute().get();
                //dummy = result.get(0).getName();
            } catch (Exception exception) {
                //createAndShowDialog(exception, "Error");
                //dummy = "lalala";
            }
            return item;
        }

        @Override
        protected void onPostExecute(LOST1 item) {
            //LOST1 sched = result.get(1);
            //createAndShowDialog(dummy,"title");
            mAdapter.clear();
            for (LOST1 data : result) {
            mAdapter.add(data);}
        }
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
    public void gotomenu(){
        Intent appmenu = new Intent(this, Appmenu.class);
        startActivity(appmenu);
    }


    /*public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final LOST1 sched = new LOST1();*/

            /******* Firstly take data in model object ******/
            //sched.setName("Name"+i);
            //sched.setPlace("Place"+i);

            /******** Take Model Object in ArrayList **********/
            /*arraylist.add( sched );
        }

    }*/

}
