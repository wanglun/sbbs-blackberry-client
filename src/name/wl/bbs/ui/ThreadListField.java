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
import name.wl.bbs.app.ArticleScreen;

public class ThreadListField extends BbsObjectListField
{
    private Vector topics;
    private Vector current;

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
        Topic t = (Topic)current.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                bbs.pushScreen(new ArticleScreen(t));
                return true;
            case 'r':
                bbs.pushScreen(new PostScreen(null, t));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)current.elementAt(index);

        if (t.isUnread())
            graphics.drawText("*", 0, y, DrawStyle.ELLIPSIS, 16);

        graphics.drawText(t.getAuthor() + " " + t.getTitle(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
