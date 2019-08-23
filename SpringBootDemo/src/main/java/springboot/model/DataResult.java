package springboot.model;

import java.io.Serializable;

/**
 *
 * @ClassName:DataResult
 * @Description:返回客户端的响应结果
 * @author:
 * @date:2019年7月10日
 */
public class DataResult implements Serializable{
    //返回的提示消息
    private String message;
    //返回的数据
    private Object data;

    private Integer curPage;

    private Integer pageSize;

    private Integer total;
    /**
     * 200：代表成功  ，404： 代表查询结果为空   500：系统报错
     */
    private Integer status;

	public DataResult() {
	}



	public String getMessage() {
        return message;
    }
    public Object getData() {
        return data;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Object data) {
        this.data = data;
    }
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public DataResult(String message, Object data, Integer curPage,
                      Integer pageSize, Integer status, Integer total) {
		this.message = message;
		this.data = data;
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.status = status;
		this.total = total;
	}

	public DataResult(Integer status, String message, Object data) {
		this.message = message;
		this.data = data;
		this.status = status;
	}

}
