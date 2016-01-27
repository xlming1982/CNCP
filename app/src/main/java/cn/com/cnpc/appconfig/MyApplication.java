package cn.com.cnpc.appconfig;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.supermap.data.Environment;

import cn.com.cnpc.file.MyAssetManager;
import cn.com.cnpc.file.MySharedPreferences;

/**
 * Created by DongRui on 16/1/7.
 */
public class MyApplication extends Application {

    public static String DATAPATH = "";
    public static String SDCARD = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
    private static MyApplication sInstance = null;



    @Override
    public void onCreate() {
        super.onCreate();

        DATAPATH = this.getFilesDir().getAbsolutePath()+"/";
        sInstance = this;

        //第一步就是设置环境参数，初始化好iMobile
        Environment.setLicensePath(DefaultDataConfig.LicPath);
        Environment.initialization(this);

        //初始化系统相关的类
        MySharedPreferences.init(this);
        MyAssetManager.init(this);

        //配置数据
//		new DefaultDataConfig().autoConfig();


    }

    public static MyApplication getInstance()
    {
        return sInstance;
    }


    /**
     * Toast显示信息
     * @param info
     */
    public void ShowInfo(String info){
        Toast toast = Toast.makeText(sInstance, info, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    /**
     * Toast显示错误信息
     * @param err
     */
    public void ShowError(String err){
        Toast toast = Toast.makeText(sInstance, "Error: "+err, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        Log.e(this.getClass().getName(), err);
    }

    /**
     * 获取显示尺寸值
     * @param dp
     * @return
     */
    public static int dp2px(int dp){
        return (int) (dp*sInstance.getResources().getDisplayMetrics().density);
    }

    public void exit(){

        Process.killProcess(Process.myPid());
    }
}
