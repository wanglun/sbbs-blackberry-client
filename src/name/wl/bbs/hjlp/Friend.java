package name.wl.bbs.hjlp;

import java.util.*;
import org.json.me.*;

public class Friend extends User
{
    private boolean noexist = false;

    public static Friend FriendJSON(String json) throws JSONException
    {
        Friend user = new Friend();
        JSONObject data = new JSONObject(json);

        /* 暂只处理 id和name */
        user.setId(data.getString("id"));
        user.setName(data.getString("name"));

        try {
            user.setNoexist(data.getBoolean("noexist"));
        } catch (Exception e) {
        }

        return user;
    }

    public static Vector UsersJSON(String json) throws JSONException
    {
        Vector users = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            users.addElement(FriendJSON(arr.getString(i)));
        }

        return users;
    }

    public void setNoexist(boolean noexist)
    {
        this.noexist = noexist;
    }

    public boolean getNoexist()
    {
        return this.noexist;
    }
}
