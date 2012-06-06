package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class InfoScreen extends BaseScreen
{
    private Vector items;
    private InfoListField list;

    private Topic topic = null;
    private Board board = null;

    public InfoScreen(Topic topic)
    {
        this.topic = topic;
        this.items = new Vector();

        topicItems();

        list = new InfoListField(this.items);
        add(list);

        setStatusbarTitle("�鿴������Ϣ");
    }

    public InfoScreen(Board board)
    {
        this.board = board;
        this.items = new Vector();

        boardItems();

        list = new InfoListField(this.items);
        add(list);

        setStatusbarTitle("�鿴������Ϣ");
    }

    public void topicItems()
    {
        if (this.topic == null)
            return;

        items.addElement(new InfoListItem("ID��", this.topic.getId()));
        items.addElement(new InfoListItem("����ID��", this.topic.getGid()));
        items.addElement(new InfoListItem("�ظ�����ID��", this.topic.getReid()));
        items.addElement(new InfoListItem("���ڰ��棺", this.topic.getBoard()));
        items.addElement(new InfoListItem("���ߣ�", this.topic.getAuthor()));
        items.addElement(new InfoListItem("��С��", this.topic.getSize()));
        items.addElement(new InfoListItem("�Ķ�����", this.topic.getRead()));
        items.addElement(new InfoListItem("�ظ�����", this.topic.getReplies()));
        items.addElement(new InfoListItem("δ����", this.topic.isUnread()));
        items.addElement(new InfoListItem("�ö���", this.topic.isTop()));
        items.addElement(new InfoListItem("��ǣ�", this.topic.isMark()));
        items.addElement(new InfoListItem("���ɻظ���", this.topic.isNorep()));
        items.addElement(new InfoListItem("����ʱ�䣺", GenTimeStr.standard(this.topic.getTime())));
        if (this.topic.getLastAuthor() != "")
            items.addElement(new InfoListItem("���ظ��ߣ�", this.topic.getLastAuthor()));
        if (this.topic.getLastTime() != 0)
            items.addElement(new InfoListItem("���ظ�ʱ�䣺", GenTimeStr.standard(this.topic.getLastTime())));
    }

    public void boardItems()
    {
        if (this.board == null)
            return;

        items.addElement(new InfoListItem("Ӣ������", this.board.getName()));
        items.addElement(new InfoListItem("��������", this.board.getDescription()));
        items.addElement(new InfoListItem("��������", this.board.getCount()));
        items.addElement(new InfoListItem("����������", this.board.getUsers()));
        items.addElement(new InfoListItem("��δ�����ӣ�", this.board.isUnread()));
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'q':
                bbs.popScreen(this);
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
