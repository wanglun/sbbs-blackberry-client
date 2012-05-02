package name.wl.bbs.hjlp;

import java.util.*;

public class Board
{
    /* 版面英文名 */
    private String name;

    /* 版面中文名 */
    private String description;

    /* 帖子数目 */
    private int count;

    /* 在线人数 */
    private int users;

    /* 版主 */
    private Vector bm;


    /* sections & favourate */
    /* 分区包含的版面 */
    private Vector boards;

    /* 是否为叶子节点 */
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
