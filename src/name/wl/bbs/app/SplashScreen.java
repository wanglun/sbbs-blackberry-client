package name.wl.bbs.app;

import name.wl.bbs.ui.*;
import name.wl.bbs.json.*;

public class SplashScreen extends BaseScreen
{
    private BbsProgressField progress = null;

    public SplashScreen()
    {
        progress = new BbsProgressField("启动中..", 0, 100, 20);
        add(progress);

        bbs.invokeLater(new load());
    }

    class load implements Runnable
    {
        public void run()
        {
            // get the cached token
            LoginJSON login = new LoginJSON();
            login.loadCached();
            if (login.getSuccess()) {
                bbs.setId(login.getId());
                bbs.setName(login.getName());
                bbs.setToken(login.getToken());
                bbs.setLoggedIn(true);
            }

            progress.setValue(50);
            progress.setLabel("加载分区数据");

            // get the cached sections
            SectionsJSON sections = new SectionsJSON();
            sections.loadCached();
            if (sections.getSuccess()) {
                bbs.setSections(sections.getBoards());
            }

            destroy();
        }
    }

    private void destroy()
    {
        bbs.popScreen(this);

        if (bbs.isLoggedIn()) {
            bbs.pushScreen(new MenuScreen());
        } else {
            bbs.pushScreen(new LoginScreen());
        }
    }
}
