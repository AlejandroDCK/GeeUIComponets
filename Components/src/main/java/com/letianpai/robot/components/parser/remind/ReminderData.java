package com.letianpai.robot.components.parser.remind;

import java.util.Arrays;

public class ReminderData {

    private int user_id;
    private String client_id;
    private String remind_type;
    private String[] week;
    private String start_time;
    private String end_time;
    private String[] point_time;
    private int status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRemind_type() {
        return remind_type;
    }

    public void setRemind_type(String remind_type) {
        this.remind_type = remind_type;
    }

    public String[] getWeek() {
        return week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String[] getPoint_time() {
        return point_time;
    }

    public void setPoint_time(String[] point_time) {
        this.point_time = point_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReminderData{" +
                "user_id=" + user_id +
                ", client_id='" + client_id + '\'' +
                ", remind_type='" + remind_type + '\'' +
                ", week='" + week + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", point_time=" + Arrays.toString(point_time) +
                ", status=" + status +
                '}';
    }
}
