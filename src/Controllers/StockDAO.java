package Controllers;

import Modeles.Stock;
import Services.ServicesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAO extends ServicesDAO<Stock> {

    @Override
    protected int getId(Stock stock) {
        return stock.getStockID();
    }

    @Override
    protected void setPreparedStatementValues(PreparedStatement pstmt, Stock stock) throws SQLException {
        pstmt.setInt(1, stock.getDossier());
        pstmt.setString(2, stock.getCouleur());
        pstmt.setDate(3, new java.sql.Date(stock.getDate().getTime()));
        pstmt.setString(4, stock.getEtagere());
    }

    @Override
    protected Stock mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Stock(
                rs.getInt("id"),
                rs.getInt("dos"),
                rs.getString("coul"),
                rs.getString("etg"),
                rs.getDate("dt")
        );
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO stock (dossier, couleur, date, etagere) VALUES (?,?,?,?)";
    }

    @Override
    protected String getSelectSQL() {
        return "SELECT * FROM stock WHERE stockID = ?";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE stock SET dossier = ?, couleur=?, date=?,etagere=? WHERE stockID = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM stock WHERE stockID = ?";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT stockID as id,dossier as dos,couleur as coul,date as dt,"
                + "etagere as etg FROM stock";
    }

    @Override
    protected int getParameterIndexForId() {
        return 6; // Assuming the ID is the last parameter in the update statement
    }
    
}
