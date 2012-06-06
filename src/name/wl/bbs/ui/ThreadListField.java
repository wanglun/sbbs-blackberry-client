package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.*;

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

        setObjects(this.topics);
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
        setObjects(this.topics);
        this.setSelectedIndex(sel);
    }

    public Vector getTopics()
    {
        return this.topics;
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        if (topics.isEmpty()) {
            return super.keyChar(key, status, time);
        }


        Topic t = (Topic)topics.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                bbs.pushScreen(new ArticleScreen(this));
                return true;
            case 'r':
                bbs.pushScreen(new PostScreen(null, t));
                return true;
            case 'a':
                bbs.pushScreen(new UserScreen(new User(t.getAuthor())));
                return true;
            case 'i':
                bbs.pushScreen(new InfoScreen(t));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)topics.elementAt(index);

        super.drawListRow(listField, graphics, index, y, width);

        if (t.isUnread()) {
            graphics.setColor(Color.GREEN);
        }

        if (t.isMark()) {
            graphics.setColor(Color.ORANGE);
        }

        if (t.isTop()) {
            graphics.setColor(Color.RED);
        }

        if (t.isNorep()) {
            graphics.setColor(Color.GRAY);
        }

        graphics.drawText(t.getAuthor(), 16, y, DrawStyle.ELLIPSIS, (int)(width*0.3));
        graphics.drawText(GenTimeStr.pretty(t.getTime()), (int)(width*0.3), y, DrawStyle.RIGHT, (int)(width*0.7) - 10);
        graphics.drawText(t.getTitle(), 16, y + lineHeight, DrawStyle.ELLIPSIS, width - 10);
    }
}
