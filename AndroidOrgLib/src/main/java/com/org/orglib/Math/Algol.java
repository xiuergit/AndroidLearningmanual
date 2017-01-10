package com.org.orglib.Math;

/**
 * 一些简单的算法
 * Created by xiuer on 16/12/9.
 */

public class Algol {

    /**
     * 计算阶乘  res＝m*(m-1)(m-2).....0;
     *
     * @param m
     * @return 返回 m的阶乘
     */

    public static long fac(int m) {

        long f;
        if (m == 0 || m == 1) {
            f = 1;
        } else {

            f = m * fac(m - 1);
        }

        return f;
    }

    /**
     * 计算Berntein 函数 组合项 Cn
     *
     * @param n
     * @param i
     * @return
     */
    public static double cni(int n, int i) {

        return (double) (fac(n)) / (fac(i) * fac(n - i));
    }


    /**
     * 根据坐标点  返回曲线上的坐标点
     *
     * @param t     (0-1)   曲度
     * @param i     当前第几个点
     * @param n     点的个数
     * @param point 直线上的点
     * @return
     */
    public static ZPoint curvePoint(double t, int i, int n, ZPoint point) {

        ZPoint zPoint = new ZPoint();

        zPoint.setmX((float) (point.getmX() * cni(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i)));

        zPoint.setmY((float) (point.getmY() * cni(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i)));
        return zPoint;


    }


}
