package fr.axzial.catmanager;

import fr.axzial.catmanager.controller.CatController;
import fr.axzial.catmanager.controller.CatOwnerController;
import fr.axzial.catmanager.dto.requestbody.CatOwnerWithCatsId;
import fr.axzial.catmanager.dto.requestbody.CatWithOwnerIdDto;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatOwner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CatManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CatManagerApplication.class, args);
	}

}
