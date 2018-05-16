package ch.ipt.moensch;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PRequest {

    private int id;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    private String request;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
