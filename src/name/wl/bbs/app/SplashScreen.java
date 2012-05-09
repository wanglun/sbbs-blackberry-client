package name.wl.bbs.app;

import name.wl.bbs.ui.*;

public class SplashScreen extends BaseScreen
{
    public SplashScreen()
    {
        add(new BbsProgressField("test", 0, 100, 30));

        bbs.invokeLater(new load());
    }

    class load implements Runnable
    {
        public void run()
        {
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
