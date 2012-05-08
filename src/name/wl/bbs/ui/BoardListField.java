package name.wl.bbs.ui;

import java.util.*;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.app.BoardScreen;

public class BoardListField extends BbsObjectListField
{
    private Vector boards;
    private Stack preVector;
    private Stack preIndex;
    private Vector current;

    public BoardListField(Vector boards)
    {
        this.boards = boards;
        this.preVector = new Stack();
        this.preIndex = new Stack();

        this.setCurrent(boards);
    }

    public void setCurrent(Vector current)
    {
        this.current = current;

        this.setSize(current.size());
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

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case Keypad.KEY_ENTER:
            case 'o':
                Board b = (Board)current.elementAt(idx);
                if (b.isLeaf() == false) {
                    this.preVector.push(this.current);
                    this.preIndex.push(new Integer(idx));
                    this.setCurrent(b.getBoards());
                } else {
                    bbs.pushScreen(new BoardScreen(b));
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }

    public void drawListRow(ListField listField, Graphics graphics, int index, int y, int width)
    {
        Board b = (Board)current.elementAt(index);

        if (b.isLeaf()) {
            graphics.drawText("*", 0, y, DrawStyle.ELLIPSIS, 16);
        } else {
            graphics.drawText("+", 0, y, DrawStyle.ELLIPSIS, 16);
        }

        graphics.drawText(b.getName(), 16, y, DrawStyle.ELLIPSIS, width - 16);
    }
}
