package name.wl.bbs.ui;

import name.wl.bbs.app.Bbs;
import name.wl.bbs.util.*;

import net.rim.device.api.ui.component.*;

public class BbsObjectListField extends ObjectListField
{
    protected Bbs bbs;

    protected Listener moreListener = null;
    
    public BbsObjectListField()
    {
        super();
        bbs = Bbs.getInstance();

        setRowHeight(20);
    }

    public boolean setPrevious()
    {
        return false;
    }

    protected boolean keyChar(char key, int status, int time)
    {
        int idx = this.getSelectedIndex();
        switch (key) {
            case 'j':
                if (idx < this.getSize() - 1) {
                    this.setSelectedIndex(idx + 1);
                } else if (idx == this.getSize() - 1) {
                    if (moreListener != null) {
                        moreListener.callback(null);
                    }
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
                break;
        }

        return false;
    }
}
