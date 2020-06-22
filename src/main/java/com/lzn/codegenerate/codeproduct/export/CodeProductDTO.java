package com.elog.fs.admin.codeproduct.export;
import org.elogside.modules.utils.DateUtils;
import com.elog.fs.admin.codeproduct.entity.CodeProduct;
import com.fengmi.ddj.common.export.ExportRowCreator;
import java.util.Date;


public class CodeProductDTO extends CodeProduct implements ExportRowCreator {

	@Override
	public String[] createRow() {
		return new String[]{DateUtils.formatDate(getCreateDate()),DateUtils.formatDate(getModifyDate()),getName().toString(),getMobile().toString(),getBulidDate().toString(),getCompanyName().toString(),getShopGoods().toString(),getOtherInfo().toString(),getStatus().toString(),getOperator().toString()		};
	}
}
