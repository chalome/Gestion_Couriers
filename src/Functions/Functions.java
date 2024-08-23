package Functions;

import BaseDeDonnee.Connections;
import Modeles.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import swing.ImageAvatar;

public class Functions {

    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet res;
    private String imagePath;

    public Functions() {
    }

    public void getImage(ImageAvatar imageLabel) {
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            imageLabel.setImage(icon);

        }
    }

    public int ajouter(Utilisateur utilisateur, String imagePath) {
        int ajouter = 0;
        String sql = "INSERT INTO utilisateur (nom, prenom, service, password, type,photo) VALUES "
                + "(?, ?,(select serviceid from service where servicenom=?),?,"
                + "(select id from typeUtilisateur where nom=?),?)";
        connection = Connections.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getService());
            pst.setString(4, utilisateur.getPassword());
            pst.setString(5, utilisateur.getType());
            if (imagePath != null) {
                File imageFile = new File(imagePath);
                InputStream inputStream = new FileInputStream(imageFile);
                pst.setBlob(6, inputStream);

            } else {
                pst.setNull(6, java.sql.Types.BLOB);
            }
            ajouter = pst.executeUpdate();
            return ajouter;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajouter;
    }

    public int modifier(Utilisateur utilisateur, String imagePath) {
        int modifier = 0;
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, service = (select serviceid from service where servicenom=?), "
                + "password=?, type = (select id from typeUtilisateur where nom=?), photo=? WHERE userID = ?";
        connection = Connections.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getService());
            pst.setString(4, utilisateur.getPassword());
            pst.setString(5, utilisateur.getType());
            if (imagePath != null) {
                File imageFile = new File(imagePath);
                InputStream inputStream = new FileInputStream(imageFile);
                pst.setBlob(6, inputStream);

            } else {
                pst.setNull(6, java.sql.Types.BLOB);
            }
            pst.setInt(7, utilisateur.getUserID());
            modifier = pst.executeUpdate();
            return modifier;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifier;
    }
}
