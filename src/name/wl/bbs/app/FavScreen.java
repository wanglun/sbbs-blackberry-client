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
    private BoardListField list;

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
                list = new BoardListField(boards);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        FavScreen.this.add(list);
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("load fav failed!");
                    }
                });
            }
        }
    };
}
