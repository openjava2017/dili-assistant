package com.diligrp.assistant.product.controller;

import com.diligrp.assistant.product.domain.CategoryVO;
import com.diligrp.assistant.product.domain.CategoryPageQuery;
import com.diligrp.assistant.product.domain.ListCategory;
import com.diligrp.assistant.product.model.Category;
import com.diligrp.assistant.product.service.ProductCategoryService;
import com.diligrp.assistant.shared.domain.Message;
import com.diligrp.assistant.shared.util.AssertUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 对外接口控制器
 */
@RestController
@RequestMapping(value = "/api/category")
public class CategoryOpenApiController {

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/findById.do")
    public Message<CategoryVO> findById(@RequestParam("id") Long id) {
        Category category = productCategoryService.findCategoryById(id);
        return Message.success(CategoryVO.of(category.getId(), category.getParentId(),
            category.getName(), category.getAlias(), category.getLevel(), category.getIcon(), category.getPath()));
    }

    @RequestMapping(value = "/findParentById.do")
    public Message<CategoryVO> findParentById(@RequestParam("id") Long id) {
        Category category = productCategoryService.findParentCategoryById(id);
        return Message.success(CategoryVO.of(category.getId(), category.getParentId(),
            category.getName(), category.getAlias(), category.getLevel(), category.getIcon(), category.getPath()));
    }

    @RequestMapping(value = "/listParentsById.do")
    public Message<List<CategoryVO>> listParentsById(@RequestParam("id") Long id) {
        List<CategoryVO> categories = productCategoryService.listParentsById(id).stream().map(category ->
            CategoryVO.of(category.getId(), category.getParentId(), category.getName(), category.getAlias(),
            category.getLevel(), category.getIcon(), category.getPath())).collect(Collectors.toList());
        return Message.success(categories);
    }

    @RequestMapping(value = "/listChildrenById.do")
    public Message<List<CategoryVO>> findChildrenById(@RequestBody ListCategory request) {
        AssertUtils.notNull(request.getId(), "id missed");
        AssertUtils.notNull(request.getPageNo(), "pageNo missed");
        AssertUtils.notNull(request.getPageSize(), "pageSize missed");

        CategoryPageQuery query = new CategoryPageQuery();
        query.setId(request.getId());
        query.from(request.getPageNo(), request.getPageSize());

        List<CategoryVO> categories = productCategoryService.listChildrenById(query).stream().map(category ->
            CategoryVO.of(category.getId(), category.getParentId(), category.getName(), category.getAlias(),
            category.getLevel(), category.getIcon(), category.getPath())).collect(Collectors.toList());
        return Message.success(categories);
    }

    @RequestMapping(value = "/listByLevel.do")
    public Message<List<CategoryVO>> findByLevel(@RequestBody ListCategory request) {
        AssertUtils.notNull(request.getLevel(), "level missed");
        AssertUtils.notNull(request.getPageNo(), "pageNo missed");
        AssertUtils.notNull(request.getPageSize(), "pageSize missed");

        CategoryPageQuery query = new CategoryPageQuery();
        query.setLevel(request.getLevel());
        query.from(request.getPageNo(), request.getPageSize());

        List<CategoryVO> categories = productCategoryService.listCategoriesByLevel(query).stream().map(category ->
            CategoryVO.of(category.getId(), category.getParentId(), category.getName(), category.getAlias(),
            category.getLevel(), category.getIcon(), category.getPath())).collect(Collectors.toList());
        return Message.success(categories);
    }
}
