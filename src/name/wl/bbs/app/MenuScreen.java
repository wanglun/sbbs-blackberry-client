package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public MenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("Sections", sectionsListener));
        items.addElement(new MenuListItem("Fav", favListener));

        list = new MenuListField(items);
        add(list);
    }

    public Listener sectionsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new SectionsScreen());
        }
    };

    public Listener favListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new FavScreen());
        }
    };
}
