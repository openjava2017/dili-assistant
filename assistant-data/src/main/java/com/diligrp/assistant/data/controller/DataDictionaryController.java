package com.diligrp.assistant.data.controller;

import com.diligrp.assistant.data.domain.DataDictionaryDTO;
import com.diligrp.assistant.data.model.DataDictionary;
import com.diligrp.assistant.data.service.DataDictionaryService;
import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/dictionary")
public class DataDictionaryController {

    @Resource
    private DataDictionaryService dataDictionaryService;

    @RequestMapping(value = "/save.do")
    public Message save(@RequestBody DataDictionaryDTO request) {
        AssertUtils.notNull(request.getType(), "type missed");
        AssertUtils.notEmpty(request.getGroupCode(), "groupCode missed");
        AssertUtils.notEmpty(request.getCode(), "code missed");
        AssertUtils.notEmpty(request.getValue(), "value missed");

        LocalDateTime now = LocalDateTime.now();
        DataDictionary.Builder dictionary = DataDictionary.builder();
        dictionary.type(request.getType()).groupCode(request.getGroupCode()).code(request.getCode())
            .name(request.getName()).value(request.getValue()).description(request.getDescription())
            .createdTime(now).modifiedTime(now);
        dataDictionaryService.insertDataDictionary(dictionary.build());
        return Message.success();
    }

    @RequestMapping(value = "/findByCode.do")
    public Message<DataDictionaryDTO> findByCode(@RequestBody DataDictionaryDTO request) {
        AssertUtils.notEmpty(request.getGroupCode(), "groupCode missed");
        AssertUtils.notEmpty(request.getCode(), "code missed");

        DataDictionary dictionary = dataDictionaryService.findDataDictionaryByCode(request.getGroupCode(), request.getCode());
        return Message.success(DataDictionaryDTO.from(dictionary.getType(), dictionary.getGroupCode(),
            dictionary.getCode(), dictionary.getName(), dictionary.getValue(), dictionary.getDescription()));
    }

    @RequestMapping(value = "/findByGroupCode.do")
    public Message<List<DataDictionaryDTO>> findByGroupCode(@RequestBody DataDictionaryDTO request) {
        AssertUtils.notEmpty(request.getGroupCode(), "groupCode missed");

        List<DataDictionary> dictionaries = dataDictionaryService.findDataDictionaries(request.getGroupCode());
        List<DataDictionaryDTO> data = dictionaries.stream().map(dictionary -> DataDictionaryDTO.from(dictionary.getType(),
            dictionary.getGroupCode(), dictionary.getCode(), dictionary.getName(), dictionary.getValue(),
            dictionary.getDescription())).collect(Collectors.toList());

        return Message.success(data);
    }

    @RequestMapping(value = "/update.do")
    public Message update(@RequestBody DataDictionaryDTO request) {
        AssertUtils.notEmpty(request.getGroupCode(), "groupCode missed");
        AssertUtils.notEmpty(request.getCode(), "code missed");
        AssertUtils.notEmpty(request.getValue(), "value missed");

        DataDictionary.Builder dictionary = DataDictionary.builder().groupCode(request.getGroupCode())
            .code(request.getCode()).name(request.getName()).value(request.getValue())
            .description(request.getDescription()).modifiedTime(LocalDateTime.now());
        dataDictionaryService.updateDataDictionary(dictionary.build());

        return Message.success();
    }
}
