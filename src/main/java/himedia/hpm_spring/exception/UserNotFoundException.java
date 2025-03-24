package himedia.hpm_spring.exception;


//UserNotFoundException 클래스에서 예외 처리를 하는데에도 불구하고 전역 예외 처리에서 또 다루는 이유 (던지기)
//예외가 발생하면 throw new UserNotFoundException("메시지")와 같이 코드에서 이 예외를 던진다.
//	예외를 직접 던지기 위한 역할로 "예외가 발생한 이유"를 명확히 하기 위한 목적

public class UserNotFoundException extends RuntimeException {

	// 생성자
	public UserNotFoundException(String message) {
		super(message); // 부모 클래스인 RuntimeException에 메세지 전달
	}
}
