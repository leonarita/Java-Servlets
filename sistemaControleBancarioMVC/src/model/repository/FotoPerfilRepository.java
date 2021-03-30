package model.repository;

import model.FotoPerfil;

public class FotoPerfilRepository extends PersistenceConfig
{
	public static FotoPerfil recuperarFotoPerfilPorId(long id)
	{
		FotoPerfil resultado = null;
		
		try
		{
			resultado = getEntityManager().find(FotoPerfil.class, id);
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a foto de perfil! " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultado;
	}
}
