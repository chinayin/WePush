package com.fangxuele.tool.push.ui;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.fangxuele.tool.push.App;
import com.fangxuele.tool.push.ui.form.AboutForm;
import com.fangxuele.tool.push.ui.form.HelpForm;
import com.fangxuele.tool.push.ui.form.MemberForm;
import com.fangxuele.tool.push.ui.form.MessageEditForm;
import com.fangxuele.tool.push.ui.form.MessageManageForm;
import com.fangxuele.tool.push.ui.form.MessageTypeForm;
import com.fangxuele.tool.push.ui.form.PushForm;
import com.fangxuele.tool.push.ui.form.PushHisForm;
import com.fangxuele.tool.push.ui.form.ScheduleForm;
import com.fangxuele.tool.push.ui.form.SettingForm;
import com.fangxuele.tool.push.ui.form.UserCaseForm;
import com.fangxuele.tool.push.ui.listener.AboutListener;
import com.fangxuele.tool.push.util.SystemUtil;
import org.apache.commons.lang3.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * <pre>
 * 初始化类
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2017/6/15.
 */
public class Init {

    private static final Log logger = LogFactory.get();

    /**
     * 设置全局字体
     */
    public static void initGlobalFont() {

        // 低分辨率屏幕字号初始化
        String lowDpiKey = "lowDpiInit";
        // 得到屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width <= 1366 && StringUtils.isEmpty(App.config.getProps(lowDpiKey))) {
            App.config.setFontSize(12);
            App.config.setProps(lowDpiKey, "true");
        }

        // Mac等高分辨率屏幕字号初始化
        String highDpiKey = "highDpiInit";
        if (SystemUtil.isMacOs() && StringUtils.isEmpty(App.config.getProps(highDpiKey))) {
            App.config.setFontSize(15);
            App.config.setProps(highDpiKey, "true");
        } else if (screenSize.width > 1920 && StringUtils.isEmpty(App.config.getProps(highDpiKey))) {
            App.config.setFontSize(14);
            App.config.setProps(highDpiKey, "true");
        }

        App.config.save();

        Font font = new Font(App.config.getFont(), Font.PLAIN, App.config.getFontSize());
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    /**
     * 其他初始化
     */
    public static void initOthers() {
        // 设置滚动条速度
        SettingForm.settingForm.getSettingScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        SettingForm.settingForm.getSettingScrollPane().getVerticalScrollBar().setDoubleBuffered(true);

        UserCaseForm.userCaseForm.getUserCaseScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        UserCaseForm.userCaseForm.getUserCaseScrollPane().getVerticalScrollBar().setDoubleBuffered(true);

        MemberForm.memberForm.getMemberImportScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        MemberForm.memberForm.getMemberImportScrollPane().getVerticalScrollBar().setDoubleBuffered(true);

        MessageTypeForm.messageTypeForm.getMessageTypeScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        MessageTypeForm.messageTypeForm.getMessageTypeScrollPane().getVerticalScrollBar().setDoubleBuffered(true);

        // 设置版本
        AboutForm.aboutForm.getVersionLabel().setText(UiConsts.APP_VERSION);
    }

    /**
     * 初始化look and feel
     */
    public static void initTheme() {

        try {
            switch (App.config.getTheme()) {
                case "BeautyEye":
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);
                    break;
                case "系统默认":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case "weblaf":
                case "Darcula(推荐)":
                default:
                    UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    /**
     * 初始化所有tab
     */
    public static void initAllTab() {
        MessageTypeForm.init();
        HelpForm.init();
        ThreadUtil.execute(UserCaseForm::init);
        MessageEditForm.init(null);
        MessageManageForm.init();
        MemberForm.init();
        PushForm.init();
        ScheduleForm.init();
        SettingForm.init();
        PushHisForm.init();

        // 检查新版版
        if (App.config.isAutoCheckUpdate()) {
            ThreadUtil.execute(() -> AboutListener.checkUpdate(true));
        }
        // 更新二维码
        ThreadUtil.execute(AboutListener::initQrCode);
    }

}
