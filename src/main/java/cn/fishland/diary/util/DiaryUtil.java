package cn.fishland.diary.util;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class DiaryUtil {

    // Redis Keys
    /** api接口访问浏览数存放key */
    public final static String REDIS_API_BROWSE_KEY = "Diary:Count:ApiBrowse";
    /** 每日更新 */
    public final static String REDIS_DAILY_UPDATE = "Diary:Daily:Update";
    /** 每日热梦 */
    public final static String REDIS_DAILY_HOT = "Diary:Daily:Hot";

    // App final keys
    /** 主页每页显示数量 */
    public final static int APP_INDEX_PAGE_NUMBER = 16;
    /** 验证码 session key */
    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

}
