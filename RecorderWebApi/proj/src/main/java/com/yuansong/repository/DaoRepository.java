package com.yuansong.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DaoRepository<T,KeyType> {
	
	public void add(T dao);
	
	public void del(T dao);
	
	public void update(T dao);
	
	public T get(KeyType id);
	
	public List<T> getList();
	
	public List<T> getList(boolean deleted);
	
}
