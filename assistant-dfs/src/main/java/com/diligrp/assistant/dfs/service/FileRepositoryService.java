package com.diligrp.assistant.dfs.service;

import com.diligrp.assistant.dfs.domain.DfsAccessToken;
import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.model.FileRepository;

public interface FileRepositoryService {
    String createFileRepository(DfsAccessToken accessToken, FileRepository repository);

    void deleteFileRepository(DfsAccessToken accessToken);

    String uploadFile(DfsAccessToken accessToken, DfsFile file);

    DfsFile downloadFile(DfsAccessToken accessToken, String fileId, String style);

    void deleteFile(DfsAccessToken accessToken, String fileId);
}
