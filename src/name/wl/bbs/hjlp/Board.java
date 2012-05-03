package name.wl.bbs.hjlp;

import java.util.*;
import org.json.me.*;

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
        board.setCount(data.getInt("count"));
        board.setBm(data.getString("bm"));
        board.setLeaf(data.getBoolean("leaf"));

        if (!board.isLeaf()) {
            board.setBoards(BoardsJSON(data.getString("boards")));
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

    private void setName(String name)
    {
        this.name = name;
    }

    private void setDescription(String description)
    {
        this.description = description;
    }

    private void setCount(int count)
    {
        this.count = count;
    }

    private void setUsers(int users)
    {
        this.users = users;
    }

    private void setBm(String bm) throws JSONException
    {
        JSONArray arr = new JSONArray(bm);

        for (int i = 0; i < arr.length(); i++) {
            this.bm.addElement(new User(arr.getString(i)));
        }
    }

    private void setBoards(Vector boards)
    {
        this.boards = boards;
    }

    private void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }
}
