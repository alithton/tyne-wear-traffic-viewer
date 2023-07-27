package com.afsmith.tyneweartrafficviewer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(args = "--local --local-routes")
@ActiveProfiles("test")
class TyneWearTrafficViewerApplicationTests {

	@Test
	void contextLoads() {
	}

}
