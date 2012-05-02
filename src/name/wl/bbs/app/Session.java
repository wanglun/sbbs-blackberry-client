package name.wl.bbs.app;

import java.util.Hashtable;

import name.wl.bbs.json.LoginJSON;
import name.wl.bbs.util.*;

public class Session
{
    protected String user = null;

    protected String token = null;

    protected boolean loggedIn = false;

    protected static Session singleSession = null;

    /**
     * Private constructor for singleton.
     */
    private Session()
    {
    }

    public static Session getInstance() 
    {
        if (singleSession == null) {
            singleSession = new Session();
        }

        return singleSession;
    }

    public boolean login(String user, String pass)
    {
        this.user = user;
        LoginJSON login = new LoginJSON(user, pass);
        Event.observe(login, "LOADED", this.loginListener);
        login.load();

        return true;
    }

    public boolean logout()
    {
        return true;
    }

    /**
     * Checks if there is a currently authenticated user session.
     * 
     * @return
     */
    public boolean isLoggedIn()
    {
        return this.loggedIn;
    }
    /**
     * Gets username.
     * 
     * @return
     */
    public String getUsername()
    {
        return this.user;
    }
    /**
     * Gets the cookie for the currently authenticated user.
     * 
     * @return
     */
    public String getToken()
    {
        return (this.token == null) ? "" : this.token;
    }

    // Event Listeners
    /**
     * Listener for login HTTP request.
     */
    public Listener loginListener = new Listener()
    {
        public void callback(Object r)
        {
            if (((LoginJSON) r).getSuccess()) {
                token = ((LoginJSON) r).getToken();
                loggedIn = true;
            }

            Event.trigger(getInstance(), "LOGIN");
        }
    };

    public Listener logoutListener = new Listener()
    {
        public void callback(Object r)
        {
            user = null;
            token = null;
            loggedIn = false;

            Event.trigger(getInstance(), "LOGOUT");
        }
    };
}
