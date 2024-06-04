package com.diligrp.assistant.uid.service;

/**
 * SequenceKey基础类
 */
public interface KeyGenerator {
    /**
     * 获取下一个ID
     *
     * @return 下一个ID
     */
    String nextId();
}
