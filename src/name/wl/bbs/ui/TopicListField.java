package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicListField extends BbsObjectListField
{
    private Vector topics;
    private Listener topicListener = null;

    public TopicListField(Vector topics, Listener topicListener)
    {
        this(topics, topicListener, null);
    }

    public TopicListField(Vector topics, Listener topicListener, Listener moreListener)
    {
        if (topics == null)
            this.topics = new Vector();
        else
            this.topics = topics;

        this.setSize(this.topics.size());

        this.topicListener = topicListener;
        this.moreListener = moreListener;

        setRowHeight(2 * lineHeight);
    }

    public void setTopics(Vector topics)
    {
        if (topics == null)
            this.topics = new Vector();
        else
            this.topics = topics;

        this.setSize(this.topics.size());
    }

    public Vector getTopics()
    {
        return this.topics;
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

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                Topic t = (Topic)topics.elementAt(idx);
                topicListener.callback(t);
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)topics.elementAt(index);

        graphics.drawText(t.getAuthor() + " " + t.getTimeStr(), 16, y, DrawStyle.ELLIPSIS, width - 16);
        graphics.drawText(t.getTitle(), 16, y + lineHeight, DrawStyle.ELLIPSIS, width - 16);

        super.drawListRow(listField, graphics, index, y, width);
    }
}
