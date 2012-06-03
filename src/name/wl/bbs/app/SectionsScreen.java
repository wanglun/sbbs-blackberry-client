package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.SectionsJSON;

public class SectionsScreen extends BaseScreen
{
    private SectionsJSON sections;
    private Vector boards;
    private SectionListField list;

    public SectionsScreen()
    {
        sections = new SectionsJSON();

        alert("加载中", ALERT_WARNING);
        sections.load(loadListener);

        setStatusbarTitle("分类讨论区");
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
            SectionsJSON obj = (SectionsJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                list = new SectionListField(boards);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        SectionsScreen.this.add(list);
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
            SectionsJSON obj = (SectionsJSON)o;
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
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_SECTIONS));
                return true;
            case 'r':
                if (this.list != null) {
                    alert("刷新中", ALERT_WARNING);
                    sections.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
