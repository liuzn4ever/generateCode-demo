package com.lzn.codegenerate.codeproduct.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elog.fs.admin.entity.IdEntity;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import com.fengmi.ddj.common.entity.BaseEnttiyListener;
/**
 * <p>
 * 代码生成
 * </p>
 *
 * @author DDJIT
 * @since 2020-06-19
 */

@Entity
@Table(name = "test_code_product")
@EntityListeners(BaseEnttiyListener.class)
public class CodeProduct extends IdEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
	private String name;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 生成时间
     */
	@TableField("bulid_date")
	private Date bulidDate;
    /**
     * 公司名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 主营商品
     */
	@TableField("shop_goods")
	private String shopGoods;
    /**
     * 备注信息
     */
	@TableField("other_info")
	private String otherInfo;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 处理人
     */
	private String operator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBulidDate() {
		return bulidDate;
	}

	public void setBulidDate(Date bulidDate) {
		this.bulidDate = bulidDate;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopGoods() {
		return shopGoods;
	}

	public void setShopGoods(String shopGoods) {
		this.shopGoods = shopGoods;
	}
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	@Override
	public String toString() {
		return "CodeProduct{" +
			", name=" + name +
			", mobile=" + mobile +
			", bulidDate=" + bulidDate +
			", companyName=" + companyName +
			", shopGoods=" + shopGoods +
			", otherInfo=" + otherInfo +
			", status=" + status +
			", operator=" + operator +
			"}";
	}
}
