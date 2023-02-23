package sg.edu.nus.iss.ssf_day17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ssf_day17.services.HttpBinService;

@SpringBootApplication
public class SsfDay17Application  implements CommandLineRunner{

	@Autowired
	private HttpBinService httpBinSvc;

	public static void main(String[] args) {
		SpringApplication.run(SsfDay17Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		httpBinSvc.get();
		//httpBinSvc.get("fred","fred@gmail.com");

	}

}
