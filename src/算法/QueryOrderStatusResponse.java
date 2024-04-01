package 算法; /**
 * 
 */

/**
 *@ClassName:QueryOrderStatus.java
 *@Description:查询订单返回
 *@author zhangxingyun
 *@Date:2017年5月16日
 */
public class QueryOrderStatusResponse {

	//0：查询处理成功.其他失败
	private int code;

	//状态：-30:已作废、-31:审核不通过、-32:放款不通过；-20:已坏账；-11:已逾期；0:待审核；1:审核通过；2:签约成功；10:待配资；
	// 11:待募资；20:待放款；21:已放款；22:放款中；30:部分还款；31:已还款
	private int status;

	//放款成功/失败日期 空表示未知
	private String loanTime;

	//错误信息，不是必定会返回
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static void main(String[] args) {
		QueryOrderStatusResponse response = new QueryOrderStatusResponse();
		System.out.println("算法.test");
	}
}
