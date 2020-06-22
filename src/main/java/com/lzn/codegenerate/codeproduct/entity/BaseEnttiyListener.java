package com.lzn.codegenerate.codeproduct.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * 实体类监听器
 * @filename      : BaseEnttiyListener.java
 * @description   : 实体类监听器 统一修改数据的创建时间和修改时间
 */
public class BaseEnttiyListener {

	
	/**
	 * 在持久化实体之前调用
	 * @autor liuf
	 * @param entity
	 */
	@PrePersist
	public void onSave(IdEntity entity){
		Date createDate = new Date();
		entity.setCreateDate(createDate);
		entity.setModifyDate(createDate);
	}
	
	
	/**
	 * 在update实体之前调用
	 * @autor liuf
	 * @param entity
	 */
	@PreUpdate
	public void onUpdate(IdEntity entity){
		Date modeifyDate = new Date();
		entity.setModifyDate(modeifyDate);
	}
}
