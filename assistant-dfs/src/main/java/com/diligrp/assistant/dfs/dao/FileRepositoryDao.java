package com.diligrp.assistant.dfs.dao;

import com.diligrp.assistant.dfs.model.FileRepository;
import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("fileRepositoryDao")
public interface FileRepositoryDao extends MybatisMapperSupport {
    void insertFileRepository(FileRepository repository);

    Optional<FileRepository> findFileRepositoryById(String repositoryId);

    int deleteFileRepository(String repositoryId);
}
