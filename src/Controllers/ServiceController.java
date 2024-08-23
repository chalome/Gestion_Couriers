package Controllers;

import Modeles.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceController extends Services.ServicesDAO<Service> {

    @Override
    protected int getId(Service service) {
        return service.getServiceID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, Service service) throws SQLException {
        pstmt.setString(1, service.getServiceNom());
    }

    @Override
    protected Service mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Service(
                rs.getInt("serviceID"),
                rs.getString("serviceNom")
        );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO service(serviceNom)values(?)";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM service";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE service SET serviceNom=? WHERE serviceID=?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM service WHERE serviceID=?";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT * FROM service";
    }

    @Override
    protected int getParameterIndexForId() {
        return 2;
    }

}
