package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class UserListField extends BbsObjectListField
{
    private Vector users;
    private Listener listener;

    public UserListField(Vector users, Listener listener)
    {
        this.users = users;
        this.setSize(users.size());

        this.listener = listener;
    }

    public void setUsers(Vector users)
    {
        this.users = users;
        this.setSize(users.size());
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                User u = (User)users.elementAt(idx);
                listener.callback(u);
                break;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        User u = (User)users.elementAt(index);

        graphics.drawText("*", 0, y, DrawStyle.ELLIPSIS, 16);
        graphics.drawText(u.getName(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
