package POJO;

public class SleepData {
    private int sleepid;
    private int userid;
    private String sleeptime;
    private int day;

    public SleepData(int sleepid,int userid,String sleeptime,int day){
        this.sleepid=sleepid;
        this.userid=userid;
        this.sleeptime=sleeptime;
        this.day=day;
    }

    public int getSleepid() {
        return sleepid;
    }

    public void setSleepid(int sleepid) {
        this.sleepid = sleepid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public int getDay() {
        return day;
    }

    public String getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(String sleeptime) {
        this.sleeptime = sleeptime;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
