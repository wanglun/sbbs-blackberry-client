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

        setStatusbarTitle("查看文章信息");
    }

    public InfoScreen(Board board)
    {
        this.board = board;
        this.items = new Vector();

        boardItems();

        list = new InfoListField(this.items);
        add(list);

        setStatusbarTitle("查看版面信息");
    }

    public void topicItems()
    {
        if (this.topic == null)
            return;

        items.addElement(new InfoListItem("ID：", this.topic.getId()));
        items.addElement(new InfoListItem("主题ID：", this.topic.getGid()));
        items.addElement(new InfoListItem("回复帖子ID：", this.topic.getReid()));
        items.addElement(new InfoListItem("所在版面：", this.topic.getBoard()));
        items.addElement(new InfoListItem("作者：", this.topic.getAuthor()));
        items.addElement(new InfoListItem("大小：", this.topic.getSize()));
        items.addElement(new InfoListItem("阅读数：", this.topic.getRead()));
        items.addElement(new InfoListItem("回复数：", this.topic.getReplies()));
        items.addElement(new InfoListItem("未读：", this.topic.isUnread()));
        items.addElement(new InfoListItem("置顶：", this.topic.isTop()));
        items.addElement(new InfoListItem("标记：", this.topic.isMark()));
        items.addElement(new InfoListItem("不可回复：", this.topic.isNorep()));
        items.addElement(new InfoListItem("发表时间：", GenTimeStr.standard(this.topic.getTime())));
        if (this.topic.getLastAuthor() != "")
            items.addElement(new InfoListItem("最后回复者：", this.topic.getLastAuthor()));
        if (this.topic.getLastTime() != 0)
            items.addElement(new InfoListItem("最后回复时间：", GenTimeStr.standard(this.topic.getLastTime())));
    }

    public void boardItems()
    {
        if (this.board == null)
            return;

        items.addElement(new InfoListItem("英文名：", this.board.getName()));
        items.addElement(new InfoListItem("中文名：", this.board.getDescription()));
        items.addElement(new InfoListItem("帖子数：", this.board.getCount()));
        items.addElement(new InfoListItem("在线人数：", this.board.getUsers()));
        items.addElement(new InfoListItem("有未读帖子：", this.board.isUnread()));
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
