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

        fav.load(loadListener);
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
            } else {
                alert("load fav failed!");
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
                    }
                });
            } else {
                alert("refresh fav failed!");
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                if (this.list != null) {
                    fav.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
