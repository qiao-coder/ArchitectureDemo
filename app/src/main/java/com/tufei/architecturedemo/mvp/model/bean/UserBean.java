package com.tufei.architecturedemo.mvp.model.bean;

/**
 * @author tufei
 * @date 2017/11/15.
 */

public class UserBean {
    String id;

    String name;

    String evaluation;

    public UserBean(String id,String name, String evaluation) {
        this.id = id;
        this.name = name;
        this.evaluation = evaluation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
