package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SectionsJSON extends ApiJSON
{
    private static String API = "/sections.json";

    /* --����-- */
    /* ��ѡ �Զ�����..��Ϊ�ϲ�Ŀ¼ */
    private boolean up = false;

    /* --����-- */
    private Vector boards;

    public SectionsJSON()
    {
        this.boards = null;
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.boards = Board.BoardsJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
