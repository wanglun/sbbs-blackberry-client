package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

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
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                Mail t = (Mail)mails.elementAt(idx);
                mailListener.callback(t);
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Mail t = (Mail)mails.elementAt(index);

        graphics.drawText(t.getAuthor() + " " + t.getTimeStr(), 16, y, DrawStyle.ELLIPSIS, width - 16);
        graphics.drawText(t.getTitle(), 16, y + lineHeight, DrawStyle.ELLIPSIS, width - 16);

        super.drawListRow(listField, graphics, index, y, width);
    }
}
