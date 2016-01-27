package cn.com.cnpc.appconfig;

import java.io.InputStream;

import cn.com.cnpc.file.FileManager;
import cn.com.cnpc.file.MyAssetManager;

/**
 * Created by DongRui on 16/1/13.
 */
public class DefaultDataConfig {


    public static final String LicPath       = MyApplication.SDCARD+"SuperMap/License/";
    private final       String LicName       = "Trial License.slm";


    private static final String DefaultServer = "http://120.27.106.216:8090";



    public static String WorkspacePath = "http://support.supermap.com.cn:8090/iserver/services/map-china400/rest/maps/China_4326";

    public static  String YJGD_CHINA_DOM = DefaultServer + "iserver/services/map-mongodb/rest/maps/YJGD_CHINA_DOM";
    //管道走向
    public static  String ZNGD_YW = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/ZNGD_YW";
    //阀室
    public static  String MAP_YW_ZCFS = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_YW_ZCFS";
    //人口分布
    public static  String MAP_POPULATIONS = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_POPULATIONS";
    //政府所在地
    public static  String MAP_CUNZFSZD = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_CUNZFSZD";
    //人口数量统计
    public static  String MAP_THEM_RKTJ = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_THEM_RKTJ";
    //沿线人口密度分布
    public static  String MAP_THEME_RKMD = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_THEME_RKMD";
    //紧急服务资源分布
    public static  String MAP_JJFW = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_JJFW";
    //紧急服务统计专题图
    public static  String MAP_THEM_JJFW = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_THEM_JJFW";
    //应急资源分布图
    public static  String MAP_YJZY = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_YJZY";
    //应急资源统计专题图
    public static  String MAP_THEM_YJZYTJ = DefaultServer + "/iserver/services/map-ZNGDGIS/rest/maps/MAP_THEM_YJZYTJ";


    //影像图+管道阀室
    public static  String DOM_PIPE =DefaultServer + "/iserver/services/map-ZNGDYD/rest/maps/DOM_PIPE";
    //矢量图+管道阀室
    public static  String DLG_PIPE =DefaultServer + "/iserver/services/map-ZNGDYD/rest/maps/DLG_PIPE";
    //矢量图+管道阀室+人口专题图
    public static  String DLG_THEME_RKFB =DefaultServer + "/services/map-ZNGDYD/rest/maps/DLG_THEME_RKFB";


    //-----------表示管道实体数据--------------------

    //中心线
    public static  String ST_LN_ZXZ	 = "ST_LN_ZXZ";
    //警示牌
    public static  String ST_PT_JSP	= "ST_PT_JSP";
    //穿跨越
    public static  String ST_PT_CYZ	= "ST_PT_CYZ";
    //转角桩
    public static  String ST_PT_ZJZ	= "ST_PT_ZJZ";
    //阴保桩
    public static  String ST_PT_YBZ	= "ST_PT_YBZ";
    //穿跨越
    public static  String ST_LN_CKY	= "ST_LN_CKY";
    //阀室
    public static  String ST_PT_FS ="ST_PT_FS";
    //中心桩
    public static  String ST_PT_ZXZ	="ST_PT_ZXZ";
    //第三方管道
    public static  String ST_PT_DSFGD ="ST_PT_DSFGD";
    //水工保护
    public static  String ST_LN_SGBH = "ST_LN_SGBH";
    //桩
    public static  String ST_PT_STAKE="ST_PT_STAKE";
    //高后果区
    public static  String ST_PT_HCAS	="ST_PT_HCAS";


    //---------------表示管道周边地理环境数据--------------
    //人口
    public static  String Populations	="Populations";
    //公安交警队伍
    public static  String gajjdw ="gajjdw";
    //消防救援队伍
    public static  String XFJYDW	="XFJYDW";
    //沿线抢险资源
    public static  String YXQXZYY	="YXQXZYY";
    //自然保护区
    public static  String ZRBHQ	="ZRBHQ";
    //密集居民区
    public static  String MJJMQ	="MJJMQ";
    //敏感目标
    public static  String MGMB	="MGMB";
    //重大危险源
    public static  String ZDWXY	="ZDWXY";
    //水文信息
    public static  String SWXX	="SWXX";
    //应急道路特征点
    public static  String YJDLTZD	="YJDLTZD";
    //医疗救护机构
    public static  String YLJH_ORG	="YLJH_ORG";
    //村委会乡镇政府所在地
    public static  String CWHXZFSZD	="CWHXZFSZD";

    //----------------表示用户收藏夹数据-----------------
    //收藏夹点
    public static  String SYS_FAV_POINT	="SYS_FAV_POINT";
    //收藏夹线
    public static  String SYS_FAV_LINE	="SYS_FAV_LINE";
    //收藏夹面
    public static  String SYS_FAV_REGION ="SYS_FAV_REGION";




    /**
     * 配置许可文件
     */
    private void configLic()
    {
        InputStream is = MyAssetManager.getInstance().open(LicName);
        if(is != null)
            FileManager.getInstance().copy(is, LicPath+LicName);
    }

}
