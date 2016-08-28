package com.thingword.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thingword.bean.DistributionInfo;
import com.thingword.bean.LoadingInfo;
import com.thingword.bean.MESSAGE;
import com.thingword.bean.ReqLoadingInfo;
import com.thingword.bean.ReqUserLogin;
import com.thingword.bean.UnLoadingInfo;
import com.thingword.bean.UserLoginInfo;

public class DBConnection {
	/**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " " + e.getCause());
			throw e;
		} finally {
			return con;
		}
	}

	/**
	 * Method to check whether uname and pwd combination are correct
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static UserLoginInfo checkLogin(ReqUserLogin reqUserLogin) throws Exception {
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		userLoginInfo.setReturn_code(MESSAGE.RETURN_FAIL);
		userLoginInfo.setReturn_msg(MESSAGE.LOGIN_FAIL);
		userLoginInfo.setAuthority("");
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dbConn == null) {
				System.out.println("error");
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM UserInfo WHERE username = '" + reqUserLogin.getUsername() + "' AND passwd="
					+ "'" + reqUserLogin.getPasswd() + "'";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				userLoginInfo.setReturn_code(MESSAGE.RETURN_SUCCESS);
				userLoginInfo.setReturn_msg(MESSAGE.LOGIN_SUCCESS);
				userLoginInfo.setAuthority(rs.getString("authority"));
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return userLoginInfo;
	}

	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into user(name, username, password) values('" + name + "'," + "'" + uname + "','"
					+ pwd + "')";
			// System.out.println(query);
			int records = stmt.executeUpdate(query);
			// System.out.println(records);
			// When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			// sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}

	public static List<LoadingInfo> getLodingInfo(ReqLoadingInfo reqLoadingInfo) throws Exception {
		List<LoadingInfo> data = new ArrayList<>();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dbConn == null) {
				System.out.println("error");
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM loadingInfo WHERE date = '" + reqLoadingInfo.getDate()+"'";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				LoadingInfo loadingInfo = new LoadingInfo(rs.getString("date"), rs.getString("cBatch"),
						rs.getString("iQuantity"), rs.getString("cInvStd"), rs.getString("cInvName"),
						rs.getString("cInvCode"));
				data.add(loadingInfo);

			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return data;
	}
	
	public static List<UnLoadingInfo> getUnLodingInfo(ReqLoadingInfo reqLoadingInfo) throws Exception {
		List<UnLoadingInfo> data = new ArrayList<>();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dbConn == null) {
				System.out.println("error");
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM unloadingInfo WHERE executor = '" + reqLoadingInfo.getPerson() + "' AND date="
			+ "'" + reqLoadingInfo.getDate()+ "'";
			
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UnLoadingInfo unloadingInfo = new UnLoadingInfo(rs.getString("date"), rs.getString("cBatch"),
						rs.getString("iQuantity"), rs.getString("cInvStd"), rs.getString("cInvName"),
						rs.getString("cInvCode"),rs.getString("cInvDefine8"), rs.getString("invcode"),
						rs.getString("shopnum"));
				data.add(unloadingInfo);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return data;
	}
	
	public static List<DistributionInfo> getDistriInfo(ReqLoadingInfo reqLoadingInfo) throws Exception {
		List<DistributionInfo> data = new ArrayList<>();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dbConn == null) {
				System.out.println("error");
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM distriInfo WHERE date = '" + reqLoadingInfo.getDate()+"'";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				DistributionInfo distributionInfo = new DistributionInfo(rs.getString("date"), rs.getString("cBatch"),
						rs.getString("iQuantity"), rs.getString("cInvStd"), rs.getString("cInvName"),
						rs.getString("cInvCode"),rs.getString("shopnum"), rs.getString("cMoCode"), rs.getString("invcode"),
						rs.getString("cinvstd_1"));
				data.add(distributionInfo);

			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return data;
	}
}