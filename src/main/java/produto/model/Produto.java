package produto.model;

public class Produto{
	
	private Long id;
	
	private String nome;
	
	private Integer qtd;
	
	private Double preco;
	
	@Override
	public boolean equals(Object o){
		
		Produto that = (Produto) o;
		
		return this.id.compareTo(that.id) == 0;
	}
	
	public Long getId(){
		
		return id;
	}

	public void setId(Long id){
		
		this.id = id;
	}

	public String getNome(){
		
		return nome;
	}

	public void setNome(String nome){
		
		this.nome = nome;
	}

	public Integer getQtd(){
		
		return qtd;
	}

	public void setQtd(Integer qtd){
		
		this.qtd = qtd;
	}

	public Double getPreco(){
		
		return preco;
	}

	public void setPreco(Double preco){
	
		this.preco = preco;
	}
}