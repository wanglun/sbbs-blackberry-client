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
    private static final String[] sections = {
        "本站系统", "东南大学", "电脑技术", "学术科学", "艺术文化", "乡情校谊",
        "休闲娱乐", "知性感性", "人文信息", "体坛风暴", "校务信箱", "社团群体"
    };

    public HotSectionMenuScreen()
    {
        items = new Vector();
        for (int i = 0; i < sections.length; i++) {
            items.addElement(new MenuListItem(sections[i], hotsectionListener, i));
        }

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("分区热点");
    }

    public Listener hotsectionListener = new Listener() {
        public void callback(Object o)
        {
            MenuListItem item = (MenuListItem)o;
            bbs.pushScreen(new HotSectionScreen(item.getIndex()));
        }
    };
}
