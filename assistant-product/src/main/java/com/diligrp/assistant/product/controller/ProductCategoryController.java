package com.diligrp.assistant.product.controller;

import com.diligrp.assistant.product.domain.CategoryDTO;
import com.diligrp.assistant.product.service.ProductCategoryService;
import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/create.do")
    public Message<?> create(@RequestBody CategoryDTO request) {
        AssertUtils.notNull(request.getParentId(), "parentId missed");
        AssertUtils.notEmpty(request.getName(), "name missed");

        Long id = productCategoryService.createCategory(request);
        return Message.success(id);
    }

    @RequestMapping(value = "/update.do")
    public Message<?> update(@RequestBody CategoryDTO request) {
        AssertUtils.notNull(request.getId(), "id missed");

        productCategoryService.updateCategory(request);
        return Message.success();
    }

    @RequestMapping(value = "/delete.do")
    public Message<?> delete(@RequestParam Long id) {
        productCategoryService.deleteCategory(id);
        return Message.success();
    }
}
