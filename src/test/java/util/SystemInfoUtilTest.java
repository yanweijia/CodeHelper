package util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SystemInfoUtilTest {

    @Test
    public void showSystemInfos() {
        String propertiesInfo = JSON.toJSONString(System.getProperties(), true);
        System.out.println(propertiesInfo);
        Assert.assertFalse(StringUtils.isEmpty(propertiesInfo));
    }

    @Test
    public void showDiskInfo() {
        String diskInfo = SystemInfoUtil.getDiskInfo();
        System.out.println(diskInfo);
        Assert.assertFalse(StringUtils.isEmpty(diskInfo));
    }
}
