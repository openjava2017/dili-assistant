package com.diligrp.assistant.data.service;

import com.diligrp.assistant.data.domain.DistrictPageQuery;
import com.diligrp.assistant.data.model.DataDistrict;

import java.util.List;

public interface DataDistrictService {
    /**
     * 根据ID查询区域
     *
     * @param id - 区域ID
     * @return 区域
     */
    DataDistrict findDataDistrictById(Long id);

    /**
     * 根据ID查询父级区域
     *
     * @param id - 区域ID
     * @return 父级区域
     */
    DataDistrict findParentDistrictById(Long id);

    /**
     * 根据ID查询所有的祖先区域，查询结果包含自身且按照等级降序排序
     *
     * @param id - 区域ID
     * @return 祖先区域
     */
    List<DataDistrict> listParentsById(Long id);

    /**
     * 根据父区域ID分页查询子区域
     *
     * @param query 查询条件
     * @return 子区域
     */
    List<DataDistrict> listChildrenById(DistrictPageQuery query);

    /**
     * 根据区域级别分页查询区域
     *
     * @param query 查询条件
     * @return 区域
     */
    List<DataDistrict> listDataDistrictsByLevel(DistrictPageQuery query);
}
