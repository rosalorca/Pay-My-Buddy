package com.openclassrooms.paymybuddy;

import com.openclassrooms.controller.LoginController;
import jdk.dynalink.linker.support.Guards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PayMyBuddyApplicationTests {
	@Autowired
	private LoginController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();

	}

}
