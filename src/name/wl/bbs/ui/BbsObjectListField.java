package name.wl.bbs.ui;

import name.wl.bbs.app.Bbs;
import name.wl.bbs.util.*;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.Font;
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

    public void loadMore()
    {
        if (moreListener != null) {
            moreListener.callback(null);
        }
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case 'j':
                if (idx < this.getSize() - 1) {
                    this.setSelectedIndex(idx + 1);
                } else if (idx == this.getSize() - 1) {
                    loadMore();
                }
                return true;
            case 'k':
                if (idx > 0) {
                    this.setSelectedIndex(idx - 1);
                }
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

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        graphics.setColor(Color.GRAY);
        graphics.drawLine(0, y + getRowHeight() - 1, width, y + getRowHeight() - 1);
    }
}
