package com.lzn.codegenerate.utils.export;

/**
 * 将实体类 转换 成表格一行的接口
 *
 * @author zhangzhao zhangzhao2@sf-express.com
 *         createDate 2017/5/24 16:09
 * @version v1.0.0
 */
public interface ExportRowCreator {

    String[] createRow();
}
