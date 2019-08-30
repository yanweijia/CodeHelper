package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


@Slf4j
public class PdfUtilTest {

    @Test
    public void pdf2png() {
        try {
            long startTime = System.currentTimeMillis();
            PDDocument doc = PDDocument.load(new File("./temp/contract.pdf"));
            long costTime = System.currentTimeMillis() - startTime;
            log.info("load costTime:{}ms", costTime);
            startTime = System.currentTimeMillis();
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImage(1, 1f, ImageType.RGB);
            log.info("image loaded ");
            ImageIO.write(image, "PNG", new File(String.format("./temp/page/%s.png", 0)));
            log.info("image writed");
            doc.close();
            log.info("convert2png costTime:{}ms", System.currentTimeMillis() - startTime);

        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
    }
}
