package name.wl.bbs.hjlp;

import java.util.*;

public class Board
{
    /* ����Ӣ���� */
    private String name;

    /* ���������� */
    private String description;

    /* ������Ŀ */
    private int count;

    /* �������� */
    private int users;

    /* ���� */
    private Vector bm;


    /* sections & favourate */
    /* ���������İ��� */
    private Vector boards;

    /* �Ƿ�ΪҶ�ӽڵ� */
    private boolean leaf;

    public Board()
    {
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCount()
    {
        return count;
    }

    public int getUsers()
    {
        return users;
    }

    public Vector getBm()
    {
        return bm;
    }

    public Vector getBoards()
    {
        return boards;
    }

    public boolean isLeaf()
    {
        return leaf;
    }
}
