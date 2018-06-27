package com.example.ec_201804d.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author kakiki
 *
 */
public class PagingForm {
	
	/**ページングの起点となる数値*/
	Integer paging;
	
	public Integer getPaging() {
		return paging;
	}
	public void setPaging(Integer paging) {
		this.paging = paging;
	}
}
