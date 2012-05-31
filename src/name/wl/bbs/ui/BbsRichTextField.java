package name.wl.bbs.ui;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.system.Clipboard;

import name.wl.bbs.app.BaseScreen;

public class BbsRichTextField extends RichTextField
{
    private boolean visualMode = false;

    public BbsRichTextField(String text)
    {
        super(text);
    }

    protected boolean keyChar(char key, int status, int time)
    {
        BaseScreen screen = (BaseScreen)getScreen();
        switch (key) {
            case 'j':
                screen.trackwheelRoll(1, 0, 1000);
                return true;
            case 'k':
                screen.trackwheelRoll(-1, 0, 1000);
                return true;
            case 'h':
                screen.trackwheelRoll(-1, Keypad.KEY_ALT, 1000);
                return true;
            case 'l':
                screen.trackwheelRoll(1, Keypad.KEY_ALT, 1000);
                return true;
            case 'V':
                select(!visualMode);
                visualMode = !visualMode;
                return true;
            case 'y':
                if (visualMode) {
                    selectionCopy(Clipboard.getClipboard());
                    select(!visualMode);
                    visualMode = !visualMode;
                }
                return true;
        }
        return super.keyChar(key, status, time);
    }
}
