package com.warehouse.util.tools;

/**
 * Created by Le0n on 2017/2/28.
 */
import java.util.List;

/**
 * 判断对象是否为空
 *
 * @author Rex
 *
 */
public class EmptyUtil
{
    /**
     * 判断对象为空
     *
     * @param obj
     *            对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    /**
     * 判断对象不为空
     *
     * @param obj
     *            对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }
}