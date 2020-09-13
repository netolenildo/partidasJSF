package br.com.partidasJSF.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.partidasJSF.models.Jogador;
import br.com.partidasJSF.models.Mapa;
import br.com.partidasJSF.models.Partida;
import br.com.partidasJSF.models.Posicao;
import br.com.partidasJSF.services.JogadorService;
import br.com.partidasJSF.services.PartidaService;

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

		partidaService = new PartidaService();
		jogadorService = new JogadorService();
	}

	public void carregarListaPartidas() {
		partidas = partidaService.findAll();
	}

	public String listarPartidas() {
		partidas = new ArrayList<Partida>();

		return "/index.jsf";
	}

	public String cadastrarPartida() {
		partida = new Partida();

		return "/form.jsf";
	}

	public String salvarPartida() throws Exception {
		try {
			partidaService.save(partida);

			addMensagemSucesso("Partida cadastrada com sucesso.");

			partida = new Partida();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}

		return null;
	}

	public String removerPartida(Long id) {
		partidaService.delete(id);

		addMensagemSucesso("Partida removida com sucesso.");

		return null;
	}

	public String visualizarPartida(Long id) {
		partida = partidaService.findById(id);

		jogadores = partida.getJogadores();

		jogador = new Jogador();

		return "/detalhes.jsf";
	}

	public String salvarJogador() {
		try {
			jogadorService.save(partida, jogador);

			addMensagemSucesso("Jogador cadastrado com sucesso.");
			jogador = new Jogador();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}

		return null;
	}

	public String removerJogador(Long id) throws Exception {
		Jogador jogador = jogadorService.findById(id);
		
		partida.getJogadores().remove(jogador);
		
		partidaService.update(partida);
		
		jogadorService.delete(id);
		
		addMensagemSucesso("Jogador removido com sucesso");

		return null;
	}

	public List<Integer> getMapas() {
		return Mapa.getMapas();
	}

	public String getNomeMapa(int mapa) {
		return Mapa.getNome(mapa);
	}

	public List<Integer> getPosicoes() {
		return Posicao.posicoes();
	}

	public String getNomePosicao(int posicao) {
		return Posicao.getNome(posicao);
	}

	public void addMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
	}

	public void addMensagemSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
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
