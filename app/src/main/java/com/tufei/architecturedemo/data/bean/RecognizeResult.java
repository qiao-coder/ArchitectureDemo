package com.tufei.architecturedemo.data.bean;

import java.util.List;

public class RecognizeResult {

    /**
     * group_id : taxrobot
     * scores : [96.190711975098]
     * uid : 1500449711307
     * user_info : {"name":"土匪","id":"123456"}
     */

    private String group_id;
    private String uid;
    private String user_info;
    private List<Double> scores;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }
}
