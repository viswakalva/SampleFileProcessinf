package com.mmkalva.genesys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmkalva.genesys.entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long>{
	
	//native queries
	
	//Method queries
	
	@Override
	default List<FileData> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Named queries

}
