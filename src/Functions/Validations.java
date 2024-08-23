package Functions;

import static BaseDeDonnee.Connections.getConnection;
import Modeles.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validations {

    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet res;

    public Validations() {
    }

    public static boolean isLogin(Utilisateur utilisateur) {
        connection = getConnection();
        String sql = "SELECT * FROM  utilisateur  where prenom=? and password=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, utilisateur.getPrenom());
            pst.setString(2, utilisateur.getPassword());
            res = pst.executeQuery();
            while (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean dossierExist(String id) {
        connection = getConnection();
        String sql = "SELECT * FROM  dossier  where dossierID=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, id);
            res = pst.executeQuery();
            while (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean stockExist(String dossier) {
        connection = getConnection();
        String sql = "SELECT * FROM  stock  where dossier=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, dossier);
            res = pst.executeQuery();
            while (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
