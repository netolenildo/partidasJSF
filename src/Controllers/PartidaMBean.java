package Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Services.JogadorService;
import Services.PartidaService;
import models.Jogador;
import models.Partida;

@ManagedBean
@SessionScoped
public class PartidaMBean {

	private Partida partida;
	
	private List<Partida> partidas;
	
	private Jogador jogador;
	
	private List<Jogador> jogadores;
	
	private PartidaService partidaService;
	
	private JogadorService jogadorService;
	
	public PartidaMBean() {
		partida = new Partida();
		jogador = new Jogador();
		
		partidas = new ArrayList<Partida>();
		jogadores = new ArrayList<Jogador>();
	}
	
	public String listarPartidas() {
		partidas = partidaService.findAll();
		
		return "";
	}
	
	public String cadastrarPartida() {
		partida = new Partida();
		
		return "";
	}
	
	public String salvarPartida() throws Exception {
		try {
			partidaService.save(partida);
			
			addMensagemSucesso("Partida cadastrada com sucesso.");
		}catch(Exception e) {
			addMensagemErro(e.getMessage());
		}
		
		return null;
	}
	
	public void removerPartida(Long id) {
		partidaService.delete(id);
		
		addMensagemSucesso("Partida removida com sucesso.");
		
		return;
	}
	
	public String visualizarPartida(Long id) {
		partida = partidaService.findById(id);
		
		jogadores = partida.getJogadores();
		
		jogador = new Jogador();
		
		return "";
	}
	
	public String salvarJogador() {
		try {
			jogadorService.save(partida, jogador);
			
			addMensagemSucesso("jogador cadastrado com sucesso.");
		}catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		
		return null;
	}
	
	public void removerJogador(Long id) {
		jogadorService.delete(id);
		
		return;
	}
	
	public void addMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagem));
	}
	
	public void addMensagemSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
}
