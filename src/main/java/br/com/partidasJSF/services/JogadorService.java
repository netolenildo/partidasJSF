package br.com.partidasJSF.services;

import br.com.partidasJSF.daos.JogadorDAO;
import br.com.partidasJSF.daos.PartidaDAO;
import br.com.partidasJSF.models.Jogador;
import br.com.partidasJSF.models.Partida;

public class JogadorService {
	
	private JogadorDAO jogadorDao;
	
	private PartidaDAO partidaDao;
	
	public JogadorService() {
		jogadorDao = new JogadorDAO();
		partidaDao = new PartidaDAO();
	}
	
	public void save(Partida partida, Jogador jogador) throws Exception {
		if(partida.getJogadores().size() > 10) {
			throw new Exception("Limite de jogadores atingido.");
		}
		validate(jogador);
		
		jogadorDao.save(jogador);
		
		partida.getJogadores().add(jogador);
		
		partidaDao.update(partida);
		
	}
	
	public void delete(Long id) {
		jogadorDao.delete(id);
	}
	
	public Jogador findById(Long id) {
		return jogadorDao.findById(id);
	}

	public void validate(Jogador jogador) throws Exception {
		if(jogador.getNome() == null || jogador.getNome().isEmpty()) {
			throw new Exception("Nome Inválido.");
		}
		if(jogador.getPosicao() == null || jogador.getPosicao() <= 0) {
			throw new Exception("Posição Inválida.");
		}
	}

}
