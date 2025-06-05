
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;




public class conectaDAO {
    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }
    
    
    /*public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root" ,"C@ir1887");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }*/
    
     public void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root" ,"C@ir1887");
            System.out.println("SUCESSO DE CONEXÃO!");
        } catch (ClassNotFoundException cn) {
            System.out.println("Falha ao conectar com o Banco" + cn);
        } catch (SQLException sql) {
            System.out.println("Erro de SQL: " + sql);
        }
    }
    
    public void desconectar(){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
                System.out.println("DESCONECTAR");
            }
        }catch(SQLException se){
            System.out.println("Problema de Conectar do Banco: " + se);
                
        }
    }
    
}
