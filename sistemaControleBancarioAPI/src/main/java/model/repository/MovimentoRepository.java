package model.repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityTransaction;

import model.Movimento;

public class MovimentoRepository extends PersistenceConfig
{
	public static boolean criarMovimento(Movimento movimento)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			getEntityManager().persist(movimento);
			transaction.commit();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar persistir o Movimento! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Movimento> recuperarMovimentosPorNumeroConta(long numeroConta)
	{
		Set<Movimento> resultado = null;
		
		try
		{
			// HQL: FROM Movimento WHERE numeroconta = ? ORDER BY dataHoraMovimento ASC
			Stream<Movimento> movimentosStream =
					getEntityManager().createQuery("FROM " + Movimento.class.getName() +
							" WHERE numeroconta = :numeroconta" +
							" ORDER BY dataHoraMovimento ASC")
						.setParameter("numeroconta", numeroConta)
						.getResultStream();
			
			resultado = movimentosStream.collect(Collectors.toSet());
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar o Movimento! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
}
