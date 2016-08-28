package com.thingword.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class DistributionInfo {
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
    @XmlElement(name="shopnum")
    String shopnum;
    @XmlElement(name="cMoCode")
    String cMoCode;
    @XmlElement(name="invcode")
    String invcode;
    @XmlElement(name="cinvstd_1")
    String cinvstd_1;


    public DistributionInfo(String date, String cBatch,String iQuantity, 
    		String cInvStd,String cInvName, String cInvCode,
    		String shopnum,String cMoCode, String invcode,String cinvstd_1) {
        this.date = date;
        this.cBatch = cBatch;
        this.iQuantity = iQuantity;
        this.cInvStd = cInvStd;
        this.cInvName = cInvName;
        this.cInvCode = cInvCode;
        this.shopnum = shopnum;
        this.cMoCode = cMoCode;
        this.invcode = invcode;
        this.cinvstd_1 = cinvstd_1;
    }    

    @Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject().put("date", date).put("cBatch", cBatch).put("iQuantity", iQuantity)
            		.put("cInvStd", cInvStd).put("cInvName", cInvName).put("cInvCode", cInvCode)
            		.put("shopnum", shopnum).put("cMoCode", cMoCode).put("invcode", invcode).put("cinvstd_1", cinvstd_1).toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
