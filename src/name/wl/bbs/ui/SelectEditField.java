package name.wl.bbs.ui;

import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.*;
import net.rim.device.api.util.*;
import net.rim.device.api.ui.component.*;

public class SelectEditField extends BasicEditField
{   
    public SelectEditField(String label)
    {
        super(USE_ALL_WIDTH|NON_FOCUSABLE|NO_LEARNING|NO_NEWLINE); 

        setLabel(label);
    }

    public boolean isDirty()
    {            
        return false;
    }      

    protected boolean keyChar(char ch, int status, int time)
    {
        return super.keyChar(ch, status, time);
    }                     

    protected void paint(Graphics graphics)
    {            
        super.paint(graphics);

        getFocusRect(new XYRect());
        drawFocus(graphics, true);                          
    }
}
