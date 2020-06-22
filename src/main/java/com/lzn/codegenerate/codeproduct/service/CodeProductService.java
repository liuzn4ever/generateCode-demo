package com.lzn.codegenerate.codeproduct.service;

import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import com.lzn.codegenerate.codeproduct.dao.CodeProductDao;
import com.lzn.codegenerate.codeproduct.entity.GridDataModel;
import com.lzn.codegenerate.codeproduct.entity.OmuiPage;
import com.lzn.codegenerate.codeproduct.service.CodeProductService;
import com.elog.fs.admin.service.BaseService;
import com.elog.fs.admin.vo.GridDataModel;
import com.elog.fs.admin.vo.OmuiPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Map;
import java.util.List;
/**
 * <p>
 * 代码生成 服务类
 * </p>
 *
 * @author DDJIT
 * @since 2020-06-19
 */
@Service
@Transactional
public class CodeProductService extends BaseService<CodeProduct> {

    @Resource
    private CodeProductDao codeProductDao;


    public GridDataModel<CodeProduct> findByPage(Map<String, Object> searchParams, OmuiPage page){
        GridDataModel<CodeProduct> gm = new GridDataModel<CodeProduct>();
        PageRequest pageRequest = buildPageRequest(page);
        Specification<CodeProduct> spec = buildSpecification(searchParams);
        Page<CodeProduct> resultPage = codeProductDao.findAll(spec, pageRequest);
        gm.setTotal(resultPage.getTotalElements());
        gm.setRows(resultPage.getContent());
        return gm;
    }


    //cvs导出用
    public long count(Map<String, Object> searchParams){
        Specification<CodeProduct> spec = buildSpecification(searchParams);
        return codeProductDao.count(spec);
        }

    //cvs导出用
    public List<CodeProduct> queryList(Map<String, Object> searchParams, int start, int size){
        PageRequest pageRequest = buildPageRequest(start,size);
        Specification<CodeProduct> spec = buildSpecification(searchParams);
        Page<CodeProduct> resultPage = codeProductDao.findAll(spec, pageRequest);
        return resultPage.getContent();
    }


}

