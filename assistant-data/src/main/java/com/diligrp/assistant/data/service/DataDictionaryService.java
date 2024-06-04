package com.diligrp.assistant.data.service;

import com.diligrp.assistant.data.model.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictionaryService {
    /**
     * 新增数据字典配置
     *
     * @param dictionary - 数据字典
     */
    void insertDataDictionary(DataDictionary dictionary);

    /**
     * 根据编码(groupCode和Code)查询数据字典配置
     *
     * @param groupCode - 分组编码，必填
     * @param code - 参数编码，必填
     * @return DataDictionary - 查询结果大于一条记录将抛出异常
     */
    DataDictionary findDataDictionaryByCode(@Param("groupCode") String groupCode, @Param("code") String code);

    /**
     * 根据编码查询数据字典列表
     *
     * @param groupCode - 分组编码，非必填
     * @return List<DataDictionary> - 数据字典列表
     */
    List<DataDictionary> findDataDictionaries(@Param("groupCode") String groupCode);

    /**
     * 根据编码(groupCode和Code)修改数据字典配置
     *
     * @param dictionary - 数据字典
     * @return int - 更新条数
     */
    void updateDataDictionary(DataDictionary dictionary);
}
