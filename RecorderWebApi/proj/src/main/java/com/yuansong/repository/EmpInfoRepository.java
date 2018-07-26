package com.yuansong.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yuansong.pojo.EmpInfo;

@Repository
public class EmpInfoRepository {
	
//	private final Logger logger = Logger.getLogger(EmpInfoRepository.class);
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO xtempinfo" + 
			"           ([FUserId]" + 
			"           ,[FUserName]" + 
			"           ,[Faddtime]" + 
			"           ,[Flastupdate]" + 
			"           ,[Fdelete])" + 
			"     VALUES" + 
			"           ( ? , ? , ? , ? , ?)";
	private static final String SQL_DEL_REAL = ""
			+ "DELETE FROM xtempinfo" + 
			"      WHERE FUserId = ?";
	private static final String SQL_UPDATE = ""
			+ "UPDATE xtempinfo" + 
			"   SET [FUserName] = ?" + 
			"      ,[Faddtime] = ?" + 
			"      ,[Flastupdate] = ?" + 
			"      ,[Fdelete] = ?" + 
			" WHERE FUserId = ?";
//	private static final String SQL_GET = "";
//	private static final String SQL_GETLIST = "";
//	private static final String SQL_GETLIST_DELETED = "";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void add(EmpInfo empInfo) {
		jdbcTemplate.update(SQL_ADD, new Object[] {
				empInfo.getUserId(),
				empInfo.getUserName(),
				empInfo.getAddTime(),
				empInfo.getLastUpdate(),
				empInfo.isDelete()?1:0
				});
	}
	
	public void del(EmpInfo empInfo, boolean real) {
		if(real) {
			jdbcTemplate.update(SQL_DEL_REAL, new Object[] {empInfo.getUserId()});
		}
		else {
			empInfo.setDelete(true);
			update(empInfo);
		}
	}
	
	public void update(EmpInfo empInfo) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] {
				empInfo.getUserName(),
				empInfo.getAddTime(),
				empInfo.getLastUpdate(),
				empInfo.isDelete()?1:0,
				empInfo.getUserId()
				});
	}
	
	public EmpInfo getEmp(Integer id) {
		return null;
	}
	
	public List<EmpInfo> getEmpList(){
		return null;
	}
	
	public List<EmpInfo> getEmpList(boolean isDeleted){
		return null;
	}
	
	
}
