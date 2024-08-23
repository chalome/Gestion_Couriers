package Controllers;

import Modeles.TypeDossier;
import Services.ServicesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDossierController extends ServicesDAO<TypeDossier> {

    @Override
    protected int getId(TypeDossier typeDossier) {
        return typeDossier.getTypeID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, TypeDossier typeDossier) throws SQLException {
        pstmt.setString(1, typeDossier.getTypeNom());
    }

    @Override
    protected TypeDossier mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TypeDossier(
                rs.getInt("id"),
                rs.getString("nom")
        );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO typeDossier(nom)values(?)";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM typeDossier";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE service SET nom=? WHERE id=?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM typeDossier WHERE id=?";
    }

    @Override
    protected String getSelectAllSQL() {

        return "SELECT * FROM typeDossier";
    }

    @Override
    protected int getParameterIndexForId() {
        return 2;
    }

}
