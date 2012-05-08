package name.wl.bbs.ui;

import name.wl.bbs.app.Bbs;

import net.rim.device.api.ui.component.*;

public class BbsObjectListField extends ObjectListField
{
    protected Bbs bbs;
    
    public BbsObjectListField()
    {
        super();
        bbs = Bbs.getInstance();
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
                }
                break;
        }

        return false;
    }
}
