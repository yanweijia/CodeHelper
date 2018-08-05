package util;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class ScriptEngineUtilTest {

    @Test
    public void exec() throws ScriptException {
        String script = "function add(a,b){return (a+b).toString() }";
        script += "var result = add(1,2)";

        Assert.assertEquals(String.valueOf(3), ScriptEngineUtil.exec(script, "result"));
    }

    @Test
    public void testScriptEngineCostTimes() throws Exception {

        int loopCount = 1000;

        Thread.sleep(2000); //睡眠 2s 防止因为 build 时耗时和 cpu 对测试结果的影响
        long startTime = System.currentTimeMillis();
        ScriptEngine jsEngine = null;
        for (int i = 0; i < loopCount; i++) {
            if (jsEngine == null) {
                ScriptEngineManager manager = new ScriptEngineManager();
                jsEngine = manager.getEngineByName("javascript");
            } else {
                jsEngine = jsEngine;
            }
            jsEngine.eval("var a=1;");
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println(costTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            ScriptEngineManager manager = new ScriptEngineManager();
            jsEngine = manager.getEngineByName("javascript");
            jsEngine.eval("var a=1;");
        }

        costTime = System.currentTimeMillis() - startTime;
        System.out.println(costTime);

    }


}
