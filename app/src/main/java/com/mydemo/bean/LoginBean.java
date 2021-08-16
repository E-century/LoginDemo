package com.mydemo.bean;

/**
 * @ClassName LoginBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021-08-16 10:32
 */

public class LoginBean {

	private String code;
	private Object message;
	private DataBean data;


	public static class DataBean {
		private UserBean user;
		private String token;


		public static class UserBean {
			private String userNo;
			private Object password;
			private Object lockFlag;
			private Integer failureNum;
			private Object errLoginDate;
			private String address;
			private String idNumber;
			private String eMail;
			private String phoneNumber;
			private Object successLoginDate;
			private Object token;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}
}
