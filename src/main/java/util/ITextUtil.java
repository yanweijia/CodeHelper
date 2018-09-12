package util;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * IText 工具类
 * @Author: weijia
 * function: Html 转 PDF
 */
public class ITextUtil {

    //TODO: 中文字体的支持
    private static final String FONT_NAME = "./FontSongType.ttf";

    /**
     * 导出PDF文件
     *
     * @param htmlContent html 文本, UTF-8格式
     * @param pdfFile     用于保存 pdf 文件
     * @return 导出结果
     */
    public static boolean exportPdf(String htmlContent, File pdfFile) {
        htmlContent = HtmlUtil.formatHtml4(htmlContent);
        FileOutputStream fos = null;
        Document document = new Document();
        try {
            fos = new FileOutputStream(pdfFile);

            PdfWriter writer = PdfWriter.getInstance(document, fos);

            document.open();
            InputStream htmlInput = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
            // 使用我们的字体提供器，并将其设置为unicode字体样式
            MyFontsProvider fontProvider = new MyFontsProvider();
            fontProvider.addFontSubstitute("lowagie", "garamond");
            fontProvider.setUseUnicode(true);
            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlInput, null, Charset.forName(StandardCharsets.UTF_8.name()),
                    fontProvider);
            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            return false;
        } finally {
            IOUtils.closeQuietly(fos);
        }
        return true;
    }

    /**
     * 重写 字符设置方法，解决中文乱码问题
     */
    public static class MyFontsProvider extends XMLWorkerFontProvider {

        public MyFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
            String fntname = fontname;
            if (fntname == null) {
                fntname = FONT_NAME;
            }
            if (size == 0) {
                size = 4;
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

}