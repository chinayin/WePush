package com.fangxuele.tool.push.ui;

import java.awt.*;

/**
 * <pre>
 * UI相关的常量
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2019/4/20.
 */
public class UiConsts {

    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "WePush";
    public final static String APP_VERSION = "v_3.2.0_190601";

    /**
     * 主窗口图标-大
     */
    public final static Image IMAGE_ICON_LG = Toolkit.getDefaultToolkit()
            .getImage(UiConsts.class.getResource("/icon/logo-lg.png"));

    /**
     * 主窗口图标-中
     */
    public final static Image IMAGE_ICON_MD = Toolkit.getDefaultToolkit()
            .getImage(UiConsts.class.getResource("/icon/logo-md.png"));

    /**
     * 主窗口图标-小
     */
    public final static Image IMAGE_ICON_SM = Toolkit.getDefaultToolkit()
            .getImage(UiConsts.class.getResource("/icon/logo-sm.png"));

    /**
     * 主窗口图标-超小
     */
    public final static Image IMAGE_ICON_XS = Toolkit.getDefaultToolkit()
            .getImage(UiConsts.class.getResource("/icon/logo-xs.png"));

    /**
     * 软件版本检查url
     */
    public final static String CHECK_VERSION_URL = "https://raw.githubusercontent.com/rememberber/WePush/master/src/main/resources/version_summary.json";

    /**
     * 用户案例url
     */
    public final static String USER_CASE_URL = "http://download.zhoubochina.com/file/user_case.json";

    /**
     * 用户案例url
     */
    public final static String QR_CODE_URL = "http://download.zhoubochina.com/file/wepush_qrcode.json";

}
