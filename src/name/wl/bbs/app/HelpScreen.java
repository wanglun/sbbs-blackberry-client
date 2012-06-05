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

        setStatusbarTitle("����");
    }

    private void base(Vector items) {
        items.addElement(new HelpListItem("������ݼ�"));
        items.addElement(new HelpListItem("?", "�鿴����"));
        items.addElement(new HelpListItem("j", "���¹���/�б���һ��Ŀ"));
        items.addElement(new HelpListItem("k", "���Ϲ���/�б���һ��Ŀ"));
        items.addElement(new HelpListItem("q", "����"));
        items.addElement(new HelpListItem("o/Enter", "��/����"));
        items.addElement(new HelpListItem("v", "�鿴����"));
        items.addElement(new HelpListItem("w", "�鿴����"));
        items.addElement(new HelpListItem("x", "�鿴֪ͨ��"));
        items.addElement(new HelpListItem("H", "�鿴ʮ����"));
        items.addElement(new HelpListItem(":", "�鿴����ʮ��"));
    }

    private void article(Vector items) {
        items.addElement(new HelpListItem("�鿴���� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "�ظ�����"));
        items.addElement(new HelpListItem("n", "��һƪ����"));
        items.addElement(new HelpListItem("p", "��һƪ����"));
        items.addElement(new HelpListItem("V", "����/�뿪����ѡ��ģʽ"));
        items.addElement(new HelpListItem("h", "�����ƶ����"));
        items.addElement(new HelpListItem("l", "�����ƶ����"));
        items.addElement(new HelpListItem("y", "����ѡ�е�����"));
    }

    private void board(Vector items) {
        items.addElement(new HelpListItem("���� - ��ݼ�"));
        items.addElement(new HelpListItem("p", "������"));
        items.addElement(new HelpListItem("c", "�������������δ�����"));
        items.addElement(new HelpListItem("r", "ˢ�������б�"));
        items.addElement(new HelpListItem("a", "�鿴����"));
        items.addElement(new HelpListItem("N", "�л�����ͨģʽ"));
        items.addElement(new HelpListItem("T", "�л�������ģʽ"));
        items.addElement(new HelpListItem("F", "�л�����̳ģʽ"));
        items.addElement(new HelpListItem("M", "�л�������ģʽ"));
        items.addElement(new HelpListItem("D", "�л�����ժģʽ"));
        items.addElement(new HelpListItem("Z", "�л����ö�ģʽ"));
    }

    private void fav(Vector items) {
        items.addElement(new HelpListItem("�ղؼ� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ǿ�Ƹ����ղؼ�"));
    }

    private void friendsAll(Vector items) {
        items.addElement(new HelpListItem("���к��� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ǿ�Ƹ��º����б�"));
        items.addElement(new HelpListItem("O", "�鿴���ߺ���"));
    }

    private void friends(Vector items) {
        items.addElement(new HelpListItem("���ߺ��� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ǿ�Ƹ��º����б�"));
        items.addElement(new HelpListItem("A", "�鿴���к���"));
    }

    private void hot(Vector items) {
        items.addElement(new HelpListItem("ʮ���� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ǿ�Ƹ���������Ϣ"));
        items.addElement(new HelpListItem("a", "�鿴����"));
    }

    private void mailBox(Vector items) {
        items.addElement(new HelpListItem("���� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ˢ���������ż�"));
        items.addElement(new HelpListItem("I", "�л����ռ���"));
        items.addElement(new HelpListItem("S", "�л���������"));
        items.addElement(new HelpListItem("D", "�л���������"));
    }

    private void mail(Vector items) {
        items.addElement(new HelpListItem("�鿴�ż� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "����"));
        items.addElement(new HelpListItem("n", "��һƪ�ż�"));
        items.addElement(new HelpListItem("p", "��һƪ�ż�"));
    }

    private void notificationMenu(Vector items) {
        items.addElement(new HelpListItem("֪ͨ�� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ˢ��֪ͨ��"));
        items.addElement(new HelpListItem("c", "���δ��֪ͨ"));
    }

    private void sections(Vector items) {
        items.addElement(new HelpListItem("���������� - ��ݼ�"));
        items.addElement(new HelpListItem("r", "ˢ�·���������(�ȽϺ�ʱ)"));
    }

    private void thread(Vector items) {
        items.addElement(new HelpListItem("�鿴���� - ��ݼ�"));
        items.addElement(new HelpListItem("p", "������"));
        items.addElement(new HelpListItem("r", "�ظ�����"));
        items.addElement(new HelpListItem("a", "�鿴����"));
    }

    private void user(Vector items) {
        items.addElement(new HelpListItem("�鿴�û� - ��ݼ�"));
        items.addElement(new HelpListItem("m", "�����û�д��"));
    }
}
