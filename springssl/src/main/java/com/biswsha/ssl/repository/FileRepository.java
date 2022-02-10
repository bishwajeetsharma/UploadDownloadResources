package com.biswsha.ssl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biswsha.ssl.model.FileDB;

@Repository
public interface FileRepository extends JpaRepository<FileDB, Integer>{

}
