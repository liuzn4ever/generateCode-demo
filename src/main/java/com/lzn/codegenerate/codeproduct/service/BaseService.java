package com.lzn.codegenerate.codeproduct.service;

import com.elog.fs.admin.dao.jpa.SysLogDao;
import com.elog.fs.admin.entity.FrozenBalanceLog;
import com.elog.fs.admin.entity.SysLog;
import com.elog.fs.admin.vo.OmuiPage;
import com.fengmi.ddj.common.enumeration.BisType;
import com.lzn.codegenerate.codeproduct.entity.OmuiPage;
import com.sun.jndi.toolkit.dir.SearchFilter;
import org.elogside.modules.persistence.DynamicSpecifications;
import org.elogside.modules.persistence.SearchFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

public class BaseService<T> {

	Class<T> persistentClass;

	@Resource
	protected FrozenBalanceLogService frozenBalanceLogService;
	@Resource
	protected SysLogDao sysLogDao;

	/**
	 * Hibernate分页查询----------创建分页请求.
	 */
	public PageRequest buildPageRequest(OmuiPage page) {

		Sort sort = null;
		if("desc".equals(page.getSortDir())){
			sort = new Sort(Direction.DESC, page.getSortBy());;
		}else if("asc".equals(page.getSortDir())){
			sort = new Sort(Direction.ASC, page.getSortBy());;
		}else{
			sort = new Sort(Direction.DESC, "id");
		}
		return new PageRequest(page.getStart()/ page.getLimit(), page.getLimit(), sort);
	}

	public PageRequest buildPageRequest(int start, int limit) {
		Sort sort = new Sort(Direction.DESC, "id");
		return new PageRequest(start/ limit, limit, sort);
	}
	
	/**
	 * 创建动态查询条件组合.
	 */
	@SuppressWarnings("unused")
	protected Specification<T> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), persistentClass);
		return spec;
	}
	
	protected String handerSortBy(String sortBy){
		StringBuffer sbr=new StringBuffer();
		int j=0;
		if(null!=sortBy && ""!=sortBy){
			int i=0;
			for(;i<sortBy.length();i++){
				if(sortBy.charAt(i)>='A' && sortBy.charAt(i)<='Z'){
					sbr.append(sortBy.substring(j, i).toLowerCase()).append("_");
					j=i;
				}
			}
			sbr.append(sortBy.substring(j,sortBy.length()).toLowerCase());		
		}else {
			return sortBy;
		}
		if(""!=sbr.toString()&& null !=sbr.toString()){
			return sbr.toString();
		}
		else
		{
			return sortBy;
		}
	}


}
