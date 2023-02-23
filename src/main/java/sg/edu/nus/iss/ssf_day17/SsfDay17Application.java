package sg.edu.nus.iss.ssf_day17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ssf_day17.services.HttpBinService;
import sg.edu.nus.iss.ssf_day17.services.WeatherService;

@SpringBootApplication
public class SsfDay17Application  implements CommandLineRunner{

	@Autowired
	private HttpBinService httpBinSvc;

	@Autowired
	private WeatherService weatherSvc;

	public static void main(String[] args) {
		SpringApplication.run(SsfDay17Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		//httpBinSvc.get();
		//httpBinSvc.get("fred","fred@gmail.com");
		//httpBinSvc.post("fred","fred@gmail.com");
		//httpBinSvc.postAsJson("fred","fred@gmail.com");
		//weatherSvc.getWeather("Singapore");

	}

}
