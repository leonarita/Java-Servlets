package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotosperfis")
public class FotoPerfil implements Serializable
{
	/* ATRIBUTOS */
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "contenttype", nullable = false)
	private String contentType; // MIME type (ex. "image/jpeg", "image/png")
	
	@Column(name = "arquivo", nullable = false)
	private byte[] arquivo;
	
	/* CONSTRUTORES */
	
	public FotoPerfil() { }
	
	public FotoPerfil(String nome, String contentType, byte[] arquivo)
	{
		this.nome = nome;
		this.contentType = contentType;
		this.arquivo = arquivo;
	}
	
	/* GETTERS AND SETTERS */

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}	

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public byte[] getArquivo()
	{
		return arquivo;
	}

	public void setArquivo(byte[] arquivo)
	{
		this.arquivo = arquivo;
	}
}
