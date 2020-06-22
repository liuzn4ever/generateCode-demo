
package com.lzn.codegenerate.codeproduct.action;


import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import com.lzn.codegenerate.codeproduct.entity.GridDataModel;
import com.lzn.codegenerate.codeproduct.entity.JsonMapper;
import com.lzn.codegenerate.codeproduct.entity.OmuiPage;
import com.lzn.codegenerate.codeproduct.export.CodeProductExportService;
import com.lzn.codegenerate.codeproduct.service.CodeProductService;
import com.lzn.codegenerate.utils.Exceptions;
import com.lzn.codegenerate.utils.ParameterUtil;
import com.lzn.codegenerate.utils.export.ExportMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * <p>
 * 代码生成  前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping(value="/admin/codeProduct")
public class CodeProductController{
    private static Logger logger = LoggerFactory.getLogger(CodeProductController.class);

    @Resource
    private CodeProductService codeProductService;



    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        return "/admin/codeproduct/list";
    }

    @RequestMapping(value = "searchList", method = RequestMethod.POST)
    @ResponseBody
    @ExportMethod(serviceClass = CodeProductExportService.class, memo = "明细导出")
    public String searchList(ServletRequest request,@ModelAttribute("page") OmuiPage page){
        try {
            Map<String,Object> searchParam =	 ParameterUtil.getParametersStartingWith(request, "filter_");
            GridDataModel<CodeProduct> gd =codeProductService.findByPage(searchParam, page);
            return  JsonMapper.nonDefaultMapper().toJson(gd);
        } catch (Exception e) {
            logger.error(Exceptions.getStackTraceAsString(e));
            return JsonMapper.nonDefaultMapper().toJson(new Resp("false", e.getMessage()));
        }
    }


}
