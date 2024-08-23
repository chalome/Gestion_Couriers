package Controllers;

import Modeles.Utilisateur;
import Services.ServicesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO extends ServicesDAO<Utilisateur> {

    @Override
    protected int getId(Utilisateur utilisateur) {
        return utilisateur.getUserID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, Utilisateur utilisateur) throws SQLException {
        pstmt.setString(1, utilisateur.getNom());
        pstmt.setString(2, utilisateur.getPrenom());
        pstmt.setString(3, utilisateur.getService());
        pstmt.setString(4, utilisateur.getPassword());
        pstmt.setString(5, utilisateur.getType());
    }

    @Override
    protected Utilisateur mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Utilisateur(
                rs.getInt("id"),
                rs.getString("n"),
                rs.getString("p"),
                rs.getString("serv"),
                rs.getString("pwd"),
                rs.getString("cat")
        );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO utilisateur (nom, prenom, service, password, type) VALUES (?, ?,(select serviceid from service where servicenom=?),?,(select id from typeUtilisateur where nom=?))";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM utilisateur WHERE userID = ?";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE utilisateur SET nom = ?, prenom = ?, service = (select id from service where nom=?), password=?, type = (select id from typeUtilisateur where nom=?) WHERE userID = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM utilisateur WHERE userID = ?";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT userid as id,utilisateur.nom as n,prenom as p,service.servicenom as serv,password as pwd,typeUtilisateur.nom as cat FROM utilisateur,typeUtilisateur,service where utilisateur.service=service.serviceid and typeUtilisateur.id=utilisateur.type";
    }

    @Override
    protected int getParameterIndexForId() {
        return 6; // Assuming the ID is the last parameter in the update statement
    }

}
