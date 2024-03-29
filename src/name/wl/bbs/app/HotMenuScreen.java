package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class HotMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public HotMenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("十大话题", toptenListener));
        items.addElement(new MenuListItem("热门版面", boardsListener));
        items.addElement(new MenuListItem("分区热点", sectionsListener));

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("热门信息");
    }

    public Listener toptenListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new HotToptenScreen());
        }
    };

    public Listener boardsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new HotBoardsScreen());
        }
    };

    public Listener sectionsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new HotSectionMenuScreen());
        }
    };
}
