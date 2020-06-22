
/**
 * <p>
 * 代码生成 dao 接口
 * </p>
 *
 * @author DDJIT
 * @since 2020-06-19
 */
package com.lzn.codegenerate.codeproduct.dao;
import com.lzn.codegenerate.codeproduct.entity.CodeProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CodeProductDao extends PagingAndSortingRepository<CodeProduct, Long>,JpaSpecificationExecutor<CodeProduct>{

}
