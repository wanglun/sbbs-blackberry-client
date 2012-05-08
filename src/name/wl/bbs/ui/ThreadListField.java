package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.PostScreen;

public class ThreadListField extends BbsObjectListField
{
    private Vector topics;
    private Vector current;

    // 当前打开的话题序号
    private int opened = -1;

    public ThreadListField(Vector topics)
    {
        this.topics = topics;

        this.setCurrent(topics);
    }

    public void setCurrent(Vector current)
    {
        this.current = current;

        this.setSize(current.size());
    }

    public boolean setPrevious()
    {
        return false;
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
                break;
            case 'r':
                Topic t = (Topic)current.elementAt(idx);
                bbs.pushScreen(new PostScreen(null, t));
                break;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)current.elementAt(index);

        if (this.opened == index) {
            graphics.drawText("*", 0, y, DrawStyle.ELLIPSIS, 16);
        } else {
            graphics.drawText("+", 0, y, DrawStyle.ELLIPSIS, 16);
        }

        graphics.drawText(t.getTitle() + t.getContent(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
