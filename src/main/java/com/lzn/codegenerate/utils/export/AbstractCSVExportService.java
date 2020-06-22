package com.lzn.codegenerate.utils.export;



import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


/**
 * 默认Csv文件导出方法
 *
 * @author zhangzhao zhangzhao2@sf-express.com
 *         createDate 2017/5/24 16:09
 * @version v1.0.0
 */
public abstract class AbstractCSVExportService<T> extends AbstractExportService<T> {

    CSVWriter csvWriter;

    @Override
    public void initWriteFile(OutputStream outputStream) {
        OutputStreamWriter osWriter = null;
        try {
            osWriter = new OutputStreamWriter(outputStream,"GBK");
        } catch (UnsupportedEncodingException e) {
            osWriter = new OutputStreamWriter(outputStream);
        }
        csvWriter = new CSVWriter(osWriter);
    }

    @Override
    public void downloadFinish(OutputStream outputStream) {
        try {
            csvWriter.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void writeLine(String[] line) {
        csvWriter.writeNext(line);
    }
}
