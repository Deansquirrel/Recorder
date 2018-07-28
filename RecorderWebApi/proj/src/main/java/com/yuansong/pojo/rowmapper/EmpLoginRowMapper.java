package com.yuansong.pojo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.EmpLogin;

public class EmpLoginRowMapper implements RowMapper<EmpLogin> {

	@Override
	public EmpLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmpLogin obj = new EmpLogin();
		obj.setUserId(rs.getInt("FUserId"));
		obj.setLoginName("FLoginName");
		obj.setUserPwd("FUserPwd");
		obj.setAddTime(rs.getDate("Faddtime"));
		obj.setLastUpdate(rs.getDate("Flastupdate"));
		if(rs.getInt("Fdelete") == 0) {
			obj.setDeleted(false);
		}
		else {
			obj.setDeleted(true);
		}
		return obj;
	}


}
