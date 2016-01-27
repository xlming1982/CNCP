package cn.com.cnpc.mainpage.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import cn.com.cnpc.R;
import cn.com.cnpc.mainpage.seminar.po.MenuEntity;

/**
 * Created by dawn on 16/1/25.
 */
public class ChildMenuAdapter extends BaseAdapter {

    private Context mContext;
    private String mParentMenu;
    private Map<String, ArrayList<MenuEntity>> mChildMenus;

    public ChildMenuAdapter(Context context, String parentMenu, Map<String, ArrayList<MenuEntity>> childMenus) {
        mContext = context;
        mParentMenu = parentMenu;
        mChildMenus = childMenus;
    }

    @Override
    public int getCount() {
        return mChildMenus.get(mParentMenu).size();
    }

    @Override
    public Object getItem(int position) {
        return mChildMenus.get(mParentMenu).get(position);
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
            convertView = inflater.inflate(R.layout.item_childmenu, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.mmlv_title);
            viewHolder.checkbox = (ImageView) convertView.findViewById(R.id.menu_checkbox);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ArrayList<MenuEntity> currentChilds = mChildMenus.get(mParentMenu);

        viewHolder.title.setText(currentChilds.get(position).getTitle());


        if (currentChilds.get(position).isChecked()) {
            viewHolder.checkbox.setImageResource(R.drawable.tick);
        } else {
            viewHolder.checkbox.setImageDrawable(null);
        }

        return convertView;
    }

    public void setParentMenu(String parentMenu) {
        mParentMenu = parentMenu;

        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView title;
        ImageView checkbox;
    }

}
