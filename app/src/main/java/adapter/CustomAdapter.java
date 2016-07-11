package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import astoundtechnology.customlvjsonparse.R;
import model.Country;

/**
 * Created by Astound Rushi on 04-07-2016.
 */
public class CustomAdapter extends BaseAdapter {
    List<Country> clist;
    Context context;
    LayoutInflater inflater;
    private DisplayImageOptions options;

    public CustomAdapter(List<Country> country, Context c) {
        clist = country;
        context = c;
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .showImageOnFail(R.drawable.temp_img)
                .showImageForEmptyUri(R.drawable.temp_img)
                .displayer(new RoundedBitmapDisplayer(0)).build();
    }

    @Override
    public int getCount() {
        return clist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView country_flag;
        TextView country_name;
        TextView country_pop;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom = inflater.inflate(R.layout.custom_row, parent, false);

        Holder h = new Holder();

        h.country_name = (TextView) custom.findViewById(R.id.tV_Country);
        h.country_pop = (TextView) custom.findViewById(R.id.tV_population);

        h.country_flag = (ImageView) custom.findViewById(R.id.iV_Flag);

        h.country_name.setText(clist.get(position).getName());
        h.country_pop.setText(clist.get(position).getPopulation());

        ImageLoader.getInstance().displayImage(clist.get(position).getFlagUrl(), h.country_flag, options);
        return custom;
    }
}
