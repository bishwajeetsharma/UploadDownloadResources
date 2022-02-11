package com.biswsha.ssl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biswsha.ssl.model.FileDB;
import com.biswsha.ssl.model.FileInfo;

@Repository
public interface FileRepository extends JpaRepository<FileDB, Integer>{

	@Query(value="select new com.biswsha.ssl.model.FileInfo(f.id,f.name) from FileDB f")
	public List<FileInfo> getFileInfo();
}
