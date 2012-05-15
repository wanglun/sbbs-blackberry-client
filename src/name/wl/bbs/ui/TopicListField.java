package name.wl.bbs.ui;

import java.util.*;
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
    private Listener listener;

    public TopicListField(Vector topics, Listener listener)
    {
        if (topics == null)
            this.topics = new Vector();
        else
            this.topics = topics;

        this.setSize(this.topics.size());

        this.listener = listener;
    }

    public void setTopics(Vector topics)
    {
        if (topics == null)
            this.topics = new Vector();
        else
            this.topics = topics;

        this.setSize(this.topics.size());
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                Topic t = (Topic)topics.elementAt(idx);
                listener.callback(t);
                break;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Topic t = (Topic)topics.elementAt(index);

        graphics.drawText(t.getAuthor() + " " + t.getTitle(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
