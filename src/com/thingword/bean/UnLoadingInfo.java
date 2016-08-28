package com.thingword.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class UnLoadingInfo {
    @XmlElement(name="date")
    String date;
    @XmlElement(name="cBatch")
    String cBatch;
    @XmlElement(name="iQuantity")
    String iQuantity;
    @XmlElement(name="cInvStd")
    String cInvStd;
    @XmlElement(name="cInvName")
    String cInvName;
    @XmlElement(name="cInvCode")
    String cInvCode;
    @XmlElement(name="cInvDefine8")
    String cInvDefine8;
    @XmlElement(name="invcode")
    String invcode;
    @XmlElement(name="shopnum")
    String shopnum;


    public UnLoadingInfo(String date, String cBatch,String iQuantity, String cInvStd,String cInvName, String cInvCode,
    		String cInvDefine8,String invcode, String shopnum) {
        this.date = date;
        this.cBatch = cBatch;
        this.iQuantity = iQuantity;
        this.cInvStd = cInvStd;
        this.cInvName = cInvName;
        this.cInvCode = cInvCode;
        this.cInvDefine8 = cInvDefine8;
        this.invcode = invcode;
        this.shopnum = shopnum;
    }    

    @Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject().put("date", date).put("cBatch", cBatch).put("iQuantity", iQuantity)
            		.put("cInvStd", cInvStd).put("cInvName", cInvName).put("cInvCode", cInvCode)
            		.put("cInvDefine8", cInvDefine8).put("invcode", invcode).put("shopnum", shopnum).toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
