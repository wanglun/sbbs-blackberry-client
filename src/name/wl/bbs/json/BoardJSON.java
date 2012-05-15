package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class BoardJSON extends ApiJSON
{
    /* 
     * "/board/test.json" ���滰��
     * "/board/SBBS_PT.json" Ŀ¼����
     * */
    private static String API = "/board/";

    /* --����-- */
    private Board board;

    /* ��ͨģʽ */
    public static final int NORMAL = 0;
    /* ����ģʽ */
    public static final int THREAD = 1;
    /* ��̳ģʽ */
    public static final int FORUM = 2;
    /* �ö� */
    public static final int TOP = 3;
    /* ��ժ */
    public static final int DIGEST = 4;
    /* ���� */
    public static final int MARK = 5;

    /* ��ѡ ����ģʽ */
    private int mode;

    /* ��ѡ ��ʼ�±� */
    private int start;

    private static int LIMIT = 10;
    /* ��ѡ ��໰����� */
    private int limit;

    /* --����-- */

    /* ���ص�������Ŀ */
    private int total;

    /* ���滰�� */
    private Vector topics;

    /* Ŀ¼չ������ */
    private Vector boards;

    public BoardJSON(Board board)
    {
        this(board, NORMAL, 0, LIMIT);
    }
    public BoardJSON(Board board, int mode)
    {
        this(board, mode, 0, LIMIT);
    }
    public BoardJSON(Board board, int mode, int start)
    {
        this(board, mode, start, LIMIT);
    }
    public BoardJSON(Board board, int mode, int start, int limit)
    {
        this.board = board;
        this.mode = mode;
        this.start = start;
        this.limit = limit;

        this.total = 0;
        this.topics = null;
        this.boards = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("mode", Integer.toString(this.mode));
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API + this.board.getName() + ".json", gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                /* ���� */
                if (this.board.isLeaf()) {
                    this.total = this.data.getInt("total");
                    this.topics = Topic.TopicsJSON(this.data.getString("topics"));
                } else {
                    /* Ŀ¼ */
                    this.boards = Board.BoardsJSON(this.data.getString("boards"));
                }
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public Vector getTopics()
    {
        return this.topics;
    }

    public int getTotal()
    {
        return this.total;
    }
}
