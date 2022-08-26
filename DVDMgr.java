package com.aaa.DVD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author: niuniu
 * @DateTime: 2022/6/20 8:42
 * @Description: TODO
 */
//DVD管理器
public class DVDMgr {
    Scanner input = new Scanner(System.in);
    DVD[] dvd = new DVD[4];
    int back; //返回键
    //初始化三个DVD数组对象
    public  void init(){
        DVD s1 = new DVD(1,"已借出","罗马假日",1,15);
        dvd[0]=s1;
        DVD s2 = new DVD(2,"可 借","风声鹤唳",0,12);
        dvd[1]=s2;
        DVD s3 = new DVD(3,"可 借","浪漫满屋",0,30);
        dvd[2]=s3;
    }
    //主菜单
    public void start(){
        do{
            System.out.println("欢迎使用 DVD 管理器");
            System.out.println("-----------------------------");
            System.out.println("1. 新增DVD\n2. 查看DVD\n3. 删除DVD\n4. 借出DVD\n5. 归还DVD\n6. 退出DVD");
            System.out.println("-----------------------------");
            System.out.print("请选择：");
            int num = input.nextInt();
            switch(num){
                case 1:
                    this.addDVD();
                    break;
                case 2:
                    this.queryDVD();
                    break;
                case 3:
                    this.delDVD();
                    break;
                case 4:
                    this.loan();
                    break;
                case 5:
                    this.backDVD();
                    break;
                case 6:
                    System.out.println("谢谢使用");
                    return;
                default:
                    System.out.println("输入错误");
                    break;
            }
            do{
                System.out.println("输入0返回");
                back = input.nextInt();
                if(back!=0){
                    System.out.println("输入错误");
                }
            }while(back!=0);
        }while(true);
    }

    //新增DVD
    public void addDVD(){
        System.out.println("---> 新增DVD\n");
        System.out.print("请输入DVD名称：");
        String name = input.next();
        //1.判断数组是否已满
        //2.找到第一个为null的下标，赋值
        boolean tag = false;
        for(int i=0;i<dvd.length;i++){
            if(dvd[i]==null){
                dvd[i]=new DVD();
                dvd[i].setName(name);
                System.out.println("新增《"+dvd[i].getName()+"》成功！");
                System.out.println("**************************");
                dvd[i].setState("可 借");
                dvd[i].setSerial(i+1);
                tag = true;
                break;
            }
        }
        if(!tag){
            System.out.println("新增《"+name+"》失败！货架已满！");
        }
    }
    //查看DVD
    public void queryDVD(){
        System.out.println("---> 查看DVD");
        System.out.println("\n序号\t状 态\t名称\t\t借出日期\t借出次数");
        int serial = 1;
        for(int i=0;i<dvd.length;i++){
            if(dvd[i]!=null){
                dvd[i].setSerial(serial++);
                dvd[i].setOut(dvd[i].getOutDate()+"日");
                if(dvd[i].getState().equals("可 借")){
                    dvd[i].setOut(" ");
                }
                //System.out.println(dvd[i]);
                System.out.println(dvd[i].getSerial()+"\t"+dvd[i].getState()+"\t<<"+dvd[i].getName()+">>\t"+dvd[i].getOut()+"\t"+dvd[i].getTime()+"次");
            }
        }
        System.out.println("**************************");
    }
    //删除DVD
    public void delDVD(){
        System.out.println("---> 删除DVD\n");
        System.out.print("请输入DVD名称：");
        String name = input.next();
//		（1）删除失败，没有找到匹配信息！
//		（2）删除失败： 为借出状态，不能删除！
//		（3）删除成功：
        int index=-1;
        boolean tag = false;
        for(int i=0;i<dvd.length;i++){
            if(dvd[i]!=null&&name.equals(dvd[i].getName())&&dvd[i].getState().equals("可 借")){
                index=i;
                tag = true;
                break;
            }else if(dvd[i]!=null&&name.equals(dvd[i].getName())&&dvd[i].getState().equals("已借出")){
                tag = true;
                System.out.println("《"+name+"》为借出状态，不能删除！");
                break;
            }
        }
        if(!tag){
            System.out.println("没有找到匹配信息！");
        }
        //删除操作
        if(index!=-1){
            for(int i=index;i<dvd.length-1;i++){
                dvd[i]=dvd[i+1];
            }
            dvd[dvd.length-1]=null;
            System.out.println("删除《"+name+"》成功！");
        }
        System.out.println("**************************");
    }
    //借出DVD
    public void loan(){
        System.out.println("---> 借出DVD\n");
        System.out.print("请输入DVD名称：");
        String name = input.next();
//		（1）借出DVD失败:
//     		没有DVD
//  		已经借出
//		（2）借出DVD成功：
        int index=-1;
        boolean tag = false;
        for(int i=0;i<dvd.length;i++){
            if(dvd[i]!=null&&name.equals(dvd[i].getName())&&"可 借".equals(dvd[i].getState())){
                index=i;
                tag=true;
                break;
            }else if(dvd[i]!=null&&name.equals(dvd[i].getName())&&"已借出".equals(dvd[i].getState())){
                tag=true;
                System.out.println("《"+name+"》已被借出！");
                break;
            }
        }
        if(!tag){
            System.out.println("《"+name+"》暂时没有上架！");
        }
        //借出操作
        if(index!=-1){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            String format = simpleDateFormat.format(date);
            System.out.println("借出日期:"+format);
            //System.out.println(format);
            //System.out.print("请输入借出日期：");
            System.out.println("请输入借出天数：");
            int outDate = input.nextInt();
            dvd[index].setOutDate(outDate);
            dvd[index].setState("已借出");
            dvd[index].setTime(dvd[index].getTime()+1);
            System.out.println("借出《"+name+"》成功！");
        }
        System.out.println("**************************");
    }
    //归还DVD
    public void backDVD(){
        System.out.println("---> 归还DVD\n");
        System.out.print("请输入DVD名称：");
        String name = input.next();
//		1.归还成功
//		2.归还失败
//			暂时没有此DVD
//			还没有借出
        int index=-1;
        boolean tag = false;
        for(int i=0;i<dvd.length;i++){
            if(dvd[i]!=null&&name.equals(dvd[i].getName())&&"已借出".equals(dvd[i].getState())){
                index=i;
                tag=true;
                break;
            }else if(dvd[i]!=null&&name.equals(dvd[i].getName())&&"可 借".equals(dvd[i].getState())){
                tag=true;
                System.out.println("《"+name+"》还没有被借出，不能归还！");
                break;
            }
        }
        if(!tag){
            System.out.println("《"+name+"》暂时没有上架！");
        }
        //借出操作
        if(index!=-1){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            String format = simpleDateFormat.format(date);
            System.out.println("归还日期:"+format);
            //System.out.println(format);
            //System.out.print("请输入归还日期：");
            System.out.print("请输入实际借出天数：");
            int backDate = input.nextInt();
            dvd[index].setState("可 借");
            System.out.println("\n归还《"+name+"》成功！");
            //System.out.println("借出日期为："+dvd[index].getOutDate()+"日");
            //System.out.println("归还日期为："+backDate+"日");
            System.out.println("借出天数为："+dvd[index].getOutDate()+"日");
            System.out.println("实际借出天数为："+backDate+"日");
            int rent = backDate-dvd[index].getOutDate();
            System.out.println("应付租金(元):"+rent);
        }
        System.out.println("**************************");
    }
}
