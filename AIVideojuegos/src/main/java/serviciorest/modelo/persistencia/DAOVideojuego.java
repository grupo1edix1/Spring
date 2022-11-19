package serviciorest.modelo.persistencia;

import serviciorest.modelo.entidad.Videojuego;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class DAOVideojuego {
	
	public List<Videojuego> biblioteca;
	int id=0;
	
	public DAOVideojuego (){
			
		System.out.println("DaoVideojuego");
		biblioteca = new ArrayList<Videojuego>();
		Videojuego v1 = new Videojuego(id++, "God of War", "Santa Monica Studios", 99);
		Videojuego v2 = new Videojuego(id++, "Monkey Island", "Lucas Arts", 100);
		Videojuego v3 = new Videojuego(id++, "Superman 64", "Titus Interactive", 0.1);
		Videojuego v4 = new Videojuego(id++, "Bloodborne", "Fromsoftware", 95);
		Videojuego v5 = new Videojuego(id++, "Frostpunk", "11 bits Stusios", 89);
		
		biblioteca.add(v1);
		biblioteca.add(v2);
		biblioteca.add(v3);
		biblioteca.add(v4);
		biblioteca.add(v5);
		
				
		}
	
	
	public Videojuego get(int gameID){
		Videojuego videojuego = null;
		try {
			for (Videojuego v : biblioteca) {
				if (v.getGameID() == gameID) {
					videojuego =v;
				}
			}
			return videojuego;
		} catch (NullPointerException iobe) {
			System.out.println("No existe");
			return null;
		}
	}
	
	public List<Videojuego> getListNombre(String nombre){		
		List<Videojuego> lista = new ArrayList<Videojuego>();
		try {
			for (Videojuego v : biblioteca) {
				if (v.getNombre().toLowerCase().contains(nombre.toLowerCase())){
					lista.add(v);
				}
			}
			lista.toString();
			return lista;			
		} catch (NullPointerException iobe) {
			System.out.println("No existe");
			return null;
		}
	}
	
	public boolean checkNombre(String nombre) {
		boolean check = true;
		for (Videojuego v : biblioteca) {
			if (v.getNombre().equalsIgnoreCase(nombre)){
				check = false;
				return check;
				}
		}
		return check;			
	}
	
	public boolean checkID(int id) {
		boolean check = true;
		for (Videojuego v : biblioteca) {
			if (v.getGameID()==id){
				check = false;
				return check;
				}
		}
		return check;			
	}

	
	public List<Videojuego> getBiblioteca() {
		return biblioteca;
	}


	public void setBiblioteca(List<Videojuego> biblioteca) {
		this.biblioteca = biblioteca;
	}


	public void add(Videojuego videojuego) {
		videojuego.setGameID(id++);
		this.biblioteca.add(videojuego);		
	}
		
	
	
	public Videojuego remove(int gameID) {			
			try{
				return biblioteca.remove(biblioteca.indexOf(this.get(gameID)));
			}catch (Exception e) {
				System.out.println("Error");
				return null;
			}
	}
	
	public Videojuego update(Videojuego v) {
		try {		
			Videojuego vSet = this.get(v.getGameID());
			vSet.setGameID(v.getGameID());
			vSet.setNombre(v.getNombre());
			vSet.setCompania(v.getCompania());
			vSet.setNota(v.getNota());
			return vSet;
		}catch (Exception e) {
				System.out.println("Update -> Error");
				return null;
		}
			
	}
	
}
	

	
				
	

		


