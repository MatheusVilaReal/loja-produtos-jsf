package produto.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import produto.model.Produto;

@Named
@SessionScoped
public class CarrinhoController implements Serializable{

	private static final long serialVersionUID = 1L;

	List <Produto> listaProdutosEscolhidos;
	
	public List <Produto> getListaProdutosEscolhidos(){
		
		return listaProdutosEscolhidos;
	}

	public Double getValorTotalCarrinho(){
		
		Double total = (double) 0;
		
		for(Produto p : listaProdutosEscolhidos){
			
			total += p.getPreco();
		}
		
		return total;
	}
	
	public void setListaProdutosEscolhidos(List <Produto> lista){
		this.listaProdutosEscolhidos = lista;
	}
	
	public void adicionarEscolhido(Produto produto){
		
		Produto produtoCarrinho;
		
		if (listaProdutosEscolhidos.contains(produto) == false){
			
			produtoCarrinho = new Produto();
			
			produtoCarrinho.setNome(produto.getNome());
			produtoCarrinho.setId(produto.getId());
			produtoCarrinho.setQtd(1);
			produtoCarrinho.setPreco(produto.getPreco());
			
			listaProdutosEscolhidos.add(produtoCarrinho);
			
			FacesContext.getCurrentInstance().addMessage(null, new 
					FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							produto.getNome() + " adicionado com sucesso ao carrinho"));
		
		} else{
			
			produtoCarrinho = 
					listaProdutosEscolhidos.get(listaProdutosEscolhidos.indexOf(produto));
			
			if(produto.getQtd() > produtoCarrinho.getQtd()){
				
				produtoCarrinho.setQtd(produtoCarrinho.getQtd() + 1);
				
				FacesContext.getCurrentInstance().addMessage(null, new 
						FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
								produto.getNome() + " adicionado com sucesso ao carrinho"));
			
			} else{
				
				FacesContext.getCurrentInstance().addMessage(null, new 
						FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não há " + 
								produtoCarrinho.getNome() + " no estoque."));
			}
		}
	}
	
	public void adicionarMaisUm(Produto produtoCarrinho, Produto produtoLista){
		
		if(produtoCarrinho.getQtd() < produtoLista.getQtd()){
			
			produtoCarrinho.setQtd(produtoCarrinho.getQtd() + 1);
			
			FacesContext.getCurrentInstance().addMessage(null, new 
					FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							produtoCarrinho.getNome() + " adicionado com sucesso ao carrinho"));
			
		} else{
			
			FacesContext.getCurrentInstance().addMessage(null, new 
					FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não há " + 
							produtoCarrinho.getNome() + " no estoque."));
		}
	}
	
	public void remover(Produto produto){
		
		listaProdutosEscolhidos.remove(produto);
	}
	
	public void removerUm(Produto produto){
		
		Integer qtd = produto.getQtd();
		
		Produto p = listaProdutosEscolhidos.get(listaProdutosEscolhidos.indexOf(produto));
		
		if(p.getQtd() > 1){
		
			p.setQtd(qtd - 1);
			
		} else{
			
			remover(produto);
		}	
	}

	@PostConstruct
	public void init(){
		
		listaProdutosEscolhidos = new ArrayList <Produto>();
	}
}