package com.yuansong.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.pojo.EmpInfo;
import com.yuansong.pojo.rowmapper.EmpInfoRowMapper;

@Repository
@Transactional
public class EmpInfoRepository implements DaoRepository<EmpInfo, Integer> {
	
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
	private static final String SQL_DEL= ""
			+ "DELETE FROM xtempinfo" + 
			"      WHERE FUserId = ?";
	private static final String SQL_UPDATE = ""
			+ "UPDATE xtempinfo" + 
			"   SET [FUserName] = ?" + 
			"      ,[Faddtime] = ?" + 
			"      ,[Flastupdate] = ?" + 
			"      ,[Fdelete] = ?" + 
			" WHERE FUserId = ?";
	private static final String SQL_GET = ""
			+ "SELECT [FUserId]" + 
			"      ,[FUserName]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtempinfo]" + 
			"  WHERE [FUserId] = ?";
	private static final String SQL_GETLIST = ""
			+ "SELECT [FUserId]" + 
			"      ,[FUserName]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtempinfo]";
	private static final String SQL_GETLIST_DELETED = ""
			+ "SELECT [FUserId]" + 
			"      ,[FUserName]" + 
			"      ,[Faddtime]" + 
			"      ,[Flastupdate]" + 
			"      ,[Fdelete]" + 
			"  FROM [xtempinfo]" + 
			"  WHERE [Fdelete] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(EmpInfo dao) {
		jdbcTemplate.update(SQL_ADD, new Object[] {
				dao.getUserId(),
				dao.getUserName(),
				dao.getAddTime(),
				dao.getLastUpdate(),
				dao.isDeleted()?1:0
		});
	}

	@Override
	public void del(EmpInfo dao) {
		jdbcTemplate.update(SQL_DEL, new Object[] {dao.getUserId()});
	}

	@Override
	public void update(EmpInfo dao) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] {
				dao.getUserName(),
				dao.getAddTime(),
				dao.getLastUpdate(),
				dao.isDeleted()?1:0,
				dao.getUserId()
				});
	}

	@Override
	public EmpInfo get(Integer id) {
		List<EmpInfo> list = jdbcTemplate.query(SQL_GET, new Object[] {id}, new EmpInfoRowMapper());
		if(list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public List<EmpInfo> getList() {
		return jdbcTemplate.query(SQL_GETLIST, new EmpInfoRowMapper());
	}

	@Override
	public List<EmpInfo> getList(boolean deleted) {
		return jdbcTemplate.query(SQL_GETLIST_DELETED, new Object[] {deleted?1:0}, new EmpInfoRowMapper());
	}

	public Integer getNextId() {
		List<Integer> list = new ArrayList<Integer>();
		for(EmpInfo emp : getList()) {
			list.add(emp.getUserId());
		}
		int i = 1;
		while(true) {
			if(list.contains(i)) {
				i = i+1;
			}
			else {
				return i;
			}
		}
	}
}
