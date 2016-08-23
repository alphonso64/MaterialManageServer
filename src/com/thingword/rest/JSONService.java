package com.thingword.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thingword.bean.MESSAGE;
import com.thingword.bean.ReqUserLogin;
import com.thingword.bean.Track;
import com.thingword.bean.UserLoginInfo;

@Path("/json")
public class JSONService {

//	@GET
//	@Path("/get")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Track getTrackInJSON() {
//
//		Track track = new Track();
//		track.setTitle("Enter Sandman");
//		track.setSinger("Metallica");
//
//		return track;
//
//	}
//
//	@POST
//	@Path("/post")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createTrackInJSON(Track track) {
//		String result = "Track saved : " + track;
//		return Response.status(201).entity(result).build();	
//	}
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
		UserLoginInfo logininfo = new UserLoginInfo();
		if(reqinfo.getUsername().equals("tcc")&&reqinfo.getPasswd().equals("123")){
			logininfo.setReturn_code(MESSAGE.RETURN_SUCCESS);
			logininfo.setReturn_msg(MESSAGE.LOGIN_SUCCESS);
		}else{
			logininfo.setReturn_code(MESSAGE.RETURN_FAIL);
			logininfo.setReturn_msg(MESSAGE.LOGIN_FAIL);
		}
		return logininfo;
	}
	
	
}