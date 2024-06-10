package com.diligrp.assistant.data.service.impl;

import com.diligrp.assistant.data.dao.DataDistrictDao;
import com.diligrp.assistant.data.domain.DistrictPageQuery;
import com.diligrp.assistant.data.exception.DataAccessException;
import com.diligrp.assistant.data.model.DataDistrict;
import com.diligrp.assistant.data.service.DataDistrictService;
import com.diligrp.assistant.shared.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("dataDistrictService")
public class DataDistrictServiceImpl implements DataDistrictService {

    @Resource
    private DataDistrictDao dataDistrictDao;

    @Override
    public DataDistrict findDataDistrictById(Long id) {
        return dataDistrictDao.findDataDistrictById(id).orElseThrow(() ->
            new DataAccessException(ErrorCode.OBJECT_NOT_FOUND, "区域不存在"));
    }

    @Override
    public DataDistrict findParentDistrictById(Long id) {
        return dataDistrictDao.findParentDistrictById(id).orElseThrow(() ->
            new DataAccessException(ErrorCode.OBJECT_NOT_FOUND, "区域不存在"));
    }

    @Override
    public List<DataDistrict> findParentDistrictsById(Long id) {
        DataDistrict self = findDataDistrictById(id);
        String path = self.getPath();

        if (Objects.nonNull(path)) {
            List<Long> ids = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(path, ",");

            while (tokenizer.hasMoreTokens()) {
                ids.add(Long.parseLong(tokenizer.nextToken()));
            }
            if (ids.isEmpty()) {
                ids.add(id);
            }

            return dataDistrictDao.findDataDistrictsByIds(ids);
        } else {
            return Collections.singletonList(self);
        }
    }

    @Override
    public List<DataDistrict> findChildDistrictsById(DistrictPageQuery query) {
        return dataDistrictDao.findChildDistrictsById(query);
    }

    @Override
    public List<DataDistrict> findDataDistrictsByLevel(DistrictPageQuery query) {
        return dataDistrictDao.findDataDistrictsByLevel(query);
    }
}
