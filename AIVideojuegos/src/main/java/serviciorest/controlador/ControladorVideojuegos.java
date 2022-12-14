package serviciorest.controlador;

import serviciorest.modelo.persistencia.DAOVideojuego;
import serviciorest.modelo.entidad.Videojuego;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControladorVideojuegos {
	
	@Autowired
	private DAOVideojuego dao; 

	
	@GetMapping(path="videojuegos/{gameID}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Videojuego> get(@PathVariable("gameID") int gameID) {
		System.out.println("Buscando GameID: " + gameID);
		Videojuego v = dao.get(gameID);
		if(v != null) {
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	@GetMapping(path="videojuegos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Videojuego>> getBiblioteca(){					
			return new ResponseEntity<List<Videojuego>>(dao.getBiblioteca(),HttpStatus.OK);
		}
	
	
	@PostMapping (path="videojuegos",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> add(@RequestBody Videojuego v){
		if (dao.checkNombre(v.getNombre())){			
			System.out.println("Añadiendo GameID: " + v.getGameID());
			dao.add(v);
			return new ResponseEntity<Videojuego>(v,HttpStatus.CREATED);//201 CREATED
		} else {
			return new ResponseEntity<Videojuego>(v,HttpStatus.CONFLICT);//409 CONFLICT
		}
	}
	
	@PutMapping (path="videojuegos/{gameID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> update(@PathVariable("gameID") int id, @RequestBody Videojuego v){
		if (dao.get(id) != null) {
			if (dao.checkNombre(v.getNombre()) && id == v.getGameID()) {	
				System.out.println("Actualizando Videojuego " + dao.get(v.getGameID()).getNombre());		
				Videojuego vUp = dao.update(v);
				return new ResponseEntity<Videojuego>(vUp, HttpStatus.OK); //200 OK						
			} else {
				return new ResponseEntity<Videojuego>(v,HttpStatus.CONFLICT);//409 CONFLICT
			}
		}else{
		return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	@DeleteMapping (path="videojuegos/{gameID}")
	public ResponseEntity<Videojuego> delete(@PathVariable("gameID") int gameID){		
		Videojuego v = dao.get(gameID);
		if(v != null) {
			System.out.println("Borrando GameID: " + gameID);
			dao.remove(gameID);
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
}
