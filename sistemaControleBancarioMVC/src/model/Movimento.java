package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.repository.ContaComumRepository;
import model.repository.MovimentoRepository;
import model.repository.PersistenceConfig;

@Entity
@Table(name = "movimentos")
public class Movimento implements Serializable
{
	/* ATRIBUTOS */
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idMovimento;
	
	@Column(name = "tipo")
	private int tipoMovimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahora")
	private Calendar dataHoraMovimento;
	
	@Column(name = "valor")
	private double valorMovimento;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "numeroconta")
	private ContaComum contaMovimento;
	
	/* CONSTRUTORES */
	
	public Movimento() {}
	
	public Movimento(int tipoMovimento, Calendar dataHoraMovimento, double valorMovimento,
			ContaComum contaMovimento)
	{
		this.tipoMovimento = tipoMovimento;
		this.dataHoraMovimento = dataHoraMovimento;
		this.valorMovimento = valorMovimento;
		this.contaMovimento = contaMovimento;
	}
	
	public Movimento(int tipoMovimento, double valorMovimento, ContaComum contaMovimento)
	{
		this.tipoMovimento = tipoMovimento;
		this.valorMovimento = valorMovimento;
		this.contaMovimento = contaMovimento;
	}
	
	/* GETTERS AND SETTERS */
	
	public int getIdMovimento()
	{
		return idMovimento;
	}

	public void setIdMovimento(int id)
	{
		this.idMovimento = id;
	}
	
	public int getTipoMovimento()
	{
		return tipoMovimento;
	}
	
	public void setTipoMovimento(int tipoMovimento)
	{
		this.tipoMovimento = tipoMovimento;
	}
	
	public Calendar getDataHoraMovimento()
	{
		return dataHoraMovimento;
	}
	
	public void setDataHoraMovimento(Calendar dataHoraMovimento)
	{
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
	public double getValorMovimento()
	{
		return valorMovimento;
	}
	
	public void setValorMovimento(double valorMovimento)
	{
		this.valorMovimento = valorMovimento;
	}
	
	public ContaComum getContaMovimento()
	{
		return contaMovimento;
	}
	
	public void setContaMovimento(ContaComum contaMovimento)
	{
		this.contaMovimento = contaMovimento;
	}
	
	/* DEMAIS MÉTODOS */
	
	// Método interno auxiliar.
	// Optei por implementá-lo para que o método registrarMovimento não ficasse tão extenso.
	private boolean efetivarMovimento()
	{
		boolean resultado = false; // true=Sucesso e false=Falha
		
		if (this.contaMovimento != null)
		{
			// Garante que eu tenho em this.contaMovimento os dados mais atuais da conta
			this.contaMovimento = ContaComumRepository.recuperarContaComumPorNumeroConta(this.contaMovimento.getNumeroConta());
			
			if (this.contaMovimento != null) // Se deu certo a consulta anterior
			{
				if (this.tipoMovimento == 1) // Depósito
				{
					this.contaMovimento.setSaldoConta(this.contaMovimento.getSaldoConta() + this.valorMovimento);
					ContaComumRepository.atualizarContaComum(this.contaMovimento);
					resultado = true;
					
					System.out.println("Depósito efetuado com sucesso!");
				}
				else if (this.tipoMovimento == 2) // Saque
				{
					if (this.contaMovimento.getSaldoConta() >= this.valorMovimento)
					{
						// Só é possível sacar se existir saldo suficiente
						this.contaMovimento.setSaldoConta(this.contaMovimento.getSaldoConta() - this.valorMovimento);
						ContaComumRepository.atualizarContaComum(this.contaMovimento);
						resultado = true;
						
						System.out.println("Saque efetuado com sucesso!");
					}
					else
					{
						System.out.println("Saque não pôde ser efetivado. Sem saldo!");
					}
				}
			}
			
			PersistenceConfig.closeEntityManager();;
		}
		
		return resultado;
	}
	
	public int registrarMovimento()
	{
		int resultado = 0; // 1=Sucesso e 0=Fracasso
		
		if (this.contaMovimento != null) // Somente registra se existir conta vinculada
		{
			this.dataHoraMovimento = Calendar.getInstance();
			
			if (this.efetivarMovimento()) // Somente registra se o movimento for efetivado na conta
			{
				// Garante que eu tenho em this.contaMovimento os dados mais atuais da conta
				this.contaMovimento = ContaComumRepository.recuperarContaComumPorNumeroConta(this.contaMovimento.getNumeroConta());
				
				this.contaMovimento.getMovimentosConta().add(this);
				
				if(MovimentoRepository.criarMovimento(this)) // Persiste este objeto, registrando o movimento no BD.
				{
					// Sucesso na inserção
					resultado = 1;
				}
				
				PersistenceConfig.closeEntityManager();
			}
		}
		
		return resultado;
	}
}