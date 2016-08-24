package com.thingword.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.thingword.bean.MESSAGE;
import com.thingword.bean.ReqUserLogin;
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
        	System.out.println(e.getMessage()+" "+e.getCause());
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
            if(dbConn == null)
            {
            	System.out.println("error");
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM UserInfo WHERE username = '" + reqUserLogin.getUsername()
                    + "' AND passwd=" + "'" + reqUserLogin.getPasswd() + "'";
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
            String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
                    + uname + "','" + pwd + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
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
}