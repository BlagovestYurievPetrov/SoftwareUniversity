package com.example.json.util;

public interface XmlParser {
    <T> T fromFile(String filePath, Class<T> tClass);
}
