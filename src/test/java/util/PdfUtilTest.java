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
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                int finalI = i;
                FutureTask<Boolean> task = new FutureTask<Boolean>(() -> {
                    BufferedImage image = renderer.renderImage(finalI, 1f, ImageType.RGB);
                    log.info("image loaded ");
                    ImageIO.write(image, "PNG", new File(String.format("./temp/page/%s.png", finalI)));
                    log.info("image writed");
                    return true;
                });

            }
            doc.close();
            log.info("convert2png costTime:{}ms", System.currentTimeMillis() - startTime);

        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
    }
}
