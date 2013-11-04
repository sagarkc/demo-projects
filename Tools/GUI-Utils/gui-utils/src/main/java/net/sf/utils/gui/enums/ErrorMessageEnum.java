/**
 * 
 */
package net.sf.utils.gui.enums;

/**
 * @author sabuj.das
 *
 */
public enum ErrorMessageEnum {

	UTILITY_ERROR("GS_ERR_1001", "Cannot Complete the Operation."),
	INVALID_FILE_NAME("GS_ERR_1002", "Invalid File Name."),
	FILE_NOT_FOUND("GS_ERR_1003", "Cannot Find the path specified."),
	CANNOT_WRITE_TO_FILE("GS_ERR_1004", "Cannot write to file.");
	
	private String errorCode;
	private String errorMessage;
	
	private ErrorMessageEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getErrorMessage(String code) {
		return "";
	}
}
