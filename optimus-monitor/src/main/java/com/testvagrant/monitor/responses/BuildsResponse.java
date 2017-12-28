package com.testvagrant.monitor.responses;

import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.requests.Link;

import java.util.List;

public class BuildsResponse {
    private List<Link> links;
    private List<Build> content;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Build> getContent() {
        return content;
    }

    public void setContent(List<Build> content) {
        this.content = content;
    }
}
