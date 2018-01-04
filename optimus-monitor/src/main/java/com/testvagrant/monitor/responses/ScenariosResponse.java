package com.testvagrant.monitor.responses;

import com.testvagrant.monitor.requests.Link;
import com.testvagrant.monitor.requests.Scenario;

import java.util.List;

public class ScenariosResponse {

    private List<Link> links;
    private List<Scenario> content;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Scenario> getContent() {
        return content;
    }

    public void setContent(List<Scenario> content) {
        this.content = content;
    }
}
