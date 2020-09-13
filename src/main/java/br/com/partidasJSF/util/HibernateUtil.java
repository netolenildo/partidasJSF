package br.com.partidasJSF.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	private static EntityManagerFactory emf;
	
    private HibernateUtil() {
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            try {
                emf = Persistence
                        .createEntityManagerFactory("SysTodo");
            } catch (Exception ex) {
                System.err.println("Initial SessionFactory creation failed."
                        + ex);
            }
        }
        return emf;
    }
}
