package com.testvagrant.monitor.responses;

import com.testvagrant.monitor.requests.Device;
import com.testvagrant.monitor.requests.Link;

import java.util.List;

public class DevicesResponse {

    private List<Link> links;
    private List<Device> content;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Device> getContent() {
        return content;
    }

    public void setContent(List<Device> content) {
        this.content = content;
    }
}
