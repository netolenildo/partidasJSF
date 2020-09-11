package Services;

import Daos.JogadorDao;
import Daos.PartidaDao;
import models.Jogador;
import models.Partida;

public class JogadorService {
	
	private JogadorDao jogadorDao;
	
	private PartidaDao partidaDao;
	
	public Jogador save(Partida partida, Jogador jogador) throws Exception {
		if(partida.getJogadores().size() > 10) {
			throw new Exception("Limite de jogadores atingido.");
		}
		validate(jogador);
		
		Jogador jogadorDB = jogadorDao.save(jogador);
		
		partida.getJogadores().add(jogadorDB);
		
		partidaDao.update(partida);
		
		return jogadorDB;
	}
	
	public void delete(Long id) {
		jogadorDao.delete(id);
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
