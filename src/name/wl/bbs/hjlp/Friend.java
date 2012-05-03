package name.wl.bbs.hjlp;

import java.util.*;
import org.json.me.*;

public class Friend extends User
{
    public static User UserJSON(String json) throws JSONException
    {
        User user = new User();
        JSONObject data = new JSONObject(json);

        /* 暂只处理 id和name */
        user.setId(data.getString("id"));
        user.setName(data.getString("name"));

        return user;
    }
}
