package name.wl.bbs.app;

import name.wl.bbs.json.LoginJSON;

import name.wl.bbs.util.*;

public class TestScreen extends BaseScreen
{
    public TestScreen()
    {
        Session s = Session.getInstance();
        s.login("irun", "wxhsbbsxbj");
    }
}
