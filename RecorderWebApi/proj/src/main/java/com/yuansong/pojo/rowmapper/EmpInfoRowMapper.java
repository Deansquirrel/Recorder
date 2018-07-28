package com.yuansong.pojo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.EmpInfo;

public class EmpInfoRowMapper implements RowMapper<EmpInfo> {

	@Override
	public EmpInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpInfo obj = new EmpInfo();
		obj.setUserId(rs.getInt("FUserId"));
		obj.setUserName(rs.getString("FUserName"));
		obj.setAddTime(rs.getDate("Faddtime"));
		obj.setLastUpdate(rs.getDate("Flastupdate"));
		int delete = rs.getInt("Fdelete");
		if(delete == 0) {
			obj.setDeleted(false);
		}
		else {
			obj.setDeleted(true);
		}
		return obj; 
	}

}
