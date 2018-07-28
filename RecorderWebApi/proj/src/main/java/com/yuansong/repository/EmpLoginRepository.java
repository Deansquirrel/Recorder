package com.yuansong.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.pojo.EmpLogin;
import com.yuansong.pojo.rowmapper.EmpLoginRowMapper;

@Repository
@Transactional
public class EmpLoginRepository implements DaoRepository<EmpLogin, Integer> {
	
//	private final Logger logger = Logger.getLogger(EmpLoginRepository.class);
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [xtemplogin]" + 
			"           ([FUserId]" + 
			"           ,[FLoginName]" + 
			"           ,[FUserPwd]" + 
			"           ,[Faddtime]" + 
			"           ,[Flastupdate]" + 
			"           ,[Fdelete])" + 
			"     VALUES" + 
			"           ( ? , ? , ? , ? , ? , ?)";
	private static final String SQL_DEL = ""
			+ "DELETE FROM [xtemplogin]" + 
			"      WHERE [FUserId] = ?";
	private static final String SQL_UPDATE = ""
			+ "UPDATE [xtemplogin]" + 
			"   SET [FLoginName] = ?" + 
			"      ,[FUserPwd] = ?" + 
			"      ,[Faddtime] = ?" + 
			"      ,[Flastupdate] = ?" + 
			"      ,[Fdelete] = ?" + 
			" WHERE [FUserId] = ?";
	private static final String SQL_GET = ""
			+ "SELECT [FUserId]" + 
			"      ,[FLoginName]" + 
			"      ,[FUserPwd]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtemplogin]" + 
			"  WHERE [FUserId] = ?";
	private static final String SQL_GETLIST = ""
			+ "SELECT [FUserId]" + 
			"      ,[FLoginName]" + 
			"      ,[FUserPwd]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtemplogin]";
	private static final String SQL_GETLIST_DELETED = ""
			+ "SELECT [FUserId]" + 
			"      ,[FLoginName]" + 
			"      ,[FUserPwd]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtemplogin]" + 
			"  WHERE [Fdelete] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(EmpLogin dao) {
		jdbcTemplate.update(SQL_ADD, new Object[] {
				dao.getUserId(),
				dao.getLoginName(),
				dao.getUserPwd(),
				dao.getAddTime(),
				dao.getLastUpdate(),
				dao.isDeleted()?1:0
		});
		
	}

	@Override
	public void del(EmpLogin dao) {
		jdbcTemplate.update(SQL_DEL, new Object[] {dao.getUserId()});
	}

	@Override
	public void update(EmpLogin dao) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] {
				dao.getLoginName(),
				dao.getUserPwd(),
				dao.getAddTime(),
				dao.getLastUpdate(),
				dao.isDeleted()?1:0,
				dao.getUserId()
				});
	}

	@Override
	public EmpLogin get(Integer id) {
		List<EmpLogin> list = jdbcTemplate.query(SQL_GET, new Object[] {id}, new EmpLoginRowMapper());
		if(list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public List<EmpLogin> getList() {
		return jdbcTemplate.query(SQL_GETLIST, new EmpLoginRowMapper());
	}

	@Override
	public List<EmpLogin> getList(boolean deleted) {
		return jdbcTemplate.query(SQL_GETLIST_DELETED, new Object[] {deleted?1:0}, new EmpLoginRowMapper());
	}
	
}
