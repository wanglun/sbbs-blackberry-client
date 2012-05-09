package name.wl.bbs.app;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.BasicEditField;

import name.wl.bbs.util.*;

public class BaseScreen extends MainScreen
{
    protected Bbs bbs;

    public BaseScreen()
    {
        bbs = Bbs.getInstance();

        LabelField title = new LabelField("SBBS Client" , LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        setTitle(title);

        //add(new RichTextField("Hello World!" ,Field.NON_FOCUSABLE));
    }

    public void alert(final String info)
    {
        bbs.invokeLater(new Runnable() {
            public void run() {
                Dialog.alert(info);
            }
        });
    }

    protected boolean keyChar(char key, int status, int time)
    {
        if (getFieldWithFocus() instanceof  BasicEditField) {
        } else {
            switch (key) {
                case 'b':
                    return true;
            }
        }

        return super.keyChar(key, status, time);
    }
}
