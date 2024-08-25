package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;

import javax.swing.Spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
public class MessageSourceTest {

	@Autowired
	MessageSource ms;

	@Test
	void helloMessage() {
		String result = ms.getMessage("hello", null, null);
		org.assertj.core.api.Assertions.assertThat(result).isEqualTo("안녕");
	}

	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMethodException.class);
	}

	@Test
	void notFoundMessageCodeDefaultMessage() {
		String result = ms.getMessage("no_code", null, "기본 메시지", null);
		assertThat(result).isEqualTo("기본 메시지");
	}

	@Test
	void argumentMessage() {
		String message = ms.getMessage("hello.name", new Object[] { "Spring" }, null);
		assertThat(message).isEqualTo("안녕 Spring");
	}

	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
	}

	@Test
	void enLang() {
		assertThat(ms.getMessage("hlleo", null, Locale.ENGLISH)).isEqualTo("hello");
	}
}
