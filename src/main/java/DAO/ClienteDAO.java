package DAO;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ClienteDAO {
    private final Connection conn;

    public ClienteDAO(Connection connection) {
        this.conn = connection;
    }

    public void inserir(Cliente cliente){
        PreparedStatement ps;
        String sql = "INSERT INTO cliente (nome, email, telefone, esta_ativo)" +
                "VALUES (?, ?, ?, ?)";
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.nome());
            ps.setString(2, cliente.email());
            ps.setString(3, cliente.telefone());
            ps.setBoolean(4, true);

            ps.execute();
            conn.commit();

            conn.close();
            ps.close();
        } catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public Set<Cliente> listar (){
        Set<Cliente> clientes = new HashSet<>();
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT * FROM cliente";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                String email = rs.getString(3);
                String telefone = rs.getString(4);
                Boolean esta_ativo = rs.getBoolean(5);
                clientes.add(new Cliente(nome, email, telefone, esta_ativo));
            }

            ps.close();
            rs.close();
            conn.commit();
            conn.close();
        } catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return clientes;
    }
    public Set<Cliente> buscarPorNome(String nomeBusca){
        PreparedStatement ps;
        ResultSet rs;
        Set<Cliente> clientes = new HashSet<>();

        try {
            String sql = "SELECT * FROM cliente WHERE nome = ? AND esta_ativo = TRUE";
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, nomeBusca);
            rs = ps.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                String email = rs.getString(3);
                String telefone = rs.getString(4);
                Boolean esta_ativo = rs.getBoolean(5);

                clientes.add(new Cliente(nome, email, telefone, esta_ativo));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
