package br.com.partidasJSF.services;

import java.util.List;

import br.com.partidasJSF.daos.PartidaDAO;
import br.com.partidasJSF.models.Partida;

public class PartidaService {
	
	private PartidaDAO partidaDao;
	
	public PartidaService() {
		partidaDao = new PartidaDAO();
	}
	
	public void save(Partida partida) throws Exception {
		validate(partida);
		
		partidaDao.save(partida);
	}
	
	public void update(Partida partida) throws Exception {
		validate(partida);
		
		partidaDao.update(partida);
	}
	
	public void delete(Long id) {
		partidaDao.delete(id);
	}
	
	public Partida findById(Long id) {
		return partidaDao.findById(id);
	}
	
	public List<Partida> findAll() {
		return partidaDao.findAll();
	}

	public void validate(Partida partida) throws Exception {
		if(partida.getData() == null) {
			throw new Exception("Data Inválida.");
		}
		if(partida.getMapa() == null || partida.getMapa() <= 0) {
			throw new Exception("Mapa Inválido.");
		}
		if(partida.getTitulo() == null || partida.getTitulo().isEmpty()) {
			throw new Exception("Título Inválido.");
		}
	}

}
