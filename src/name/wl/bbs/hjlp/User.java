package name.wl.bbs.hjlp;

import java.util.*;
import org.json.me.*;

public class User
{
    /* ID */
    private String id;

    /* �ǳ� */
    private String name;

    /* ͷ��URI */
    private String avatar;

    /* �ϴε�¼ʱ�� */
    private int lastlogin;
    
    /* �ȼ� */
    private String level;

    /* ������ */
    private int posts;

    /* ����ֵ */
    private int perform;

    /* ����ֵ */
    private int experience;

    /* ѫ���� */
    private int medals;

    /* ��վ���� */
    private int logins;

    /* ������ */
    private int life;

    /* �Ա� 'M'�� 'F'Ů */
    private char gender;

    /* ���� */
    private String astro;

    public User()
    {
    }

    public User(String id)
    {
        this.id = id;
    }

    public static User UserJSON(String json) throws JSONException
    {
        User user = new User();
        JSONObject data = new JSONObject(json);

        user.setId(data.getString("id"));
        user.setName(data.getString("name"));
        user.setAvatar(data.getString("avatar"));
        user.setLastlogin(data.getInt("lastlogin"));
        user.setLevel(data.getString("level"));
        user.setPosts(data.getInt("posts"));
        user.setPerform(data.getInt("perform"));
        user.setExperience(data.getInt("experience"));
        user.setMedals(data.getInt("medals"));
        user.setLogins(data.getInt("logins"));
        user.setLife(data.getInt("life"));

        try {
            user.setGender(data.getString("gender"));
            user.setAstro(data.getString("astro"));
        } catch (Exception e) {
        }

        return user;
    }

    public static Vector UsersJSON(String json) throws JSONException
    {
        Vector users = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            users.addElement(UserJSON(arr.getString(i)));
        }

        return users;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public int getLastlogin()
    {
        return lastlogin;
    }

    public String getLevel()
    {
        return level;
    }

    public int getPosts()
    {
        return posts;
    }

    public int getPerform()
    {
        return perform;
    }

    public int getExperience()
    {
        return experience;
    }

    public int getMedals()
    {
        return medals;
    }

    public int getLogins()
    {
        return logins;
    }

    public int getLife()
    {
        return life;
    }

    public char getGender()
    {
        return gender;
    }

    public String getAstro()
    {
        return astro;
    }

    protected void setId(String id)
    {
        this.id = id;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    protected void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    protected void setLastlogin(int lastlogin)
    {
        this.lastlogin = lastlogin;
    }

    protected void setLevel(String level)
    {
        this.level = level;
    }

    protected void setPosts(int posts)
    {
        this.posts = posts;
    }

    protected void setPerform(int perform)
    {
        this.perform = perform;
    }

    protected void setExperience(int experience)
    {
        this.experience = experience;
    }

    protected void setMedals(int medals)
    {
        this.medals = medals;
    }

    protected void setLogins(int logins)
    {
        this.logins = logins;
    }

    protected void setLife(int life)
    {
        this.life = life;
    }

    protected void setGender(String gender)
    {
        this.gender = gender.charAt(0);
    }

    protected void setAstro(String astro)
    {
        this.astro = astro;
    }
}
