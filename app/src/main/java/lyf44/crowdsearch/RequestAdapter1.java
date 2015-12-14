/*package lyf44.crowdsearch;

import android.app.DownloadManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lyf44 on 21/5/2015.
 */
/*public class RequestAdapter1 extends BaseAdapter {

    Context context;

    protected List<LOST1> listitem;
    LayoutInflater inflater;

    public RequestAdapter1(Context context, List<LOST1> listitem) {
        this.listitem = listitem;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return listitem.size();
    }

    public LOST1 getItem(int position) {
        return listitem.get(position);
    }

    public long getItemId(int position) {
        return listitem.get(position).getRecordIndexL();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        RequestHolder holder;
        if (v == null) {

            holder = new RequestHolder();
            v = this.inflater.inflate(R.layout.listview,
                    parent, false);

            holder.name = (TextView) v.findViewById(R.id.textView19);
            holder.description = (TextView) v.findViewById(R.id.textView20);
            holder.img = (ImageView) v.findViewById(R.id.imageView5);

            v.setTag(holder);
        } else {
            holder = (RequestHolder) v.getTag();
        }

        LOST1 item = listitem.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getPlace());

        return v;
    }
}*/
