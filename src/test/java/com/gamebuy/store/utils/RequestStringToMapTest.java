package com.gamebuy.store.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class RequestStringToMapTest {

    @Test
    public void requestStringToMapTest() {
        String url = "id=1&name=name";
        HashMap<String, String> expected = new HashMap<>();
        expected.put("id", "1");
        expected.put("name", "name");
        Assertions.assertEquals(expected, requestStringToMap(url));
    }
}
