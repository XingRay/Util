package com.xingray.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {

    public static Map<String, String> loadConfig(String relativePath) {
        return null;
    }

    public static String saveConfig(String path, Map<String, String> config) {
        return null;
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static void saveProperties(String path, Properties properties) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String path, String key) {
        return loadProperties(path).getProperty(key);
    }

    public static void putProperty(String path, String key, String value) {
        Properties properties = loadProperties(path);
        properties.put(key, value);
        saveProperties(path, properties);
    }

    public static <T> T readFromProperties(String path, Class<T> cls) {
        Properties properties = loadProperties(path);
        HashMap<String, Object> map = new HashMap<>(properties.size());
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            map.put(key.toString(), value);
        }
        return ObjectUtil.mapToObject(map, cls);
    }

    public static <T> void writeToProperties(String path, T t) {
        Map<String, Object> map = ObjectUtil.objectToMap(t);
        if (map.isEmpty()) {
            return;
        }

        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            properties.put(key, value == null ? "" : value.toString());
        }
        saveProperties(path, properties);
    }
}
