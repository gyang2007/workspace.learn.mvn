package com.gyang.learn.app.recursion;

/**
 * 假设一个楼梯有 N 阶台阶，人每次最多可以跨 M 阶，求总共的爬楼梯方案数。
 * 例如楼梯总共有3个台阶，人每次最多跨2个台阶，也就是说人每次可以走1个，也可以走2个，但最多不会超过2个，那么楼梯总共有这么3种走法：
 * 111，12，21
 * 
 * @author gyang
 *
 */
public class Stairs {
	 
    public static void main(String[] args) {
        int n = 5;
        int maxStep = 3;
        System.out.println("方案数：" + getStepNum(n, maxStep));
    }
    /**
     * 
     * @author Cxl
     * @version 2013-1-8 下午2:29:31
     * @Contract http://wzwahl36.net
     * @param n 总的台阶数
     * @param m 一次可以走的最大楼梯阶数
     * @return
     */
    private static int getStepNum(int n, int m) {
        int sumStep = 0;
        //总台阶数为0时，终止递归循环
        if (n == 0) {
            return 1;
        }
        if (n >= m) {
            //如果n大于每步最大台阶数，则设置第一步为m之内的一个台阶数，然后递归循环
            for (int i = 1; i <= m; i++) {
                sumStep += getStepNum(n - i, m);
            }
        }
        //如果n小于m，则将一步最大台阶数缩小为n，重新递归
        else {
            sumStep = getStepNum(n, n);
        }
        return sumStep;
    }
}
