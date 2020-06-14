package jsj.mjc.mobileproject;

public class ScheduleItem {
    //private String emoticon_url;
    private String schedule, scheduleTime;

    public ScheduleItem (String schedule, String scheduleTime) {
        this.schedule = schedule;
        this.scheduleTime = scheduleTime;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }
}
