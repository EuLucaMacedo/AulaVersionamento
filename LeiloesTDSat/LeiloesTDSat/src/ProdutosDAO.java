import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public static boolean cadastrarProduto(ProdutosDTO p) {
        String sql = "INSERT INTO Produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conectaDAO conectar = new conectaDAO();
            conectar.conectar();

            PreparedStatement ps = conectar.getConexao().prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setString(3, p.getStatus());

            int AtualizacaoTabela = ps.executeUpdate();
            System.out.println("Produto Cadastrado com Sucesso!");
            conectar.desconectar();
            return AtualizacaoTabela > 0;

        } catch (SQLException se) {
            System.out.println("Erro ao Cadastrar Produto: " + se.getMessage());
            return false;

        }

    }
    
    public static List<ProdutosDTO> listarTodos(){
        List<ProdutosDTO> pro = new ArrayList();
        
        try{
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "SELECT p.id, p.nome, p.valor, p.status "
                    + "FROM Produtos p";
            
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                
                
                pro.add(p);
                
            }
            
            conexao.desconectar();
            
        }catch(SQLException se){
            System.err.println("Erro ao Listar Animais: " + se.getMessage());
        }
        
        return pro;
    }
    
    public boolean venderProduto(int idProduto) {
        conectaDAO conecta = new conectaDAO(); 
        Connection conn = null;

        try {
            conecta.conectar(); 
            conn = conecta.getConexao();

            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, "Vendido");
            stmt.setInt(2, idProduto);

            int linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conecta.desconectar(); // encerra a conexão

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
        


