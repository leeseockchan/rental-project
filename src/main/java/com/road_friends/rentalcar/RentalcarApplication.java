package com.road_friends.rentalcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class RentalcarApplication {
	//hidden method(put, delete)사용하기 위해 작성
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}


	public static void main(String[] args) {
		SpringApplication.run(RentalcarApplication.class, args);

	}

}
