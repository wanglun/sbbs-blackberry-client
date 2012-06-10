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
    private InfoListField list;

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

        list = new InfoListField(items);
        add(list);

        setStatusbarTitle("����");
    }

    private void base(Vector items) {
        items.addElement(new InfoListItem("������ݼ�"));
        items.addElement(new InfoListItem("?", "�鿴����"));
        items.addElement(new InfoListItem("j", "���¹���/�б���һ��Ŀ"));
        items.addElement(new InfoListItem("k", "���Ϲ���/�б���һ��Ŀ"));
        items.addElement(new InfoListItem("q", "����"));
        items.addElement(new InfoListItem("o/Enter", "��/����"));
        items.addElement(new InfoListItem("v", "�鿴����"));
        items.addElement(new InfoListItem("w", "�鿴����"));
        items.addElement(new InfoListItem("x", "�鿴֪ͨ��"));
        items.addElement(new InfoListItem("H", "�鿴ʮ����"));
        items.addElement(new InfoListItem(":", "�鿴����ʮ��"));
        items.addElement(new InfoListItem("s", "ѡ�����"));
        items.addElement(new InfoListItem("i", "�鿴���ӻ������Ϣ"));
        items.addElement(new InfoListItem("u", "�����û�"));
    }

    private void article(Vector items) {
        items.addElement(new InfoListItem("�鿴���� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "�ظ�����"));
        items.addElement(new InfoListItem("n", "��һƪ����"));
        items.addElement(new InfoListItem("p", "��һƪ����"));
        items.addElement(new InfoListItem("V", "����/�뿪����ѡ��ģʽ"));
        items.addElement(new InfoListItem("h", "�����ƶ����"));
        items.addElement(new InfoListItem("l", "�����ƶ����"));
        items.addElement(new InfoListItem("y", "����ѡ�е�����"));
    }

    private void board(Vector items) {
        items.addElement(new InfoListItem("���� - ��ݼ�"));
        items.addElement(new InfoListItem("p", "������"));
        items.addElement(new InfoListItem("c", "�������������δ�����"));
        items.addElement(new InfoListItem("r", "ˢ�������б�"));
        items.addElement(new InfoListItem("a", "�鿴����"));
        items.addElement(new InfoListItem("N", "�л�����ͨģʽ"));
        items.addElement(new InfoListItem("T", "�л�������ģʽ"));
        items.addElement(new InfoListItem("F", "�л�����̳ģʽ"));
        items.addElement(new InfoListItem("M", "�л�������ģʽ"));
        items.addElement(new InfoListItem("D", "�л�����ժģʽ"));
        items.addElement(new InfoListItem("Z", "�л����ö�ģʽ"));
    }

    private void fav(Vector items) {
        items.addElement(new InfoListItem("�ղؼ� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ǿ�Ƹ����ղؼ�"));
    }

    private void friendsAll(Vector items) {
        items.addElement(new InfoListItem("���к��� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ǿ�Ƹ��º����б�"));
        items.addElement(new InfoListItem("O", "�鿴���ߺ���"));
    }

    private void friends(Vector items) {
        items.addElement(new InfoListItem("���ߺ��� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ǿ�Ƹ��º����б�"));
        items.addElement(new InfoListItem("A", "�鿴���к���"));
    }

    private void hot(Vector items) {
        items.addElement(new InfoListItem("ʮ���� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ǿ�Ƹ���������Ϣ"));
        items.addElement(new InfoListItem("a", "�鿴����"));
    }

    private void mailBox(Vector items) {
        items.addElement(new InfoListItem("���� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ˢ���������ż�"));
        items.addElement(new InfoListItem("I", "�л����ռ���"));
        items.addElement(new InfoListItem("S", "�л���������"));
        items.addElement(new InfoListItem("D", "�л���������"));
    }

    private void mail(Vector items) {
        items.addElement(new InfoListItem("�鿴�ż� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "����"));
        items.addElement(new InfoListItem("n", "��һƪ�ż�"));
        items.addElement(new InfoListItem("p", "��һƪ�ż�"));
    }

    private void notificationMenu(Vector items) {
        items.addElement(new InfoListItem("֪ͨ�� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ˢ��֪ͨ��"));
        items.addElement(new InfoListItem("c", "���δ��֪ͨ"));
    }

    private void sections(Vector items) {
        items.addElement(new InfoListItem("���������� - ��ݼ�"));
        items.addElement(new InfoListItem("r", "ˢ�·���������(�ȽϺ�ʱ)"));
    }

    private void thread(Vector items) {
        items.addElement(new InfoListItem("�鿴���� - ��ݼ�"));
        items.addElement(new InfoListItem("p", "������"));
        items.addElement(new InfoListItem("r", "�ظ�����"));
        items.addElement(new InfoListItem("a", "�鿴����"));
    }

    private void user(Vector items) {
        items.addElement(new InfoListItem("�鿴�û� - ��ݼ�"));
        items.addElement(new InfoListItem("m", "�����û�д��"));
    }
}
