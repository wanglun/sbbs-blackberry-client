package name.wl.bbs.hjlp;

import java.util.*;
import org.json.me.*;

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

    /* 是否有未读帖子 */
    private boolean unread = false;

    public Board()
    {
        this.bm = new Vector();
        this.boards = new Vector();
    }

    public Board(String name)
    {
        this.name = name;
    }

    public static Board BoardJSON(String json) throws JSONException
    {
        Board board = new Board();
        JSONObject data = new JSONObject(json);

        board.setName(data.getString("name"));
        board.setDescription(data.getString("description"));

        if (data.has("unread"))
            board.setUnread(data.getBoolean("unread"));

        if (data.has("leaf"))
            board.setLeaf(data.getBoolean("leaf"));

        if (!board.isLeaf()) {
            if (data.has("boards"))
                board.setBoards(BoardsJSON(data.getString("boards")));
        } else {
            if (data.has("count"))
                board.setCount(data.getInt("count"));
            if (data.has("bm"))
                board.setBm(data.getString("bm"));
        }

        return board;
    }

    public static Vector BoardsJSON(String json) throws JSONException
    {
        Vector boards = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            boards.addElement(BoardJSON(arr.getString(i)));
        }

        return boards;
    }

    public static Board SearchJSON(String json) throws JSONException
    {
        Board board = new Board();
        JSONObject data = new JSONObject(json);

        if (data.has("name"))
            board.setName(data.getString("name"));
        if (data.has("description"))
            board.setDescription(data.getString("description"));

        return board;
    }

    public static Vector SearchesJSON(String json) throws JSONException
    {
        Vector boards = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            boards.addElement(SearchJSON(arr.getString(i)));
        }

        return boards;
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

    public boolean isUnread()
    {
        return unread;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    protected void setDescription(String description)
    {
        this.description = description;
    }

    protected void setCount(int count)
    {
        this.count = count;
    }

    protected void setUsers(int users)
    {
        this.users = users;
    }

    protected void setBm(String bm) throws JSONException
    {
        JSONArray arr = new JSONArray(bm);

        for (int i = 0; i < arr.length(); i++) {
            this.bm.addElement(new User(arr.getString(i)));
        }
    }

    protected void setBoards(Vector boards)
    {
        this.boards = boards;
    }

    protected void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }

    protected void setUnread(boolean unread)
    {
        this.unread = unread;
    }
}
