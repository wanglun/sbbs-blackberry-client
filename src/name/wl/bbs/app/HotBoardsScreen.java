package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.HotBoardsJSON;

public class HotBoardsScreen extends BaseScreen
{
    private HotBoardsJSON hotboardsJSON;
    private Vector boards;
    private BoardListField list;

    public HotBoardsScreen()
    {
        hotboardsJSON = new HotBoardsJSON();

        alert("加载中", ALERT_WARNING);
        hotboardsJSON.load(loadListener);

        setStatusbarTitle("热门版面");
    }

    public void close()
    {
        if (this.list.setPrevious()) {
        } else {
            super.close();
        }
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            HotBoardsJSON obj = (HotBoardsJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                list = new BoardListField(boards);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        HotBoardsScreen.this.add(list);
                    }
                });
                alert("加载完成");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            HotBoardsJSON obj = (HotBoardsJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        list.setBoards(boards);
                    }
                });
                alert("刷新完成");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                if (list != null) {
                    alert("刷新中", ALERT_WARNING);
                    hotboardsJSON.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
