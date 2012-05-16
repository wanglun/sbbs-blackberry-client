package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;

public class MenuListField extends BbsObjectListField
{
    private Vector items;

    public MenuListField(Vector items)
    {
        this.items = items;
        this.setSize(this.items.size());
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                MenuListItem i = (MenuListItem)items.elementAt(idx);
                i.callback();
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        MenuListItem i = (MenuListItem)items.elementAt(index);

        graphics.drawText(i.getLabel(), 16, y, DrawStyle.ELLIPSIS, width - 16);

        super.drawListRow(listField, graphics, index, y, width);
    }
}
