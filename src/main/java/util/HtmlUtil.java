package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

public class HtmlUtil {


    /**
     *
     * @param html 待格式化的文本
     * @return 支持自关闭标签的 xhtml 风格字串
     * @throws Exception
     */
    public static String formatHtml(String html){
        Document doc = Jsoup.parse(html);
        //设置解析 html 支持 self-closing 的标签,不加如下设置输出的部分标签如<link>没有关闭标签
        //参考 https://github.com/jhy/jsoup/issues/511
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().prettyPrint(true);
        return doc.toString();
    }

}
