package com.diligrp.assistant.data.controller;

import com.diligrp.assistant.data.domain.DataDistrictDTO;
import com.diligrp.assistant.data.domain.DistrictPageQuery;
import com.diligrp.assistant.data.domain.ListDataDistrict;
import com.diligrp.assistant.data.model.DataDistrict;
import com.diligrp.assistant.data.service.DataDistrictService;
import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/district")
public class DataDistrictController {

    @Resource
    private DataDistrictService dataDistrictService;

    @RequestMapping(value = "/findById.do")
    public Message<DataDistrictDTO> findById(@RequestParam("id") Long id) {
        DataDistrict district = dataDistrictService.findDataDistrictById(id);
        return Message.success(DataDistrictDTO.of(district.getId(), district.getParentId(),
            district.getName(), district.getLevel(), district.getFullName(), district.getPath()));
    }

    @RequestMapping(value = "/findParentById.do")
    public Message<DataDistrictDTO> findParentById(@RequestParam("id") Long id) {
        DataDistrict district = dataDistrictService.findParentDistrictById(id);
        return Message.success(DataDistrictDTO.of(district.getId(), district.getParentId(),
            district.getName(), district.getLevel(), district.getFullName(), district.getPath()));
    }

    @RequestMapping(value = "/listParentsById.do")
    public Message<List<DataDistrictDTO>> listParentsById(@RequestParam("id") Long id) {
        List<DataDistrictDTO> districts = dataDistrictService.listParentsById(id).stream().map(d ->
            DataDistrictDTO.of(d.getId(), d.getParentId(), d.getName(), d.getLevel(), d.getFullName(), d.getPath()))
            .collect(Collectors.toList());
        return Message.success(districts);
    }

    @RequestMapping(value = "/listChildrenById.do")
    public Message<List<DataDistrictDTO>> findChildrenById(@RequestBody ListDataDistrict request) {
        AssertUtils.notNull(request.getId(), "id missed");
        AssertUtils.notNull(request.getPageNo(), "pageNo missed");
        AssertUtils.notNull(request.getPageSize(), "pageSize missed");

        DistrictPageQuery query = new DistrictPageQuery();
        query.setId(request.getId());
        query.from(request.getPageNo(), request.getPageSize());

        List<DataDistrictDTO> districts = dataDistrictService.listChildrenById(query).stream().map(d ->
            DataDistrictDTO.of(d.getId(), d.getParentId(), d.getName(), d.getLevel(), d.getFullName(), d.getPath()))
            .collect(Collectors.toList());
        return Message.success(districts);
    }

    @RequestMapping(value = "/listByLevel.do")
    public Message<List<DataDistrictDTO>> findByLevel(@RequestBody ListDataDistrict request) {
        AssertUtils.notNull(request.getLevel(), "level missed");
        AssertUtils.notNull(request.getPageNo(), "pageNo missed");
        AssertUtils.notNull(request.getPageSize(), "pageSize missed");

        DistrictPageQuery query = new DistrictPageQuery();
        query.setLevel(request.getLevel());
        query.from(request.getPageNo(), request.getPageSize());

        List<DataDistrictDTO> districts = dataDistrictService.listDataDistrictsByLevel(query).stream().map(d ->
            DataDistrictDTO.of(d.getId(), d.getParentId(), d.getName(), d.getLevel(), d.getFullName(), d.getPath()))
            .collect(Collectors.toList());
        return Message.success(districts);
    }
}
