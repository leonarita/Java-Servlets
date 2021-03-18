package model.repository;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	

	
	@SuppressWarnings("unchecked")
	public static Set<ContaComum> recuperarContasComuns()
	{
		Set<ContaComum> resultado = null;
		
		try
		{
			// HQL: FROM ContaComum
			Stream<ContaComum> contasStream = getEntityManager()
					.createQuery("FROM " + ContaComum.class.getName()).getResultStream();
			
			resultado = contasStream
			  .sorted(Comparator.comparing(ContaComum::getNumeroConta)) //comparator - how you want to sort it
			  .collect(Collectors.toSet());
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as contas cadastradas! " + e.getMessage());
			e.printStackTrace();
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
