package DAO;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection conn;

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
}
