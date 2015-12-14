package lyf44.crowdsearch;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by lyf44 on 19/5/2015.
 */
public class RequestAdapter extends ArrayAdapter<LOST1> {

    Context Context;
    int mLayoutResourceId;

    public RequestAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        Context = context;
        mLayoutResourceId = layoutResourceId;
    }
/*
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }*/
    public View getView(int position, View convertView, ViewGroup parent) {
         View v = convertView;
         //RequestHolder holder = new RequestHolder();
        LOST1 item = getItem(position);

       // First let's verify the convertView is not null
         if (v == null) {
              // This a new view we inflate the new layout
             /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.row_layout, parent, false);*/
              LayoutInflater inflater = ((Activity) Context).getLayoutInflater();
              v = inflater.inflate(mLayoutResourceId, parent, false);}
              // Now we can fill the layout with the right values
              //name.setText("test");
              //description.setText("test");

              //holder.name = name;
              //holder.description= description;
              //holder.img = img;

         v.setTag(item);
        TextView name = (TextView) v.findViewById(R.id.textView19);
        TextView description = (TextView) v.findViewById(R.id.textView20);
        ImageView img = (ImageView) v.findViewById(R.id.imageView5);
        name.setText(item.Item);
        description.setText(item.Place);

    return v;
     }
}
