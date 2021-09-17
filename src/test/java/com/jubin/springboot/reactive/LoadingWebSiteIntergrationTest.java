package com.jubin.springboot.reactive;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LoadingWebSiteIntergrationTest {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void intergrationTest() {
		webTestClient.get().uri("/").exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.TEXT_HTML)
			.expectBody(String.class)
			.consumeWith(exchangeResult -> {
				assertThat(exchangeResult.getResponseBody()).contains("Jubin");
			});

	}
}
