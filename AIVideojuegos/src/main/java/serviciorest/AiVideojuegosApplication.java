package serviciorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiVideojuegosApplication {

	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		SpringApplication.run(AiVideojuegosApplication.class, args);
		System.out.println("Servicio Rest -> Cargado con éxito...");
	}

}
