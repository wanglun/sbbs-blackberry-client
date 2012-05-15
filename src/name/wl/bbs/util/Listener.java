package name.wl.bbs.util;

public abstract class Listener 
{
    private boolean loading = false;

    public abstract void callback(Object object);

    public void setLoading()
    {
        this.loading = true;
    }
    public void setLoaded()
    {
        this.loading = false;
    }
    public boolean isLoading()
    {
        return loading;
    }
}
