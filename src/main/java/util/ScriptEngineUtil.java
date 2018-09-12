package util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineUtil {

    private static ScriptEngine jsEngine;
    private static String engineName = "javascript";

    /**
     * 获取JS Engine
     *
     * @return
     */
    public static ScriptEngine getJavaScriptEngine() {
        if (jsEngine == null) {
            ScriptEngineManager manager = new ScriptEngineManager();
            jsEngine = manager.getEngineByName(engineName);
        }
        return jsEngine;
    }

    /**
     * 执行指定 js 脚本并获取返回结果
     *
     * @param script
     * @param resultVar 执行结果的变量名称
     * @return
     */
    public static String exec(String script, String resultVar) throws ScriptException {
        getJavaScriptEngine();
        jsEngine.eval(script);
        return String.valueOf(jsEngine.get(resultVar));
    }
}
