package cn.com.cnpc.mainpage.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.com.cnpc.R;

/**
 * Created by dawn on 16/1/21.
 */
public class MainMenuGVAdapter extends BaseAdapter {

    private Context mContext;

    public MainMenuGVAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_mm_gv, null);

            viewHolder.item = (RelativeLayout) convertView.findViewById(R.id.mmgv_item);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.mmgv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.mmgv_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DisplayMetrics metrics = mContext.getApplicationContext()
                .getResources().getDisplayMetrics();
        int screenWidthPx = metrics.widthPixels;
        int menuWidth = screenWidthPx - mContext.getResources().getDimensionPixelSize(R.dimen.menu_padding);
        int itemMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mmgv_item_margin);
        int itemWidth = (menuWidth - 4 * 2 * itemMargin) / 4;

        LinearLayout.LayoutParams itemParams = (LinearLayout.LayoutParams) viewHolder.item.getLayoutParams();
        itemParams.width = itemWidth;
        itemParams.height = itemWidth;

        itemParams.setMargins(itemMargin, itemMargin, itemMargin, itemMargin);

        switch (position) {
            case 0:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("穿跨越");

                break;
            case 1:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("水工保护");

                break;
            case 2:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("警示牌");

                break;
            case 3:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("穿越桩");

                break;
            case 4:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("转角桩");

                break;
            case 5:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("阴保桩");

                break;
            case 6:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("第三方管道");

                break;
            case 7:
                viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
                viewHolder.title.setText("阀室/站场");

                break;
        }

        return convertView;
    }

    private static class ViewHolder {
        RelativeLayout item;
        ImageView image;
        TextView title;
    }

}
