package com.okta.developer.jugtours;

import com.okta.developer.jugtours.util.AddressProcessUtil;
import org.junit.Test;

public class AddressProcessUtilTest {
    @Test
    public void testParserAddress() {
        AddressProcessUtil.parseAddress("台北市信義區市府路1號");
    }
}
