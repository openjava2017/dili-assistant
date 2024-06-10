package com.diligrp.assistant.data.dao;

import com.diligrp.assistant.data.domain.DistrictPageQuery;
import com.diligrp.assistant.data.model.DataDistrict;
import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("dataDistrictDao")
public interface DataDistrictDao extends MybatisMapperSupport {
    Optional<DataDistrict> findDataDistrictById(Long id);

    Optional<DataDistrict> findParentDistrictById(Long id);

    List<DataDistrict> findDataDistrictsByIds(List<Long> ids);

    List<DataDistrict> findChildDistrictsById(DistrictPageQuery query);

    List<DataDistrict> findDataDistrictsByLevel(DistrictPageQuery query);
}
