package name.wl.bbs.hjlp;

public class User
{
    /* ID */
    private String id;

    /* 昵称 */
    private String name;

    /* 头像URI */
    private String avatar;

    /* 上次登录时间 */
    private int lastlogin;
    
    /* 等级 */
    private String level;

    /* 文章数 */
    private int posts;

    /* 表现值 */
    private int perform;

    /* 经验值 */
    private int experience;

    /* 勋章数 */
    private int medals;

    /* 上站次数 */
    private int logins;

    /* 生命力 */
    private int life;

    /* 性别 'M'男 'F'女 */
    private char gender;

    /* 星座 */
    private String astro;

    public User()
    {
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
}
