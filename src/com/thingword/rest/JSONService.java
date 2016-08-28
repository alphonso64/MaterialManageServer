package com.thingword.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thingword.bean.DistributionInfo;
import com.thingword.bean.LoadingInfo;
import com.thingword.bean.MESSAGE;
import com.thingword.bean.ReqLoadingInfo;
import com.thingword.bean.ReqUserLogin;
import com.thingword.bean.ReturnMsg;
import com.thingword.bean.Track;
import com.thingword.bean.UnLoadingInfo;
import com.thingword.bean.UserLoginInfo;
import com.thingword.db.DBConnection;

@Path("/json")
public class JSONService {
	// @GET
	// @Path("/get")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Track getTrackInJSON() {
	//
	// Track track = new Track();
	// track.setTitle("Enter Sandman");
	// track.setSinger("Metallica");
	//
	// return track;
	//
	// }
	//
	// @POST
	// @Path("/post")
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response createTrackInJSON(Track track) {
	// String result = "Track saved : " + track;
	// return Response.status(201).entity(result).build();
	// }
	//
	@POST
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Track createTrackInJSON_(Track track_) {
		Track track = new Track();
		track.setTitle("Enter Sandmanas");
		track.setSinger("Metallica");
		return track;
	}

	@POST
	@Path("/reqUserLoginInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserLoginInfo reqUserLogin(ReqUserLogin reqinfo) {
		try {
			UserLoginInfo userLoginInfo = DBConnection.checkLogin(reqinfo);
			return userLoginInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/reqLoadingInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoadingInfo(ReqLoadingInfo reqLoadingInfo) {
		List<LoadingInfo> data;
		ReturnMsg msg;
		try {
			data = DBConnection.getLodingInfo(reqLoadingInfo);

//			if (data.size() != 0) {
//				msg = new ReturnMsg(MESSAGE.RETURN_SUCCESS, MESSAGE.RETURN_SUCCESS, data.toString());
//				return msg.toString();
//			}
			return data.toString();
		} catch (Exception e) {
			
		}
//		msg = new ReturnMsg(MESSAGE.RETURN_FAIL, MESSAGE.RETURN_FAIL, null);
//		return msg.toString();
		return null;
	}
	
	@POST
	@Path("/reqUnLoadingInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUnLoadingInfo(ReqLoadingInfo reqLoadingInfo) {
		List<UnLoadingInfo> data;
		ReturnMsg msg;
		try {
			data = DBConnection.getUnLodingInfo(reqLoadingInfo);

//			if (data.size() != 0) {
//				msg = new ReturnMsg(MESSAGE.RETURN_SUCCESS, MESSAGE.RETURN_SUCCESS, data.toString());
//				return msg.toString();
//			}
			return data.toString();
		} catch (Exception e) {
			
		}
//		msg = new ReturnMsg(MESSAGE.RETURN_FAIL, MESSAGE.RETURN_FAIL, null);
//		return msg.toString();
		return null;
	}
	
	@POST
	@Path("/reqDistriInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getDistriInfo(ReqLoadingInfo reqLoadingInfo) {
		List<DistributionInfo> data;
		ReturnMsg msg;
		try {
			data = DBConnection.getDistriInfo(reqLoadingInfo);

//			if (data.size() != 0) {
//				msg = new ReturnMsg(MESSAGE.RETURN_SUCCESS, MESSAGE.RETURN_SUCCESS, data.toString());
//				return msg.toString();
//			}
			return data.toString();
		} catch (Exception e) {
			
		}
//		msg = new ReturnMsg(MESSAGE.RETURN_FAIL, MESSAGE.RETURN_FAIL, null);
//		return msg.toString();
		return null;
	}
}