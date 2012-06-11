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
        "��վϵͳ", "���ϴ�ѧ", "���Լ���", "ѧ����ѧ", "�����Ļ�", "����У��",
        "��������", "֪�Ը���", "������Ϣ", "��̳�籩", "У������", "����Ⱥ��"
    };

    public HotSectionMenuScreen()
    {
        items = new Vector();
        for (int i = 0; i < sections.length; i++) {
            items.addElement(new MenuListItem(sections[i], hotsectionListener, i));
        }

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("�����ȵ�");
    }

    public Listener hotsectionListener = new Listener() {
        public void callback(Object o)
        {
            MenuListItem item = (MenuListItem)o;
            bbs.pushScreen(new HotSectionScreen(item.getIndex()));
        }
    };
}
