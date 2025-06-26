package com.example.sistema_vendas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		@Entity
		class Produto {
			@Id
			private Long id;
			private String nome;
		}

	}


}
