package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;

public class InfoListField extends BbsObjectListField
{
    private Vector items;

    public InfoListField(Vector items)
    {
        this.items = items;
        setObjects(items);
    }

    public void setItems(Vector items)
    {
        this.items = items;
        setObjects(this.items);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        InfoListItem i = (InfoListItem)items.elementAt(index);

        super.drawListRow(listField, graphics, index, y, width);

        if (i.getKey() == null) {
            graphics.setColor(0xEEFFEE);
            graphics.fillRect(0, y, width, getRowHeight());
            graphics.setColor(Color.GREEN);
            graphics.drawText(i.getLabel(), 0, y, DrawStyle.HCENTER, width);
        } else {
            graphics.drawText(i.getKey(), 16, y, DrawStyle.ELLIPSIS, (int)(width*0.3 - 16));
            graphics.drawText(i.getLabel(), (int)(width*0.3), y, DrawStyle.ELLIPSIS, (int)(width*0.7));
        }
    }
}
