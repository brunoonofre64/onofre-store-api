package io.brunoonofre64;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegrationTestApplicationTests {

	@Test
	@DisplayName("should normally start the main class")
	void main() {
		IntegrationTestApplication.main(new String[]{});
	}

}
