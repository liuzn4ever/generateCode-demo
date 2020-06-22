
package com.lzn.codegenerate.codeproduct.action;

import com.elog.fs.admin.action.BaseController;
import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import com.lzn.codegenerate.codeproduct.service.CodeProductService;

import com.elog.fs.admin.codeproduct.export.CodeProductExportService;
import com.elog.fs.admin.vo.GridDataModel;
import com.elog.fs.admin.vo.OmuiPage;
import com.elog.fs.admin.vo.Resp;
import com.fengmi.ddj.common.util.JsonUtil;
import com.lzn.codegenerate.utils.Exceptions;
import com.lzn.codegenerate.utils.export.ExportMethod;
import org.apache.commons.beanutils.ConvertUtils;
import org.elogside.modules.mapper.JsonMapper;
import org.elogside.modules.utils.Exceptions;
import org.elogside.modules.utils.MyStringUtils;
import org.elogside.modules.utils.web.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fengmi.ddj.common.export.ExportMethod;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成  前端控制器
 * </p>
 *
 * @author DDJIT
 * @since 2020-06-19
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
    public String searchList(ServletRequest request,@ModelAttribute("page")  OmuiPage page){
        try {
            Map<String,Object> searchParam =	 ServletUtils.getParametersStartingWith(request, "filter_");
            GridDataModel<CodeProduct> gd =codeProductService.findByPage(searchParam, page);
            return JsonMapper.nonDefaultMapper().toJson(gd);
        } catch (Exception e) {
            logger.error(Exceptions.getStackTraceAsString(e));
            return JsonMapper.nonDefaultMapper().toJson(new Resp("false", e.getMessage()));
        }
    }


}
