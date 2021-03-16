package model.repository;

import javax.persistence.EntityTransaction;

import model.ContaComum;

public class ContaComumRepository extends PersistenceConfig
{
	public static boolean criarContaComum(ContaComum contaComum)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			getEntityManager().persist(contaComum);
			transaction.commit();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar persistir a conta comum! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public static ContaComum recuperarContaComumPorNumeroConta(long numeroConta)
	{
		ContaComum resultado = null;
		
		try
		{
			resultado = getEntityManager().find(ContaComum.class, numeroConta);
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a conta comum! " + e.getMessage());
			e.printStackTrace();
			resultado = null;
		}
		
		return resultado;
	}
	
	public static boolean atualizarContaComum(ContaComum contaComum)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			getEntityManager().merge(contaComum);
			transaction.commit();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar a conta comum! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public static boolean excluirContaComum(ContaComum contaComum)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{	
			transaction.begin();
			contaComum = getEntityManager().find(ContaComum.class, contaComum.getNumeroConta());
			getEntityManager().remove(contaComum);
			transaction.commit();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar remover a Conta Comum! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
}
