package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable
{
	/* ATRIBUTOS */
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int idPessoa;
	
	@Column(name = "nome", nullable = false)
	protected String nomePessoa;
	
	@Column(name = "endereco")
	protected String enderecoPessoa;
	
	@Column(name = "cep")
	protected long cepPessoa;
	
	@Column(name = "telefone")
	protected String telefonePessoa;
	
	@Column(name = "email", unique = true, nullable = false)
	protected String email;
	
	@Column(name = "renda")
	protected double rendaPessoa;
	
	@Column(name = "situacao")
	protected int situacaoPessoa;
	
	@Column(name = "senha", nullable = false)
	protected String senha;
	
	// FetchType.LAZY -> Diz ao provedor de persistência para não buscar as entidades associadas no banco de dados até que elas sejam necessárias.
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "pessoas_contascomuns", joinColumns = { @JoinColumn(name = "idpessoa", referencedColumnName = "id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "numeroconta", referencedColumnName = "numeroconta") })
	protected Set<ContaComum> contas;
	
	/* CONSTRUTORES */
	
	public Pessoa()
	{
		this.situacaoPessoa = 1;
		this.contas = new HashSet<ContaComum>();
	}
	
	public Pessoa(String nomePessoa, String enderecoPessoa, long cepPessoa, String telefonePessoa,
			String email, double rendaPessoa, int situacaoPessoa, String senha)
	{
		this.nomePessoa = nomePessoa;
		this.enderecoPessoa = enderecoPessoa;
		this.cepPessoa = cepPessoa;
		this.telefonePessoa = telefonePessoa;
		this.email = email;
		this.rendaPessoa = rendaPessoa;
		this.situacaoPessoa = situacaoPessoa;
		this.senha = senha;
		this.contas = new HashSet<ContaComum>();
	}
	
	/* GETTERS AND SETTERS */

	public int getIdPessoa()
	{
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa)
	{
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa()
	{
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa)
	{
		this.nomePessoa = nomePessoa;
	}

	public String getEnderecoPessoa()
	{
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(String enderecoPessoa)
	{
		this.enderecoPessoa = enderecoPessoa;
	}

	public long getCepPessoa()
	{
		return cepPessoa;
	}

	public void setCepPessoa(long cepPessoa)
	{
		this.cepPessoa = cepPessoa;
	}

	public String getTelefonePessoa()
	{
		return telefonePessoa;
	}

	public void setTelefonePessoa(String telefonePessoa)
	{
		this.telefonePessoa = telefonePessoa;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public double getRendaPessoa()
	{
		return rendaPessoa;
	}

	public void setRendaPessoa(double rendaPessoa)
	{
		this.rendaPessoa = rendaPessoa;
	}

	public int getSituacaoPessoa()
	{
		return situacaoPessoa;
	}

	public void setSituacaoPessoa(int situacaoPessoa)
	{
		this.situacaoPessoa = situacaoPessoa;
	}
	
	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public Set<ContaComum> getContas()
	{
		return contas;
	}

	public void setContas(Set<ContaComum> contas)
	{
		this.contas = contas;
	}
}