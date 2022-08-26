package com.aaa.DVD;

/**
 * @Author: niuniu
 * @DateTime: 2022/6/19 16:25
 * @Description: TODO
 */
public class DVD {
    private int serial;    //序号
    private String state;  //状态
    private String name;   //名称
    private int outDate;   //借出日期
    private String out;
    private int time;      //借出次数

    public DVD(){
    }
    public DVD(int serial,String state,String name,int outDate,int time){
        this.serial=serial;
        this.state=state;
        this.name=name;
        this.outDate=outDate;
        this.time=time;
    }

    public int getOutDate() {
        return outDate;
    }
    public void setOutDate(int outDate) {
        this.outDate = outDate;
    }
    public String getOut() {
        return out;
    }
    public void setOut(String out) {
        this.out = out;
    }
    public int getSerial() {
        return serial;
    }
    public void setSerial(int serial) {
        this.serial = serial;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    //查看格式
    /*public String toString() {
        return this.getSerial()+"\t"+this.getState()+"\t<<"+this.getName()+">>\t"+this.getOut()+"\t"+this.getTime()+"次";
    }*/


}
