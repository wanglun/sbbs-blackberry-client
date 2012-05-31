package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.FavJSON;

public class FavScreen extends BaseScreen
{
    private FavJSON fav;
    private Vector boards;
    private FavListField list = null;

    public FavScreen()
    {
        fav = new FavJSON();

        alert("加载中", ALERT_WARNING);
        fav.load(loadListener);

        setStatusbarTitle("收藏夹");
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
            FavJSON obj = (FavJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                list = new FavListField(boards);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        FavScreen.this.add(list);
                    }
                });
                alert("加载完成");
            } else {
                alert("错误:" + obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            FavJSON obj = (FavJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        list.setBoards(boards);
                        alert("刷新成功");
                    }
                });
            } else {
                alert("错误:" + obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                if (this.list != null) {
                    alert("正在刷新", ALERT_WARNING);
                    fav.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
