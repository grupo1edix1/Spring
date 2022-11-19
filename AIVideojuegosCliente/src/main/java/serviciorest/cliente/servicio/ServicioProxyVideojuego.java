package serviciorest.cliente.servicio;

import java.util.Arrays;
import java.util.List;

import serviciorest.cliente.entidad.Videojuego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioProxyVideojuego {
	
	public static final String URL = "http://localhost:8080/videojuegos/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Videojuego get(int gameID){
		try {		
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + gameID, Videojuego.class);
			HttpStatus hs= re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				return re.getBody();
			}else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("obtener -> La persona NO se ha encontrado, id: " + gameID);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	public void set(Videojuego videojuego) {
		try {			
			restTemplate.put(URL + videojuego.getGameID(), videojuego, Videojuego.class);			
		}catch(HttpClientErrorException e){
			System.out.println("obtener -> La persona NO se ha encontrado, id: " + videojuego);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		}
	}
	
	public List<Videojuego> list(){		
		try {
			ResponseEntity<Videojuego[]> re = restTemplate.getForEntity(URL, Videojuego[].class);
			Videojuego[] v = re.getBody();
			return Arrays.asList(v);
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de personas");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	public void deleteVideojuego(int gameID){
		try {		
			restTemplate.delete(URL + gameID);
			System.out.println("Se borró el juego con ID: " + gameID);
		}catch (HttpClientErrorException e) {
			System.out.println(URL + gameID);
			System.out.println("obtener -> La persona NO se ha encontrado, id: " + gameID);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());		    
		}
	}
	
	public Videojuego sign(Videojuego videojuego){
		try {		
			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, videojuego, Videojuego.class);
			System.out.println("Se añadió videojuego");
			return re.getBody();
		}catch (HttpClientErrorException e) {
			System.out.println("alta -> La persona NO se ha dado de alta, id: " + videojuego);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;	    
		}
	}

}
