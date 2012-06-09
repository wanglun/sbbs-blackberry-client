package name.wl.bbs.app;

import net.rim.device.api.ui.component .*;
import net.rim.device.api.collection.*; 
import net.rim.device.api.util.*;
import java.util.*;

class SelectList implements ReadableList, KeywordProvider
{
    private Vector vector = null;

    public SelectList(Vector vector)
    {
        this.vector = vector;
    }

    public Object getAt(int index)
    {
        return vector.elementAt(index);
    }

    public int getAt(int index, int count, Object[] elements, int destIndex)
    {
        int i = 0;
        for (i = 0; i < count && i < vector.size(); i++) {
            elements[i + destIndex] = vector.elementAt(i + index);
        }

        return i;
    }

    public int getIndex(Object element)
    {
        return this.vector.indexOf(element);
    }

    public int size()
    {
        return this.vector.size();
    }

    public String[] getKeywords(Object element)
    {        
        return StringUtilities.stringToWords(element.toString());
    } 
}
