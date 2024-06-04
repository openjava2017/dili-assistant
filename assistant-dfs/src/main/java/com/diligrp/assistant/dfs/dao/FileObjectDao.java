package com.diligrp.assistant.dfs.dao;

import com.diligrp.assistant.dfs.model.FileObject;
import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("fileObjectDao")
public interface FileObjectDao extends MybatisMapperSupport {
    void insertFileObject(FileObject file);

    int hitFileObject(@Param("fileId") String fileId, @Param("modifiedTime") LocalDateTime modifiedTime);

    int deleteFileObject(String fileId);
}
