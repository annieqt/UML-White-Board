package util;

/**
 * Created by IntelliJ IDEA.
 * User: qrs
 * Date: 12-4-4
 * Time: 下午10:48
 * To change this template use File | Settings | File Templates.
 */
public class U {
    public static double distance(int x, int y, int x0, int y0) {
        return Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0));
    }
}
