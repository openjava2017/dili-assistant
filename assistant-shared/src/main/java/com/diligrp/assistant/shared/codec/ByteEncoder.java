package com.diligrp.assistant.shared.codec;

import java.io.IOException;

public interface ByteEncoder<T> {
    byte[] encode(T payload) throws IOException;
}
