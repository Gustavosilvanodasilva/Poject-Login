package poject.login.core.dao;

import poject.login.core.dao.conexao.ConexaoBD;
import poject.login.core.entity.CadastroUsuario;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import poject.login.view.Login;

public class CadastroBD {

    public void inserir(CadastroUsuario cadastrousuario) {
        String sql = "INSERT INTO usuario(nome, login, senha, email) Values(?,?,?,?) ";
        PreparedStatement ps;
        try {
            ps = ConexaoBD.getConexao().prepareStatement(sql);
            ps.setString(1, cadastrousuario.getNome());
            ps.setString(2, cadastrousuario.getLogin());
            ps.setString(3, cadastrousuario.getSenha());
            ps.setString(4, cadastrousuario.getEmail());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void buscarUsuario(CadastroUsuario cadastrousuario) {
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id, nome, login, senha, email FROM usuario WHERE login = ? AND senha = ?";

        try {
            conexao = ConexaoBD.getConexao();

            ps = conexao.prepareStatement(sql);
            ps.setString(1, cadastrousuario.getLogin());
            ps.setString(2, cadastrousuario.getSenha());
            rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Acesso Permitido");
                
            } else {
                JOptionPane.showMessageDialog(null, "Acesso Negado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro na consulta ao banco de dados: " + e.getMessage());
        }

    }

}
