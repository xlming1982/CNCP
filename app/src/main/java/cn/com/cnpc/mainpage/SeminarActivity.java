package cn.com.cnpc.mainpage;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.com.cnpc.R;
import cn.com.cnpc.mainpage.adapter.ChildMenuAdapter;
import cn.com.cnpc.mainpage.adapter.MainMenuGVAdapter;
import cn.com.cnpc.mainpage.adapter.MainMenuLVAdapter;
import cn.com.cnpc.mainpage.adapter.ParentMenuAdapter;
import cn.com.cnpc.mainpage.seminar.po.MenuEntity;

/**
 * Created by dawn on 16/1/24.
 */
public class SeminarActivity extends Activity {

    private Activity mActivity = this;

    private boolean mIsShowing;
    private boolean mIsRequesing;

    private SlidingMenu mSlideMenu;

    private LinearLayout mSeminarLl;
    private ImageView mSeminarMenuIv;
    private View mSeminarMenuView;
    private PopupWindow mSeminarMenuWindow;

    private static final String[] mMainMenu = new String[]{"人口分布", "钢管信息", "紧急服务", "应急资源", "高后果区"};
    private static final Map<String, ArrayList<MenuEntity>> mChildMenu = new HashMap<String, ArrayList<MenuEntity>>();

    private String mParentMenu;

    private ParentMenuAdapter mParentMenuAdpater;
    private ChildMenuAdapter mChildMenuAdatper;

    static {
        ArrayList<MenuEntity> l01 = new ArrayList<MenuEntity>();
        l01.add(new MenuEntity("人口分布"));
        l01.add(new MenuEntity("政府所在地"));
        l01.add(new MenuEntity("人口数量统计"));
        l01.add(new MenuEntity("沿线人口密度分布"));
        l01.add(new MenuEntity("人口密度点聚散"));
        mChildMenu.put(mMainMenu[0], l01);

        ArrayList<MenuEntity> l02 = new ArrayList<MenuEntity>();
        l02.add(new MenuEntity("年份"));
        l02.add(new MenuEntity("材质"));
        l02.add(new MenuEntity("口径"));
        l02.add(new MenuEntity("检修"));
        mChildMenu.put(mMainMenu[1], l02);

        ArrayList<MenuEntity> l03 = new ArrayList<MenuEntity>();
        l03.add(new MenuEntity("紧急服务资源分布"));
        l03.add(new MenuEntity("紧急服务统计专题图"));
        mChildMenu.put(mMainMenu[2], l03);

        ArrayList<MenuEntity> l04 = new ArrayList<MenuEntity>();
        l04.add(new MenuEntity("应急资源分布图"));
        l04.add(new MenuEntity("应急资源统计专题图"));
        l04.add(new MenuEntity("应急资源密度点聚散"));
        mChildMenu.put(mMainMenu[3], l04);

        ArrayList<MenuEntity> l05 = new ArrayList<MenuEntity>();
        l05.add(new MenuEntity("高后果区分布"));
        l05.add(new MenuEntity("高后果区风险等级"));
        mChildMenu.put(mMainMenu[4], l05);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mIsShowing = true;

        initMenuData();

        initView();

        initLeftMenu();
    }

    private void initMenuData() {

    }

