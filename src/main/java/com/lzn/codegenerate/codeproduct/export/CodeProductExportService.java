package com.lzn.codegenerate.codeproduct.export;


import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import com.lzn.codegenerate.codeproduct.service.CodeProductService;
import com.lzn.codegenerate.utils.ParameterUtil;
import com.lzn.codegenerate.utils.export.AbstractCSVExportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class CodeProductExportService extends AbstractCSVExportService<CodeProductDTO> {

    @Resource
    private CodeProductService codeProductService;

    Map<String, Object> searchMap;

    @Override
    public void prepareExport(Map<String, Object> parameterMap) {
        searchMap = ParameterUtil.getParametersStartingWith(parameterMap,"filter_");
        Iterator<String> iterator = searchMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String) searchMap.get(key);
            if (StringUtils.isEmpty(value)) {
                iterator.remove();
                continue;
            }
        }
    }

    @Override
    public int queryTotal() {
        int total = (int)codeProductService.count(searchMap);
        return total;
    }

    @Override
    public List<CodeProductDTO> queryList(int start, int size) {
        List<CodeProductDTO> resultList = new ArrayList<>();
        List<CodeProduct> list = codeProductService.queryList(searchMap,start,size);
        for (CodeProduct cp:list) {
                CodeProductDTO codeProductDTO = new CodeProductDTO();
            BeanUtils.copyProperties(cp,codeProductDTO,new String[]{});
            resultList.add(codeProductDTO);
        }
        return resultList;
    }

    @Override
    public String[] getTableHead() {
        String[] head = new String[]{
                "创建时间","修改时间",
    "姓名",    "手机号",    "生成时间",    "公司名称",    "主营商品",    "备注信息",    "状态",    "处理人"



        };
        return head;
    }

    @Override
    public String getFileName() {
        return "代码生成";
    }

    @Override
    public Class<CodeProductDTO> getClazz() {
        return CodeProductDTO.class;
    }
}
