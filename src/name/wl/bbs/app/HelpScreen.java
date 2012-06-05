package name.wl.bbs.app;

import java.util.*;
import name.wl.bbs.json.*;
import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;

public class HelpScreen extends BaseScreen
{
    public static final int TYPE_BASE = 0;
    public static final int TYPE_ARTICLE = 1;
    public static final int TYPE_BOARD = 2;
    public static final int TYPE_FAV = 3;
    public static final int TYPE_FRIENDSALL = 4;
    public static final int TYPE_FRIENDS = 5;
    public static final int TYPE_HOT = 6;
    public static final int TYPE_MAILBOX = 7;
    public static final int TYPE_MAIL = 8;
    public static final int TYPE_NOTIFICATIONMENU = 9;
    public static final int TYPE_SECTIONS = 10;
    public static final int TYPE_THREAD = 11;
    public static final int TYPE_USER = 12;

    private Vector items;
    private HelpListField list;

    public HelpScreen()
    {
        this(TYPE_BASE);
    }

    public HelpScreen(int type)
    {
        items = new Vector();

        switch (type) {
            case TYPE_BASE:
                break;
            case TYPE_ARTICLE:
                article(items);
                break;
            case TYPE_BOARD:
                board(items);
                break;
            case TYPE_FAV:
                fav(items);
                break;
            case TYPE_FRIENDSALL:
                friendsAll(items);
                break;
            case TYPE_FRIENDS:
                friends(items);
                break;
            case TYPE_HOT:
                hot(items);
                break;
            case TYPE_MAILBOX:
                mailBox(items);
                break;
            case TYPE_MAIL:
                mail(items);
                break;
            case TYPE_NOTIFICATIONMENU:
                notificationMenu(items);
                break;
            case TYPE_SECTIONS:
                sections(items);
                break;
            case TYPE_THREAD:
                thread(items);
                break;
            case TYPE_USER:
                user(items);
                break;
        }

        base(items);

        list = new HelpListField(items);
        add(list);

        setStatusbarTitle("帮助");
    }

    private void base(Vector items) {
        items.addElement(new HelpListItem("基本快捷键"));
        items.addElement(new HelpListItem("?", "查看帮助"));
        items.addElement(new HelpListItem("j", "向下滚动/列表下一条目"));
        items.addElement(new HelpListItem("k", "向上滚动/列表上一条目"));
        items.addElement(new HelpListItem("q", "返回"));
        items.addElement(new HelpListItem("o/Enter", "打开/进入"));
        items.addElement(new HelpListItem("v", "查看信箱"));
        items.addElement(new HelpListItem("w", "查看好友"));
        items.addElement(new HelpListItem("x", "查看通知箱"));
        items.addElement(new HelpListItem("H", "查看十大话题"));
        items.addElement(new HelpListItem(":", "查看分区十大"));
    }

    private void article(Vector items) {
        items.addElement(new HelpListItem("查看文章 - 快捷键"));
        items.addElement(new HelpListItem("r", "回复文章"));
        items.addElement(new HelpListItem("n", "下一篇文章"));
        items.addElement(new HelpListItem("p", "上一篇文章"));
        items.addElement(new HelpListItem("V", "进入/离开文字选择模式"));
        items.addElement(new HelpListItem("h", "向左移动光标"));
        items.addElement(new HelpListItem("l", "向右移动光标"));
        items.addElement(new HelpListItem("y", "复制选中的文字"));
    }

    private void board(Vector items) {
        items.addElement(new HelpListItem("版面 - 快捷键"));
        items.addElement(new HelpListItem("p", "发表话题"));
        items.addElement(new HelpListItem("c", "清除版面内文章未读标记"));
        items.addElement(new HelpListItem("r", "刷新文章列表"));
        items.addElement(new HelpListItem("a", "查看作者"));
        items.addElement(new HelpListItem("N", "切换到普通模式"));
        items.addElement(new HelpListItem("T", "切换到主题模式"));
        items.addElement(new HelpListItem("F", "切换到论坛模式"));
        items.addElement(new HelpListItem("M", "切换到保留模式"));
        items.addElement(new HelpListItem("D", "切换到文摘模式"));
        items.addElement(new HelpListItem("Z", "切换到置顶模式"));
    }

    private void fav(Vector items) {
        items.addElement(new HelpListItem("收藏夹 - 快捷键"));
        items.addElement(new HelpListItem("r", "强制更新收藏夹"));
    }

    private void friendsAll(Vector items) {
        items.addElement(new HelpListItem("所有好友 - 快捷键"));
        items.addElement(new HelpListItem("r", "强制更新好友列表"));
        items.addElement(new HelpListItem("O", "查看在线好友"));
    }

    private void friends(Vector items) {
        items.addElement(new HelpListItem("在线好友 - 快捷键"));
        items.addElement(new HelpListItem("r", "强制更新好友列表"));
        items.addElement(new HelpListItem("A", "查看所有好友"));
    }

    private void hot(Vector items) {
        items.addElement(new HelpListItem("十大话题 - 快捷键"));
        items.addElement(new HelpListItem("r", "强制更新热门信息"));
        items.addElement(new HelpListItem("a", "查看作者"));
    }

    private void mailBox(Vector items) {
        items.addElement(new HelpListItem("信箱 - 快捷键"));
        items.addElement(new HelpListItem("r", "刷新信箱内信件"));
        items.addElement(new HelpListItem("I", "切换到收件箱"));
        items.addElement(new HelpListItem("S", "切换到发件箱"));
        items.addElement(new HelpListItem("D", "切换到垃圾箱"));
    }

    private void mail(Vector items) {
        items.addElement(new HelpListItem("查看信件 - 快捷键"));
        items.addElement(new HelpListItem("r", "回信"));
        items.addElement(new HelpListItem("n", "下一篇信件"));
        items.addElement(new HelpListItem("p", "上一篇信件"));
    }

    private void notificationMenu(Vector items) {
        items.addElement(new HelpListItem("通知箱 - 快捷键"));
        items.addElement(new HelpListItem("r", "刷新通知箱"));
        items.addElement(new HelpListItem("c", "清除未读通知"));
    }

    private void sections(Vector items) {
        items.addElement(new HelpListItem("分类讨论区 - 快捷键"));
        items.addElement(new HelpListItem("r", "刷新分类讨论区(比较耗时)"));
    }

    private void thread(Vector items) {
        items.addElement(new HelpListItem("查看主题 - 快捷键"));
        items.addElement(new HelpListItem("p", "发表话题"));
        items.addElement(new HelpListItem("r", "回复话题"));
        items.addElement(new HelpListItem("a", "查看作者"));
    }

    private void user(Vector items) {
        items.addElement(new HelpListItem("查看用户 - 快捷键"));
        items.addElement(new HelpListItem("m", "给该用户写信"));
    }
}
