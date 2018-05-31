package com.max2.parser;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static  List<String> data = new ArrayList();
    static {
        data.add("Duck, Donald, (703)-742-0996, Golden, 99999");
        data.add("Donald Duck, Golden, 99999-1234, 703 955 0373");
        data.add("Donald, Duck, 99999, 646 111 0101, Golden");
        data.add("Donald Duck, 1 Disneyland, 99999, 876-543-2104, Golden");
    }

}
