package com.aaa.DVD;

import java.util.Scanner;

/**
 * @Author: niuniu
 * @DateTime: 2022/6/20 8:43
 * @Description: TODO
 */
public class TestDVD {
    public static void main(String[] args) {
        System.out.println("请输入用户名");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("请输入密码");
        String next1 = scanner.next();
        if(next.equals("root")&&next1.equals("root")){
            DVDMgr mgr = new DVDMgr();
            mgr.init();
            mgr.start();
        }else {
            System.out.println("输入错误");
        }

    }
}
