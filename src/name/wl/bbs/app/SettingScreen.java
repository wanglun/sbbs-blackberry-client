package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class SettingScreen extends BaseScreen
{
    // 检查新通知间隔
    private NumericChoiceField updateDelay;
    // 每次加载文章数
    private NumericChoiceField loadTopics;

    private Settings settings;
    public SettingScreen()
    {
        this.settings = bbs.getSettings();

        updateDelay = new NumericChoiceField("检查新通知间隔(分钟)", 5, 30, 5, this.settings.getUpdateDelay()/5 - 1);
        loadTopics = new NumericChoiceField("每次加载文章数", 5, 30, 5, this.settings.getLoadTopics()/5 - 1);

        add(updateDelay);
        add(loadTopics);

        setStatusbarTitle("设置");
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'q':
                if (isDirty()) {
                    if (onSavePrompt()) {
                        bbs.popScreen(this);
                    }
                } else {
                    bbs.popScreen(this);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }

    protected boolean onSavePrompt()
    {
        int ret = Dialog.ask(Dialog.D_SAVE, "保存设置？");
        
        if (ret == Dialog.SAVE) {
            this.settings.setUpdateDelay(updateDelay.getSelectedValue());
            bbs.scheduleNotificationsTask();

            this.settings.setLoadTopics(loadTopics.getSelectedValue());
            return true;
        } else if (ret == Dialog.DISCARD) {
            return true;
        } else if (ret == Dialog.CANCEL) {
            return false;
        }
        return true;
    }
}
