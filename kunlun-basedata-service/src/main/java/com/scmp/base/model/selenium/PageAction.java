package com.scmp.base.model.selenium;

public class PageAction
{
    private String type;

    private String byid;

    private String byxpath;

    private String text;

    private String script;

    private String waitTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getByid() {
        return byid;
    }

    public void setByid(String byid) {
        this.byid = byid;
    }

    public String getByxpath() {
        return byxpath;
    }

    public void setByxpath(String byxpath) {
        this.byxpath = byxpath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }
}
