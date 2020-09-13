package br.com.partidasJSF.daos;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.partidasJSF.models.Jogador;
import br.com.partidasJSF.util.HibernateUtil;

public class JogadorDAO {
	
private EntityManager em;
	
	public JogadorDAO() {
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	}
	
	public void save(Jogador jogador) {
		
		try {
			em.getTransaction().begin();
			em.persist(jogador);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public void delete(Long id) {
        try {
            em.getTransaction().begin();
            Jogador obj = em.find(Jogador.class, id);
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
	
	public Jogador findById(Long id) {
		return em.find(Jogador.class, id);
	}

}
