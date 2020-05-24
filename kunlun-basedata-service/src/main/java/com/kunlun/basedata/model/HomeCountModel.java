package com.kunlun.basedata.model;

public class HomeCountModel {
    private String id;

    private int userCount;

    private int onlineCount;

    private int leastCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public int getLeastCount() {
        return leastCount;
    }

    public void setLeastCount(int leastCount) {
        this.leastCount = leastCount;
    }
}
