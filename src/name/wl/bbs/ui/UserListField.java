package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.MailSendScreen;

public class UserListField extends BbsObjectListField
{
    private Vector users;
    private Listener listener;

    public UserListField(Vector users, Listener listener)
    {
        if (users == null)
            this.users = new Vector();
        else
            this.users = users;

        setObjects(this.users);

        this.listener = listener;
    }

    public void setUsers(Vector users)
    {
        if (users == null)
            this.users = new Vector();
        else
            this.users = users;

        setObjects(this.users);
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        if (users.isEmpty()) {
            return super.keyChar(key, status, time);
        }
        User u = (User)users.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                listener.callback(u);
                return true;
            case 'm':
                bbs.pushScreen(new MailSendScreen(u));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        User u = (User)users.elementAt(index);

        super.drawListRow(listField, graphics, index, y, width);

        graphics.drawText(u.getId() + " " + u.getName(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
