package br.com.partidasJSF.daos;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.partidasJSF.models.Partida;
import br.com.partidasJSF.util.HibernateUtil;

public class PartidaDAO {

	private EntityManager em;
	
	public PartidaDAO() {
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	}
	
	public void save(Partida partida) {
		
		try {
			em.getTransaction().begin();
			em.persist(partida);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public void update(Partida partida) {
		try {
			em.getTransaction().begin();
			em.merge(partida);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public void delete(Long id) {
        try {
            em.getTransaction().begin();
            Partida obj = em.find(Partida.class, id);
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
	
	public List<Partida> findAll() {
        List<Partida> result = null;
        try {
            Query query = em.createQuery("FROM Partida ORDER BY id DESC");
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public Partida findById(Long id) {
		return em.find(Partida.class, id);
	}
}
