import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
    
}
        


