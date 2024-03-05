package constants;

public enum HttpsStatus {
	
	OK_200(200);

	private final int code;
	HttpsStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
