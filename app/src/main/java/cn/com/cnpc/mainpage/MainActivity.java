package cn.com.cnpc.mainpage;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ZoomControls;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.supermap.data.Datasource;
import com.supermap.data.DatasourceConnectionInfo;
import com.supermap.data.EngineType;
import com.supermap.data.Point2D;
import com.supermap.data.Workspace;
import com.supermap.mapping.MapControl;
import com.supermap.mapping.MapView;

import cn.com.cnpc.R;
import cn.com.cnpc.appconfig.DefaultDataConfig;
import cn.com.cnpc.mainpage.adapter.MainMenuGVAdapter;
import cn.com.cnpc.mainpage.adapter.MainMenuLVAdapter;

public class MainActivity extends Activity {

    private MapControl m_mapControl = null;
    private Workspace m_wokspace;
    private MapView m_mapView;
    private ZoomControls m_zoom;

    private SlidingMenu mSlideMenu;

    private Activity mActivity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        initLeftMenu();

        openMap();
    }

    private void initView() {
        ImageView mMmIv = (ImageView) findViewById(R.id.mm_iv);
        mMmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideMenu.toggle();
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

    // 打开地图
    private boolean openMap() {
        m_wokspace = new Workspace();

        // 将地图显示空间和 工作空间关联
        m_mapView = (MapView) findViewById(R.id.mapview);
        m_mapControl = m_mapView.getMapControl();
        m_mapControl.getMap().setWorkspace(m_wokspace);


        DatasourceConnectionInfo dsInfo = new DatasourceConnectionInfo();
        dsInfo.setServer(DefaultDataConfig.DLG_PIPE);
        dsInfo.setEngineType(EngineType.Rest);
        dsInfo.setAlias("DLG_PIPE");

        Datasource ds = m_wokspace.getDatasources().open(dsInfo);


        if (ds != null) {
            m_mapControl.getMap().getLayers().add(ds.getDatasets().get(0), true);
            m_mapControl.getMap().setCenter(new Point2D(111.8, 37.2));
            m_mapControl.getMap().setFullScreenDrawModel(true);
            m_mapControl.getMap().isAntialias();
            m_mapControl.getMap().setScale(0.000000432682);
            m_mapControl.getMap().refresh();
            return true;
        }
        Log.e(this.getClass().getName(), "打开数据源失败了");

        return true;
    }

    @Override
    public void onBackPressed() {

    }
}