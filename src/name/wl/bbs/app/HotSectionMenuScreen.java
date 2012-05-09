package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class HotSectionMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public HotSectionMenuScreen()
    {
        Vector sections = bbs.getSections();
        items = new Vector();
        Board board = null;
        for (int i = 0; i < sections.size(); i++) {
            board = (Board)sections.elementAt(i);
            items.addElement(new MenuListItem(board.getName(), hotsectionListener, i));
        }

        list = new MenuListField(items);
        add(list);
    }

    public Listener hotsectionListener = new Listener() {
        public void callback(Object o)
        {
            MenuListItem item = (MenuListItem)o;
            bbs.pushScreen(new HotSectionScreen(item.getIndex()));
        }
    };
}
