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

import cn.com.cnpc.R;

/**
 * Created by dawn on 16/1/21.
 */
public class MainMenuLVAdapter extends BaseAdapter {

    private Context mContext;

    public MainMenuLVAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = inflater.inflate(R.layout.item_mm_lv, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.mmlv_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (position) {
            case 0:
                viewHolder.title.setText("专题展示");
                break;
            case 1:
                viewHolder.title.setText("站场展示");
                break;
            case 2:
                viewHolder.title.setText("书签收藏");
                break;
            default:
                break;
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView title;
    }

}
