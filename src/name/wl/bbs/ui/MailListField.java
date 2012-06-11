package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.*;

public class MailListField extends BbsObjectListField
{
    private Vector mails;
    private Listener mailListener;

    public MailListField(Vector mails, Listener mailListener)
    {
        this(mails, mailListener, null);
    }

    public MailListField(Vector mails, Listener mailListener, Listener moreListener)
    {
        if (mails == null)
            this.mails = new Vector();
        else
            this.mails = mails;

        setObjects(this.mails);

        this.mailListener = mailListener;
        this.moreListener = moreListener;

        setRowHeight(2 * lineHeight);
    }

    public void setMails(Vector mails)
    {
        if (mails == null)
            this.mails = new Vector();
        else
            this.mails = mails;

        setObjects(this.mails);
    }

    public Vector getMails()
    {
        return this.mails;
    }

    public void appendMails(Vector mails)
    {
        for (int i = 0; i < mails.size(); i++) {
            this.mails.addElement(mails.elementAt(i));
        }

        int sel = this.getSelectedIndex();
        setObjects(this.mails);
        this.setSelectedIndex(sel);
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        if (mails.isEmpty()) {
            return super.keyChar(key, status, time);
        }
        Mail t = (Mail)mails.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                mailListener.callback(t);
                return true;
            case 'a':
                bbs.pushScreen(new UserScreen(new User(t.getAuthor())));
                return true;
            case 'i':
                bbs.pushScreen(new InfoScreen((Topic)t));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Mail t = (Mail)mails.elementAt(index);

        super.drawListRow(listField, graphics, index, y, width);

        graphics.drawText(t.getAuthor(), 16, y, DrawStyle.ELLIPSIS, (int)(width*0.3));
        graphics.drawText(GenTimeStr.pretty(t.getTime()), (int)(width*0.3), y, DrawStyle.RIGHT, (int)(width*0.7) - 10);
        graphics.drawText(t.getTitle(), 16, y + lineHeight, DrawStyle.ELLIPSIS, width - 10);
    }
}
