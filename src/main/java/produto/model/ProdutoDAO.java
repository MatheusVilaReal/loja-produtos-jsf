package produto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO{
	
	private Connection connection;
	
	public ProdutoDAO(){
		
		try{
			
			this.connection = new ConnectionFactory().getConnection();
		
		} catch(SQLException e){
			
			throw new RuntimeException(e);
		}
	}
	
	public void inserir(Produto produto){
		
		String sql = "INSERT INTO produto(NOME, QUANTIDADE, PRECO) VALUES(?, ?, ?)";
		
		try{
		
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, produto.getNome());
			prstate.setInt(2, produto.getQtd());
			prstate.setDouble(3, produto.getPreco());
			
			prstate.execute();
			
			prstate.close();
			
		} catch(SQLException e){
			
			throw new RuntimeException(e);
		
		} finally{
			
			try{
				
				connection.close();
				
			} catch(SQLException e){
			
				throw new RuntimeException(e);
			}
		}
	}
	
	public void excluir(Long id){
		
		String sql = "DELETE FROM produto WHERE ID=?";
		
		try{
		
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setLong(1, id);
			
			prstate.execute();
			
			prstate.close();
		
		} catch(SQLException e){

			throw new RuntimeException(e);
		} 
	}
	
	public void alterar(Produto produto){
		
		String sql = "UPDATE produto SET NOME=?, QUANTIDADE=?, PRECO=? WHERE ID=?";
		
		try{
			
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, produto.getNome());
			prstate.setInt(2, produto.getQtd());
			prstate.setDouble(3, produto.getPreco());
			prstate.setLong(4, produto.getId());
			
			prstate.execute();
			
			prstate.close();
			
		} catch(SQLException e){

			throw new RuntimeException(e);

		} finally{
		
			try{
			
				connection.close();
			
			} catch(SQLException e){
			
				throw new RuntimeException(e);
			}
		}
	}
	
	public List<Produto> buscarPorNome(String nome){
		
		String sql = "SELECT * FROM produto WHERE NOME LIKE UPPER(?)";
		
		List<Produto> produtos = new ArrayList<Produto>();

		try{
			
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, new String("%" + nome + "%").toUpperCase());
					
			ResultSet resultado = 
					prstate.executeQuery();
			
			while(resultado.next()){
				
				Produto produto = new Produto();
				produto.setId(resultado.getLong("ID"));
				produto.setNome(resultado.getString("NOME"));
				produto.setPreco(resultado.getDouble("PRECO"));
				produto.setQtd(resultado.getInt("QUANTIDADE"));
				
				produtos.add(produto);
			}
			
			resultado.close();
			prstate.close();
			
		} catch(SQLException e){
			
			throw new RuntimeException(e);
		
		} finally{
		
			try{
				
				connection.close();
				
			} catch(SQLException e){

				throw new RuntimeException(e);
			}
		}
		
		return produtos;	
	}
	
	public Produto buscarPorId(Long id){
		
		String sql = "SELECT * FROM produto WHERE ID = ?";
		
		Produto produto = new Produto();
		
		try{
		
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setLong(1, id);
					
			ResultSet resultado = prstate.executeQuery();
			
			if(resultado.next() == false)
				return null;
			
			produto.setId(resultado.getLong("ID"));
			produto.setNome(resultado.getString("NOME"));
			produto.setPreco(resultado.getDouble("PRECO"));
			produto.setQtd(resultado.getInt("QUANTIDADE"));
			
			resultado.close();
			prstate.close();
			
		} catch(SQLException e){

			throw new RuntimeException(e);
			
		} finally{
			
			try{
			
				connection.close();
			
			} catch(SQLException e){

				throw new RuntimeException(e);
			}
		}
		
		return produto;
	}
}