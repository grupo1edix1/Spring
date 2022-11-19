package serviciorest.cliente;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Videojuego;
import serviciorest.cliente.servicio.ServicioProxyVideojuego;

@SpringBootApplication
public class AiVideojuegosClienteApplication implements CommandLineRunner{
	
	

	@Autowired
	private ServicioProxyVideojuego sp;
	
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	

	public static void main(String[] args) {
		
		SpringApplication.run(AiVideojuegosClienteApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("APLICACIÓN VIDEOJUEGOS");
		System.out.println("-------------------");
		System.out.println("INICIANDO MENÚ");		
				
		boolean continuar = true;
		String in;
		Scanner scan = new Scanner(System.in);
		String res;
		int id;
		String nombre, compania;
		double nota;
		Videojuego videojuego;
		
		do {			
			System.out.println("MENU");
			System.out.println("1. Dar de alta un videojuego");
			System.out.println("2. Dar de baja un videojuego por ID");
			System.out.println("3. Modificar un videojuego por ID");
			System.out.println("4. Obtener un videojuego por ID");
			System.out.println("5. Listar todos los videojuegos");
			System.out.println("0. Salir");
			System.out.println("");		
			in = scan.nextLine();
			switch(Integer.parseInt(in)) {
			
			case 0:
				continuar = false;
				break;
			case 1:
				System.out.println("Introduzca Nombre");
				nombre = scan.nextLine();
				System.out.println("Introduzca Compañía");
				compania = scan.nextLine();
				System.out.println("Introduzca Nota");
				nota = Integer.parseInt(scan.nextLine());
				videojuego = new Videojuego(nombre,compania,nota);
				sp.sign(videojuego);
				
				break;
			case 2:
				System.out.println("Introduzca ID");
				id = Integer.parseInt(scan.nextLine());
				do{
					System.out.println("¿Este es el videojuego que quiere eliminar? S/N");
					System.out.println(sp.get(id));
					res = scan.nextLine();					
				}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
				if (res.equalsIgnoreCase("S")) {
					sp.deleteVideojuego(id);
				}
				break;
			case 3:
				System.out.println("Introduzca ID");
				id = Integer.parseInt(scan.nextLine());
				do{
					System.out.println("¿Este es el videojuego que quiere modificar? S/N");
					System.out.println(sp.get(id));
					res = scan.nextLine();					
				}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));				
				System.out.println("Introduzca nuevo Nombre");
				nombre = scan.nextLine();
				System.out.println("Introduzca nuevo Compañía");
				compania = scan.nextLine();
				System.out.println("Introduzca nuevo Nota");
				nota = Integer.parseInt(scan.nextLine());
				videojuego = new Videojuego(id, nombre, compania, nota);
				sp.set(videojuego);
				break;
			case 4:
				System.out.println("Introduzca ID");
				id = Integer.parseInt(scan.nextLine());
				System.out.println(sp.get(id));
				break;
				
			case 5:
				System.out.println("Esta es su lista de juegos");
				System.out.println(sp.list()); 
				break;
				
			default:
				System.out.println("Instrucción invalida");
			break;			
			
			}				
		}while(continuar);		
	SpringApplication.exit(context, () -> 0);
	scan.close();
	System.out.println("CLIENTE: Fin del programa");
		
			
			
	}
}


