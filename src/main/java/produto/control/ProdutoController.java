package produto.control;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import produto.model.Produto;
import produto.model.ProdutoDAO;

@Named
@ViewScoped
public class ProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Produto produto;
	
	private List <Produto> listaProdutos;

	public List <Produto> getListaProdutos(){
		
		return this.listaProdutos;
	}

	public void setListaProdutos(List <Produto> listaProdutos){
		this.listaProdutos = listaProdutos;
	}

	public Produto getProduto(){
		return produto;
	}
	
	public Produto buscarPorId(Produto produto){
		
		ProdutoDAO dao = new ProdutoDAO();
		
		return dao.buscarPorId(produto.getId());
	}
	
	public void setProduto(Produto produto){
		this.produto = produto;
	}
	
	public void buscarPorNome(){
		
		ProdutoDAO dao = new ProdutoDAO();
		
		this.listaProdutos = dao.buscarPorNome(this.produto.getNome());
	}
	
	public void remover(Produto produto){
		
		long idProduto = 
				produto.getId();

		ProdutoDAO dao = 
				new ProdutoDAO();

		listaProdutos.remove(produto);

		dao.excluir(idProduto);
	}
	
	public void alterar(Produto produto){
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.alterar(produto);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Produto alterado com sucesso"));
	}
	
	public void adicionar(){
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.inserir(this.produto);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Produto inserido com sucesso"));
	}
	
	
	@PostConstruct
	public void init(){
		produto = new Produto();
	}
}