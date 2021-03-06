

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Banco {
		
	public void salvaCliente(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			em.getTransaction().begin();
			
			if(cliente.getId() == null) {
				em.persist(cliente);
			}
			else {
				em.merge(cliente);
			}
				
			em.getTransaction().commit();
			
		} catch (Exception e) {
		
			System.err.println(e);
			em.getTransaction().rollback();
		
		} finally {
			em.close();
			emf.close();
		}
	} 
	
	public List<Cliente> getListaCliente(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		
		List<Cliente> listaClientes = null;
		try {
			
			listaClientes = em.createQuery("from Cliente c").getResultList();
			return listaClientes;
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			em.close();
			emf.close();
		}
		
		return null;
	}
	
	public Cliente getClientebyId(Integer id) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		
		Cliente localiza = null;
		
		try {
			localiza = em.find(Cliente.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			em.close();
			emf.close();
		}
		
		return localiza;
	}
	
	public void deletaCliente(Integer id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = null;
		
		try {
			cliente = em.find(Cliente.class, id);
			em.getTransaction().begin();
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
