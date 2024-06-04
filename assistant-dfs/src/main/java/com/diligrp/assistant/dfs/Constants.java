package com.diligrp.assistant.dfs;

public final class Constants {
    public static final String HEADER_AUTHORIZATION = "Dfs-Authorization";

    public static final String FILE_METADATA_NAME = "name";

    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    public static final int MAX_POOL_SIZE = 200;

}
