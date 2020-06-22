package com.lzn.codegenerate.codeproduct.export;

import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import com.lzn.codegenerate.utils.DateUtils;
import com.lzn.codegenerate.utils.export.ExportRowCreator;


public class CodeProductDTO extends CodeProduct implements ExportRowCreator {

	@Override
	public String[] createRow() {
		return new String[]{DateUtils.formatDate(getCreateDate()), DateUtils.formatDate(getModifyDate()),getName().toString(),getMobile().toString(),getBulidDate().toString(),getCompanyName().toString(),getShopGoods().toString(),getOtherInfo().toString(),getStatus().toString(),getOperator().toString()		};
	}
}
