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

    public ThreadListField(Vector topics)
    {
        this(topics, null);
    }

    public ThreadListField(Vector topics, Listener moreListener)
    {
        if (topics == null)
            this.topics = new Vector();
        else
            this.topics = topics;

        this.setSize(this.topics.size());
        this.moreListener = moreListener;

        setRowHeight(2 * lineHeight);
    }

    public boolean setPrevious()
    {
        return false;
    }

    public void appendTopics(Vector topics)
    {
        for (int i = 0; i < topics.size(); i++) {
            this.topics.addElement(topics.elementAt(i));
        }

        int sel = this.getSelectedIndex();
        this.setSize(this.topics.size());
        this.setSelectedIndex(sel);
    }

    public Vector getTopics()
    {
        return this.topics;
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        Topic t = (Topic)topics.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                bbs.pushScreen(new ArticleScreen(this));
                return true;
            case 'r':
                bbs.pushScreen(new PostScreen(null, t));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)topics.elementAt(index);

        if (t.isUnread())
            graphics.drawText("*", 0, y, DrawStyle.ELLIPSIS, 16);

        graphics.drawText(t.getAuthor() + " " + t.getTimeStr(), 16, y, DrawStyle.ELLIPSIS, width - 16);
        graphics.drawText(t.getTitle(), 16, y + lineHeight, DrawStyle.ELLIPSIS, width - 16);

        super.drawListRow(listField, graphics, index, y, width);
    }
}
