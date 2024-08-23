package Controllers;

import Modeles.TypeUtiisateur;
import Services.ServicesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeUtilisateurController extends ServicesDAO<TypeUtiisateur> {

    @Override
    protected int getId(TypeUtiisateur typeUtiisateur) {
        return typeUtiisateur.getTypeID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, TypeUtiisateur typeUtiisateur) throws SQLException {
        pstmt.setString(1, typeUtiisateur.getTypeNom());
    }

    @Override
    protected TypeUtiisateur mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TypeUtiisateur(
                rs.getInt("id"),
                rs.getString("nom")
        );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO typeUtilisateur(nom)values(?)";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM typeUtilisateur";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE typeUtilisateur SET nom=? WHERE id=?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM typeUtilisateur WHERE id=?";
    }

    @Override
    protected String getSelectAllSQL() {

        return "SELECT * FROM typeUtilisateur";
    }

    @Override
    protected int getParameterIndexForId() {
        return 2;
    }

}
