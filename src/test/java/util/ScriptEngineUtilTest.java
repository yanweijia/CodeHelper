package util;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptException;


public class ScriptEngineUtilTest {

    @Test
    public void exec() throws ScriptException {
        String script = "function add(a,b){return (a+b).toString() }";
        script += "var result = add(1,2)";

        Assert.assertEquals(String.valueOf(3), ScriptEngineUtil.exec(script, "result"));
    }
}
