package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.*;

public class BoardListField extends BbsObjectListField
{
    private Vector boards;
    private Stack preVector;
    private Stack preIndex;
    private Vector current;

    public BoardListField(Vector boards)
    {
        if (boards == null)
            this.boards = new Vector();
        else
            this.boards = boards;
        this.preVector = new Stack();
        this.preIndex = new Stack();

        this.setCurrent(boards);
    }

    public void setCurrent(Vector current)
    {
        this.current = current;
        setObjects(this.current);
    }

    public boolean setPrevious()
    {
        if (this.preVector.empty()) {
            return false;
        } else {
            setCurrent((Vector)this.preVector.pop());
            Integer index = (Integer)this.preIndex.pop();
            setSelectedIndex(index.intValue());
            return true;
        }
    }

    public void setBoards(Vector boards)
    {
        if (boards == null)
            this.boards = new Vector();
        else
            this.boards = boards;
        this.preVector = new Stack();
        this.preIndex = new Stack();

        this.setCurrent(boards);
    }

    public void setRead(int index)
    {
        ((Board)(current.elementAt(index))).setUnread(false);
        invalidate(index);
    }

    public Board getSelectedBoard()
    {
        return (Board)current.elementAt(getSelectedIndex());
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        if (current.isEmpty()) {
            return super.keyChar(key, status, time);
        }

        Board b = (Board)current.elementAt(idx);
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                if (b.isLeaf() == false) {
                    this.preVector.push(this.current);
                    this.preIndex.push(new Integer(idx));
                    this.setCurrent(b.getBoards());
                } else {
                    bbs.pushScreen(new BoardScreen(this));
                }
                return true;
            case 'i':
                bbs.pushScreen(new InfoScreen(b));
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Board b = (Board)current.elementAt(index);

        super.drawListRow(listField, graphics, index, y, width);

        if (b.isUnread()) {
            graphics.setColor(Color.GREEN);
        }

        int w = Font.getDefault().getAdvance('+');
        if (!b.isLeaf()) {
            graphics.drawText("+", 0, y, DrawStyle.ELLIPSIS, w);
        }

        graphics.drawText(b.getName() + " - " + b.getDescription(), w, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
