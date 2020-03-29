package com.scmp.base.model.selenium;

import java.util.List;

public class PageModel
{
    private String name;

    private String url;

    private List<PageAction> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PageAction> getActions() {
        return actions;
    }

    public void setActions(List<PageAction> actions) {
        this.actions = actions;
    }
}
