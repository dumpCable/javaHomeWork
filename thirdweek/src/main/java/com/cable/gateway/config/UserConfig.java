package com.cable.gateway.config;

import java.util.Arrays;
import java.util.List;

public class UserConfig {
    public static List<String> allowList;

    public static List<String> blockList;

    static {
        init();
    }

    public static void init() {
        allowList = Arrays.asList("Li", "Wang", "Yang");
        blockList = Arrays.asList("Zhao", "Qian");
    }

}
