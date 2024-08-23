package Controllers;

import Modeles.Dossier;
import Services.ServicesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DossierDAO extends ServicesDAO<Dossier> {

    @Override
    protected int getId(Dossier dossier) {
        return dossier.getDossierID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, Dossier dossier) throws SQLException {
        pstmt.setString(1, dossier.getDossierType());
        pstmt.setDate(2, new java.sql.Date(dossier.getDateDossier().getTime()));
        pstmt.setDate(3, new java.sql.Date(dossier.getDateRecu().getTime()));
        pstmt.setString(4, dossier.getReference());
        pstmt.setString(5, dossier.getObjet());
        pstmt.setString(6, dossier.getSource());
        pstmt.setString(7, dossier.getDestination());
    }

    @Override
    protected Dossier mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Dossier(
                rs.getInt("id"),
                rs.getString("dossier"),
                rs.getDate("date1"),
                rs.getDate("date2"),
                rs.getString("ref"),
                rs.getString("obj"),
                rs.getString("src"),
                rs.getString("dest"),
                rs.getBoolean("trans")
    );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO dossier (typedossier, date_sur_piece, date_recu, reference, objet,source,destination)"
                + " VALUES ((select id from typedossier where nom=?), ?,?,?,?,?,"
                + "(select serviceid from service where servicenom=?))";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM dossier WHERE dossierID = ?";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE dossier SET typedossier=(select id from typedossier where nom=?), date_sur_piece=?, date_recu=?,"
                + " reference=?, objet=?,source=?,destination=(select serviceid from service where servicenom=?)"
                + " WHERE dossierID=?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM dossier WHERE dossierID = ?";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT dossier.dossierId as id,typedossier.nom as dossier,dossier.date_sur_piece as date1,"
                + "dossier.date_recu as date2,dossier.reference as ref,dossier.objet as obj,source as src,"
                + "service.serviceNom as dest,dossier.transfere as trans FROM service,typeDossier,"
                + "dossier where dossier.typeDossier=typedossier.id"
                + " and dossier.destination=service.serviceid";
    }

    @Override
    protected int getParameterIndexForId() {
        return 8; // Assuming the ID is the last parameter in the update statement
    }
}