    private void initView() {
        ImageView mMmIv = (ImageView) findViewById(R.id.mm_iv);
        mMmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideMenu.toggle();
            }
        });

        mSeminarMenuIv = (ImageView) findViewById(R.id.seminarmenu_image);

        mSeminarLl = (LinearLayout) findViewById(R.id.seminarmenu_ll);
        mSeminarLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSeminarMenu();
            }
        });


    }

    private void initLeftMenu() {
        DisplayMetrics metrics = mActivity.getApplicationContext()
                .getResources().getDisplayMetrics();
        int screenWidthPx = metrics.widthPixels;
        int menuWidth = screenWidthPx - mActivity.getResources().getDimensionPixelSize(R.dimen.menu_padding);

        mSlideMenu = new SlidingMenu(mActivity.getApplicationContext());
        // 滑动方向(LEFT,RIGHT,LEFT_RIGHT)
        mSlideMenu.setMode(SlidingMenu.LEFT);
        // 滑动显示SlidingMenu的范围
        mSlideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        // 菜单的宽度
        mSlideMenu.setBehindWidth(menuWidth);

        mSlideMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        // 菜单的布局文件
        mSlideMenu.setMenu(R.layout.leftmenu_main);

        GridView mMGv = (GridView) mSlideMenu.findViewById(R.id.leftmenu_gv);
        MainMenuGVAdapter gvAdapter = new MainMenuGVAdapter(mActivity.getApplicationContext());
        mMGv.setAdapter(gvAdapter);

        ViewGroup.LayoutParams mMGvParams = mMGv.getLayoutParams();

        int mMGvItemMargin = mActivity.getResources().getDimensionPixelSize(R.dimen.mmgv_item_margin);
        int mMGvHeight = 2 * ((menuWidth - 4 * 2 * mMGvItemMargin) / 4) + 4 * mMGvItemMargin;

        mMGvParams.height = mMGvHeight;

        ListView mMLv = (ListView) mSlideMenu.findViewById(R.id.leftmenu_lv);
        MainMenuLVAdapter lvAdapter = new MainMenuLVAdapter(mActivity.getApplicationContext());
        mMLv.setAdapter(lvAdapter);
    }

    private void toggleSeminarMenu() {
        if (mSeminarMenuWindow != null && mSeminarMenuWindow.isShowing()) {
            mSeminarMenuIv.setImageResource(R.drawable.darrow);
            mSeminarMenuWindow.dismiss();

            return;
        }

        if (mSeminarMenuView == null) {
            mSeminarMenuView = LayoutInflater.from(mActivity.getApplicationContext()).inflate(R.layout.layout_seminarmenu, null);
        }

        ListView parentLv = (ListView) mSeminarMenuView.findViewById(R.id.mainLv);

        if (mParentMenuAdpater == null) {
            mParentMenuAdpater = new ParentMenuAdapter(mActivity.getApplicationContext(), mMainMenu);
            parentLv.setAdapter(mParentMenuAdpater);
        }

        mParentMenu = mMainMenu[0];

        final ListView childLv = (ListView) mSeminarMenuView.findViewById(R.id.childLv);

        if (mChildMenuAdatper == null) {
            mChildMenuAdatper = new ChildMenuAdapter(mActivity.getApplicationContext(), mParentMenu, mChildMenu);
            childLv.setAdapter(mChildMenuAdatper);
        }

        parentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mParentMenu = mMainMenu[position];

                mChildMenuAdatper.setParentMenu(mParentMenu);

            }
        });

        childLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuEntity menu = (MenuEntity) parent.getItemAtPosition(position);
                menu.setChecked(!menu.isChecked());

                mChildMenuAdatper.notifyDataSetChanged();
            }
        });

        if (mSeminarMenuWindow == null) {
            int height = mActivity.getApplicationContext().getResources().getDimensionPixelSize(R.dimen.menu_height);

            mSeminarMenuWindow = new PopupWindow(mSeminarMenuView,
                    ViewGroup.LayoutParams.MATCH_PARENT, height, true);
            ColorDrawable dw = new ColorDrawable(0x00000000);
            mSeminarMenuWindow.setBackgroundDrawable(dw);

        }

        if (mIsShowing && !mSeminarMenuWindow.isShowing())
            mSeminarMenuWindow.showAsDropDown(mSeminarLl);

    }

    @Override
    public void onResume() {
        super.onResume();

        mIsShowing = true;
    }

    @Override
    public void onPause() {
        super.onPause();

        mIsShowing = false;
    }

}
