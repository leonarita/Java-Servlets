package model.repository;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityTransaction;

import model.Pessoa;

public class PessoaRepository extends PersistenceConfig
{
	public static boolean criarPessoa(Pessoa pessoa)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			getEntityManager().persist(pessoa);
			transaction.commit();
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar persistir uma nova pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public static Pessoa recuperarPessoaPorId(int id)
	{
		Pessoa resultado = null;
		
		try
		{
			resultado = getEntityManager().find(Pessoa.class, id);
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static Pessoa recuperarPessoaPorEmail(String email)
	{
		Set<Pessoa> pessoaSet = null;
		Pessoa resultado = null;
		
		try
		{
			// HQL: FROM Pessoa WHERE email = ?
			Stream<Pessoa> pessoaStream = getEntityManager()
					.createQuery("FROM " + Pessoa.class.getName() + " WHERE email = :email")
					.setParameter("email", email).getResultStream();
			
			pessoaSet = pessoaStream.collect(Collectors.toSet());
			
			if (pessoaSet != null && !pessoaSet.isEmpty())
			{
				
				for (Pessoa p : pessoaSet)
				{
					resultado = p;
					break;
				}
			}
			
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Pessoa> recuperarPessoas()
	{
		Set<Pessoa> resultado = null;
		
		try
		{
			// HQL: FROM Pessoa
			Stream<Pessoa> pessoasStream = getEntityManager()
					.createQuery("FROM " + Pessoa.class.getName()).getResultStream();
			
			resultado = pessoasStream
			  .sorted(Comparator.comparing(Pessoa::getNomePessoa)) //comparator - how you want to sort it
			  .collect(Collectors.toSet());
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas cadastradas! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	

	
	@SuppressWarnings("unchecked")
	public static Set<Pessoa> recuperarPessoasPorStatus(int status)
	{
		Set<Pessoa> resultado = null;
		
		try
		{
			// HQL: FROM Pessoa WHERE situacaoPessoa = ?
			Stream<Pessoa> pessoasStream = getEntityManager()
					.createQuery("FROM " + Pessoa.class.getName() + " WHERE situacaoPessoa = :status")
					.setParameter("status", status)
					.getResultStream();
			
			resultado = pessoasStream
			  .sorted(Comparator.comparing(Pessoa::getNomePessoa)) //comparator - how you want to sort it
			  .collect(Collectors.toSet());
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas cadastradas! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public static boolean atualizarPessoa(Pessoa pessoa)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			getEntityManager().merge(pessoa);
			transaction.commit();
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar os dados da pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public static boolean excluirPessoa(Pessoa pessoa)
	{
		boolean resultado = true;
		EntityTransaction transaction = getEntityManager().getTransaction();
		
		try
		{
			transaction.begin();
			pessoa = getEntityManager().find(Pessoa.class, pessoa.getIdPessoa());
			getEntityManager().remove(pessoa);
			transaction.commit();
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar remover a pessoa! " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
			resultado = false;
		}
		
		return resultado;
	}
	
	public static boolean excluirPessoaPorId(int id)
	{
		boolean resultado = true;
		
		try
		{
			Pessoa pessoa = recuperarPessoaPorId(id);
			resultado = excluirPessoa(pessoa);
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar remover a pessoa! " + e.getMessage());
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
}
