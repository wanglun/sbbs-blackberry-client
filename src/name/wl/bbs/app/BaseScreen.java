package name.wl.bbs.app;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;

import name.wl.bbs.util.*;

public class BaseScreen extends MainScreen
{
    protected Bbs bbs;

    public BaseScreen()
    {
        bbs = (Bbs)UiApplication.getUiApplication();

        LabelField title = new LabelField("SBBS Client" , LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        setTitle(title);

        //add(new RichTextField("Hello World!" ,Field.NON_FOCUSABLE));
    }
}
