package com.prime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PrimeSystemBackEndApplicationTests {

	@Test
	void contextLoads() {
		FeedResourceTest feedResourceTest = new FeedResourceTest();
		feedResourceTest.deveRetornarListaPaginadaPostsDoUsuarioLogado();
	}

}
