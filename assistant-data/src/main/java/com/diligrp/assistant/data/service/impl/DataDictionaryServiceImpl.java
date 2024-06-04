package com.diligrp.assistant.data.service.impl;

import com.diligrp.assistant.data.dao.DataDictionaryDao;
import com.diligrp.assistant.data.exception.DataAccessException;
import com.diligrp.assistant.data.model.DataDictionary;
import com.diligrp.assistant.data.service.DataDictionaryService;
import com.diligrp.assistant.shared.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryDao dataDictionaryDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertDataDictionary(DataDictionary dictionary) {
        dataDictionaryDao.insertDataDictionary(dictionary);
    }

    @Override
    public DataDictionary findDataDictionaryByCode(String groupCode, String code) {
        //TODO: Use redis or local cache?
        Optional<DataDictionary> optional = dataDictionaryDao.findDataDictionaryByCode(groupCode, code);
        return optional.orElseThrow(() -> new DataAccessException(ErrorCode.OBJECT_NOT_FOUND, "数据字典不存在"));
    }

    @Override
    public List<DataDictionary> findDataDictionaries(String groupCode) {
        return dataDictionaryDao.findDataDictionaries(groupCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDataDictionary(DataDictionary dictionary) {
        int result = dataDictionaryDao.updateDataDictionary(dictionary);
        if (result == 0) {
            throw new DataAccessException(ErrorCode.OBJECT_NOT_FOUND, "数据字典不存在");
        }
    }
}
