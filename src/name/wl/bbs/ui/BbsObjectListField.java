package name.wl.bbs.ui;

import java.util.Vector;

import name.wl.bbs.app.Bbs;
import name.wl.bbs.util.*;
import name.wl.bbs.app.BaseScreen;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.KeypadUtil;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;

public class BbsObjectListField extends ObjectListField
{
    protected Bbs bbs;

    protected int lineHeight;

    protected Listener moreListener = null;
    
    public BbsObjectListField()
    {
        super();
        bbs = Bbs.getInstance();

        lineHeight = Font.getDefault().getHeight() + 2;
        setRowHeight(lineHeight);
    }

    public boolean setPrevious()
    {
        return false;
    }

    // OS5 needs this
    public void setObjects(Vector items)
    {
        Object objs[] = new Object[items.size()];
        items.copyInto(objs);
        set(objs);

        setStatusbarIndex(1);
    }

    public void loadMore()
    {
        if (moreListener != null) {
            moreListener.callback(null);
        }
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'j':
                changeSelected(1);
                return true;
            case 'k':
                changeSelected(-1);
                return true;
            case 'q':
                if (this.setPrevious()) {
                    return true;
                } else {
                    if (bbs.getScreenCount() > 1) {
                        bbs.popScreen(getScreen());
                    }
                }
                return true;
            case 'n':
                loadMore();
                return true;
            case 't':
                this.setSelectedIndex(0);
                return true;
            case 'b':
                this.setSelectedIndex(this.getSize() - 1);
                return true;
        }

        return false;
    }

    protected boolean keyRepeat(int keycode, int time)
    {
        int j = KeypadUtil.getKeyCode('j', 0, KeypadUtil.MODE_UI_CURRENT_LOCALE);
        int k = KeypadUtil.getKeyCode('k', 0, KeypadUtil.MODE_UI_CURRENT_LOCALE);
        if (keycode == j) {
            changeSelected(1);
            return true;
        } else if (keycode == k) {
            changeSelected(-1);
            return true;
        }

        return super.keyRepeat(keycode, time);
    }

    protected void changeSelected(int direction)
    {
        moveFocus(direction, 0, 0);
    }

    public int moveFocus(int amount, int status, int time)
    {
        int idx = this.getSelectedIndex();
        if (amount > 0) {
            if (idx == this.getSize() - 1) {
                loadMore();
            } else {
                setSelectedIndex(idx + 1);
            }
        } else {
            if (idx > 0) {
                setSelectedIndex(idx - 1);
            }
        }

        return 0;
    }

    public boolean trackwheelClick(int status, int time)
    {
        return super.trackwheelClick(status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        int old_color = graphics.getColor();

        if (index % 2 == 1 && getSelectedIndex() != index) {
            graphics.setColor(0xF3F3F3);
            graphics.fillRect(0, y, width, getRowHeight());
            graphics.setColor(old_color);
        }

        graphics.setColor(Color.GRAY);
        graphics.drawLine(0, y + getRowHeight() - 1, width, y + getRowHeight() - 1);
        graphics.setColor(old_color);
    }

    public void setSelectedIndex(int index)
    {
        setStatusbarIndex(index + 1);
        super.setSelectedIndex(index);
    }

    public void setStatusbarIndex(int index)
    {
        BaseScreen screen = (BaseScreen)getScreen();
        if (screen != null) {
            screen.setStatusbarIndex(index, this.getSize());
        }
    }

    protected void onDisplay()
    {
        setStatusbarIndex(1);
    }
}
