package lyf44.crowdsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class Viewlostitems extends Fragment {

    private MobileServiceClient mClient;
    private MobileServiceTable<LOST1> mTable;
    private RequestAdapter mAdapter;
    private getrequest request;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mClient = new MobileServiceClient(
                    "https://crowdsearch11.azure-mobile.net/",
                    "VvBdqlQPkUtbJhLggjwhhvkhSSXYuV90",getActivity()
                    );
            mTable = mClient.getTable("LOST1",LOST1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_viewlostitems, container, false);
        ImageButton mbutton = (ImageButton) v.findViewById(R.id.imageButton3fl);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomenu();
            }
        });

        ListView Request = (ListView) v.findViewById(R.id.listView);
        mAdapter = new RequestAdapter(getActivity(), R.layout.listview);
        Request.setAdapter(mAdapter);
        request = new getrequest();
        request.execute();
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent viewitem = new Intent (getActivity(), Viewitem.class);
                LOST1 item = (LOST1) parent.getItemAtPosition(position);
                viewitem.putExtra("name", item.Item);
                viewitem.putExtra("location", item.Place);
                viewitem.putExtra("userid", item.UserID);
                startActivity(viewitem);
            }
        };
        Request.setOnItemClickListener(mMessageClickedHandler);

        return v;
    }

    public static Viewlostitems newInstance(String text) {

        Viewlostitems f = new Viewlostitems();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
        Intent appmenu = new Intent(getActivity(), Appmenu.class);
        startActivity(appmenu);
    }

}


