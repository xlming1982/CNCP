package cn.com.cnpc.mainpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.com.cnpc.R;

/**
 * Created by dawn on 16/1/25.
 */
public class ParentMenuAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mMenus;

    public ParentMenuAdapter(Context context, String[] menus) {
        mContext = context;
        mMenus = menus;
    }

    @Override
    public int getCount() {
        return mMenus.length;
    }

    @Override
    public Object getItem(int position) {
        return mMenus[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_parentmemu, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.mmlv_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(mMenus[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
    }

}
