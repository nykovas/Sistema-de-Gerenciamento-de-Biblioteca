package DAO;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<Cliente> listar (){
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT * FROM cliente WHERE esta_ativo = TRUE ORDER BY id ASC";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                String email = rs.getString(3);
                String telefone = rs.getString(4);
                Boolean esta_ativo = rs.getBoolean(5);
                clientes.add(new Cliente(id, nome, email, telefone, esta_ativo));
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
    public List<Cliente> buscarPorId(Integer idBusca){
        PreparedStatement ps;
        ResultSet rs;
        List<Cliente> clientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente WHERE id = ? AND esta_ativo = TRUE";
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idBusca);
            rs = ps.executeQuery();

            while (rs.next()){
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                String email = rs.getString(3);
                String telefone = rs.getString(4);
                Boolean esta_ativo = rs.getBoolean(5);

                clientes.add(new Cliente(id, nome, email, telefone, esta_ativo));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public void atualizar(Cliente cliente, Integer id){
        PreparedStatement ps;
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.nome());
            ps.setString(2, cliente.email());
            ps.setString(3, cliente.telefone());
            ps.setInt(4, id);

            ps.executeUpdate();

            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void desligarCliente(Integer id){
        PreparedStatement ps;
        String sql = "UPDATE cliente SET esta_ativo = FALSE WHERE id = ?";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

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
}
