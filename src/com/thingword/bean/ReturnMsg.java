package com.thingword.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class ReturnMsg {
	@XmlElement(name="return_msg")
	private String return_msg;
	@XmlElement(name="return_code")
	private String return_code;
	@XmlElement(name="data")
	private String data;
	
    public ReturnMsg(String return_msg, String return_code,String data) {
        this.return_msg = return_msg;
        this.return_code = return_code;
        this.data = data;
    }    

    @Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject().put("return_msg", return_msg).put("return_code", return_code).put("data", data).toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
