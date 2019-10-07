package POJO;

public class AbsorbedData {
    private int absorbid;
    private int userid;
    private String sleeptime;
    private int day;

    public AbsorbedData(int Absorbid, int userid, String sleeptime, int day){
        this.absorbid=Absorbid;
        this.userid=userid;
        this.sleeptime=sleeptime;
        this.day=day;
    }

    public int getAbsorbid() {
        return absorbid;
    }

    public void setAbsorbid(int absorbid) {
        this.absorbid = absorbid;
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
