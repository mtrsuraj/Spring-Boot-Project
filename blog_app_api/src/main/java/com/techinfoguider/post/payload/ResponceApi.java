package com.techinfoguider.post.payload;

public class ResponceApi {

		private String message;
		private boolean success;
		public ResponceApi() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public ResponceApi(String message, boolean success) {
			super();
			this.message = message;
			this.success = success;
		}


		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
}
