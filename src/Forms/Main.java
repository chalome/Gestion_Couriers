package Forms;

import Controllers.DossierDAO;
import Controllers.ServiceController;
import Controllers.StockDAO;
import Controllers.TypeDossierController;
import Controllers.TypeUtilisateurController;
import Controllers.UtilisateurDAO;
import Functions.Functions;
import Functions.Validations;
import Modeles.Dossier;
import Modeles.Service;
import Modeles.Stock;
import Modeles.TypeDossier;
import Modeles.TypeUtiisateur;
import Modeles.Utilisateur;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import enums.*;
import java.awt.Cursor;
import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {

    private String imagePath;

    public Main(Utilisateur utilisateur) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Images/pic2.jpg")).getImage());
        setTitle("Gestion des couriers");
        getAll();
        imageAvatarUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchTxt.setBackground(getBackground());
        session.setText(utilisateur.getPrenom());
        Blob imageBlob = utilisateur.getImage();
        if (imageBlob != null) {
            byte[] imageBytes;
            try {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                ImageIcon icon = new ImageIcon(imageBytes);
                profile.setImage(icon);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    final void getAll() {
        afficherUtilisateur();
        afficherService();
        afficherDossiers();
        combo();
        afficherValeurs();
        afficherStocks();
    }

    void combo() {
        List<Service> list = new ServiceController().list();
        List<TypeUtiisateur> listTpU = new TypeUtilisateurController().list();
        List<TypeDossier> listTpDo = new TypeDossierController().list();
        list.stream().forEach((item) -> serviceCombo.addItem(item));
        list.stream().forEach((item) -> destiCombo.addItem(item));
        listTpU.stream().forEach((categorie) -> {
            categorieCombo.addItem(categorie);
        });
        listTpDo.stream().forEach((type) -> {
            typeTxt.addItem(type);
        });
    }

    void afficherUtilisateur() {
        UtilisateurDAO controleur = new UtilisateurDAO();
        String header[] = {"ID", "NOM", "PRENOM", "SERVICE", "PASSWORD", "TYPE"};
        Object data[][] = new Object[controleur.list().size()][6];
        int i = 0;
        for (Utilisateur utilisateur : controleur.list()) {
            data[i][0] = utilisateur.getUserID();
            data[i][1] = utilisateur.getNom();
            data[i][2] = utilisateur.getPrenom();
            data[i][3] = utilisateur.getService();
            data[i][4] = utilisateur.getPassword();
            data[i][5] = utilisateur.getType();
            i++;
        }
        tableUser.setModel(new DefaultTableModel(data, header));

    }

    void afficherTypes() {
        List<TypeDossier> listTpDo = new TypeDossierController().list();
        listTpDo.stream().forEach((type) -> {
            typeTxt.removeAllItems();
            typeTxt.addItem(type);
        });
    }

    void afficherService() {
        ServiceController controleur = new ServiceController();
        String header[] = {"ID", "SERVICE"};
        Object data[][] = new Object[controleur.list().size()][2];
        int i = 0;
        for (Service service : controleur.list()) {
            data[i][0] = service.getServiceID();
            data[i][1] = service.getServiceNom();
            i++;
        }
        serviceTable.setModel(new DefaultTableModel(data, header));

    }

    void afficherTypeUtilisateur() {
        TypeUtilisateurController controleur = new TypeUtilisateurController();
        String header[] = {"ID", "TYPE"};
        Object data[][] = new Object[controleur.list().size()][2];
        int i = 0;
        for (TypeUtiisateur typeUtiisateur : controleur.list()) {
            data[i][0] = typeUtiisateur.getTypeID();
            data[i][1] = typeUtiisateur.getTypeNom();
            i++;
        }
        serviceTable.setModel(new DefaultTableModel(data, header));

    }

    void afficherDossiers() {
        DossierDAO controleur = new DossierDAO();
        String header[] = {"ID", "TYPE", "REFERENCE", "OBJET", "SOURCE", "DESTINATION", "DATE DOSSIER", "RECU LE", "TRANSFERE"};
        Object data[][] = new Object[controleur.list().size()][9];
        int i = 0;
        for (Dossier dossier : controleur.list()) {
            data[i][0] = dossier.getDossierID();
            data[i][1] = dossier.getDossierType();
            data[i][2] = dossier.getReference();
            data[i][3] = dossier.getObjet();
            data[i][4] = dossier.getSource();
            data[i][5] = dossier.getDestination();
            data[i][6] = dossier.getDateDossier();
            data[i][7] = dossier.getDateRecu();
            data[i][8] = dossier.isTransfere();
            i++;
        }
        tableDossier.setModel(new DefaultTableModel(data, header));

    }

    void afficherStocks() {
        StockDAO controleur = new StockDAO();
        String header[] = {"ID", "DOSSIER", "COULEUR", "DATE", "ETAGERE"};
        Object data[][] = new Object[controleur.list().size()][5];
        int i = 0;
        for (Stock stock : controleur.list()) {
            data[i][0] = stock.getStockID();
            data[i][1] = stock.getDossier();
            data[i][2] = stock.getCouleur();
            data[i][3] = stock.getDate();
            data[i][4] = stock.getEtagere();
            i++;
        }
        tableStock.setModel(new DefaultTableModel(data, header));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profile = new swing.ImageAvatar();
        buttonOutLine1 = new swing.ButtonOutLine();
        labelSplit1 = new swing.LabelSplit();
        buttonOutLine2 = new swing.ButtonOutLine();
        buttonOutLine3 = new swing.ButtonOutLine();
        buttonOutLine4 = new swing.ButtonOutLine();
        buttonOutLine5 = new swing.ButtonOutLine();
        session = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        active = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        ServicePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        serviceTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        addService = new javax.swing.JButton();
        servMod = new javax.swing.JButton();
        servRes = new javax.swing.JButton();
        servSupp = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        typeUSERTXT = new javax.swing.JTextField();
        addtypeuser = new javax.swing.JButton();
        modTypeuser = new javax.swing.JButton();
        suppTypeUser = new javax.swing.JButton();
        restTypeuser = new javax.swing.JButton();
        labelSplit2 = new swing.LabelSplit();
        jLabel22 = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        nomTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        prenomTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        serviceCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        categorieCombo = new javax.swing.JComboBox();
        imageAvatarUser = new swing.ImageAvatar();
        adduserbtn = new javax.swing.JButton();
        updateUserbtn = new javax.swing.JButton();
        deleteuserbtn = new javax.swing.JButton();
        resetuserbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        dossierPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        typeTxt = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        refTxt = new javax.swing.JTextField();
        objetTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        srcTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        destiCombo = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        dateDossier = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        dateRecu = new com.toedter.calendar.JDateChooser();
        addDoss = new javax.swing.JButton();
        modDoss = new javax.swing.JButton();
        restDoss = new javax.swing.JButton();
        suppDoss = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        typedossierTxt = new javax.swing.JTextField();
        addtypD = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDossier = new javax.swing.JTable();
        stockPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        dossierID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        color = new javax.swing.JComboBox<Couleur>();
        jLabel18 = new javax.swing.JLabel();
        dateStock = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        etagere = new javax.swing.JComboBox<Etagere>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableStock = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        searchTxt = new swing.TextFieldAnimation();
        jLabel20 = new javax.swing.JLabel();
        categorieSearch = new javax.swing.JComboBox<Couleur>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });

        buttonOutLine1.setText("Service");
        buttonOutLine1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        buttonOutLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine1ActionPerformed(evt);
            }
        });

        buttonOutLine2.setText("Utilisateur");
        buttonOutLine2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        buttonOutLine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine2ActionPerformed(evt);
            }
        });

        buttonOutLine3.setText("Dossier");
        buttonOutLine3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        buttonOutLine3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine3ActionPerformed(evt);
            }
        });

        buttonOutLine4.setText("Stock");
        buttonOutLine4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        buttonOutLine4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine4ActionPerformed(evt);
            }
        });

        buttonOutLine5.setText("Deconnexion");
        buttonOutLine5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        buttonOutLine5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine5ActionPerformed(evt);
            }
        });

        session.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        session.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonOutLine3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOutLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOutLine2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(buttonOutLine4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOutLine5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(labelSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(session, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(session, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonOutLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonOutLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonOutLine3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonOutLine4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonOutLine5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Application de Gestion des couriers");

        active.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(active, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(active, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        MainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );

        MainPanel.add(Home, "card2");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Service");

        serviceTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        serviceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                serviceTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(serviceTable);

        addService.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        addService.setText("Ajouter");
        addService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServiceActionPerformed(evt);
            }
        });

        servMod.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        servMod.setText("Modifier");
        servMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servModActionPerformed(evt);
            }
        });

        servRes.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        servRes.setText("Reset");
        servRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servResActionPerformed(evt);
            }
        });

        servSupp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        servSupp.setText("Supprimer");
        servSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servSuppActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Categorie");

        typeUSERTXT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        addtypeuser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        addtypeuser.setText("Ajouter");
        addtypeuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtypeuserActionPerformed(evt);
            }
        });

        modTypeuser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        modTypeuser.setText("Modifier");
        modTypeuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modTypeuserActionPerformed(evt);
            }
        });

        suppTypeUser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        suppTypeUser.setText("Supprimer");
        suppTypeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppTypeUserActionPerformed(evt);
            }
        });

        restTypeuser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        restTypeuser.setText("Reset");
        restTypeuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restTypeuserActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Categorie Utilisateur");

        javax.swing.GroupLayout ServicePanelLayout = new javax.swing.GroupLayout(ServicePanel);
        ServicePanel.setLayout(ServicePanelLayout);
        ServicePanelLayout.setHorizontalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(servSupp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(servMod)
                            .addComponent(servRes, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serviceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(suppTypeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addtypeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(modTypeuser)
                            .addComponent(restTypeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeUSERTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelSplit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        ServicePanelLayout.setVerticalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicePanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicePanelLayout.createSequentialGroup()
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serviceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addService, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(servMod, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(servSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(servRes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(labelSplit2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typeUSERTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addtypeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modTypeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(suppTypeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restTypeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        MainPanel.add(ServicePanel, "card3");

        nomTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nomTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomTxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Nom");

        prenomTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        prenomTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomTxtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Prenom");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Service");

        serviceCombo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Password");

        passwordTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Categorie");

        categorieCombo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        categorieCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieComboActionPerformed(evt);
            }
        });

        imageAvatarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatarUserMouseClicked(evt);
            }
        });

        adduserbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        adduserbtn.setText("Ajouter");
        adduserbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adduserbtnActionPerformed(evt);
            }
        });

        updateUserbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        updateUserbtn.setText("Modifier");
        updateUserbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserbtnActionPerformed(evt);
            }
        });

        deleteuserbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        deleteuserbtn.setText("Supprimer");
        deleteuserbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteuserbtnActionPerformed(evt);
            }
        });

        resetuserbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        resetuserbtn.setText("Reset");
        resetuserbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetuserbtnActionPerformed(evt);
            }
        });

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableUserMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adduserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(userPanelLayout.createSequentialGroup()
                            .addComponent(deleteuserbtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(resetuserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateUserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(userPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(passwordTxt))
                                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(userPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(prenomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(userPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(userPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(serviceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(userPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imageAvatarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(categorieCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prenomTxt)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(serviceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(userPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(categorieCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(userPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageAvatarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adduserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateUserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteuserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetuserbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(107, 107, 107))
        );

        MainPanel.add(userPanel, "card4");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Type");

        typeTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Reference");

        refTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        objetTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Objet");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Source");

        srcTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Destinatiom");

        destiCombo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Date Dossier");

        dateDossier.setDateFormatString("yyyy-MM-dd");
        dateDossier.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Recu le");

        dateRecu.setDateFormatString("yyyy-MM-dd");
        dateRecu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        addDoss.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addDoss.setText("Ajouter");
        addDoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDossActionPerformed(evt);
            }
        });

        modDoss.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        modDoss.setText("Modifier");
        modDoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modDossActionPerformed(evt);
            }
        });

        restDoss.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        restDoss.setText("Reset");
        restDoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restDossActionPerformed(evt);
            }
        });

        suppDoss.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        suppDoss.setText("Supprimer");
        suppDoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppDossActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Type");

        typedossierTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        typedossierTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typedossierTxtActionPerformed(evt);
            }
        });

        addtypD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addtypD.setText("Ajouter");
        addtypD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtypDActionPerformed(evt);
            }
        });

        tableDossier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableDossier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableDossierMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableDossier);

        javax.swing.GroupLayout dossierPanelLayout = new javax.swing.GroupLayout(dossierPanel);
        dossierPanel.setLayout(dossierPanelLayout);
        dossierPanelLayout.setHorizontalGroup(
            dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dossierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refTxt))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(objetTxt))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(srcTxt))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(destiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateDossier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateRecu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(addDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(suppDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(restDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typedossierTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dossierPanelLayout.createSequentialGroup()
                        .addComponent(addtypD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        dossierPanelLayout.setVerticalGroup(
            dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dossierPanelLayout.createSequentialGroup()
                .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(refTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(objetTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(srcTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(dateDossier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(suppDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restDoss, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dossierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typedossierTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addtypD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dossierPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        MainPanel.add(dossierPanel, "card5");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Dossier");

        dossierID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dossierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dossierIDActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Date");

        color.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Couleur");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Etagere");

        etagere.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tableStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableStockMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tableStock);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Modifier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Supprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Categorie");

        categorieSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        categorieSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(categorieSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(stockPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dossierID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(stockPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(color, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(stockPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dateStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(stockPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(etagere, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dossierID)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(color))
                        .addGap(25, 25, 25)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(dateStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(etagere))
                        .addGap(28, 28, 28)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(categorieSearch))
                        .addGap(29, 29, 29)
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );

        MainPanel.add(stockPanel, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOutLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine1ActionPerformed
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(ServicePanel);
        MainPanel.repaint();
        MainPanel.revalidate();
        active.setText("Gestion des Services");
    }//GEN-LAST:event_buttonOutLine1ActionPerformed

    private void buttonOutLine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine2ActionPerformed
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(userPanel);
        MainPanel.repaint();
        MainPanel.revalidate();
        active.setText("Gestion des Utilisateurs");
    }//GEN-LAST:event_buttonOutLine2ActionPerformed

    private void buttonOutLine3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine3ActionPerformed
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dossierPanel);
        MainPanel.repaint();
        MainPanel.revalidate();
        active.setText("Gestion des dossiers");
    }//GEN-LAST:event_buttonOutLine3ActionPerformed

    private void buttonOutLine4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine4ActionPerformed
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(stockPanel);
        MainPanel.repaint();
        MainPanel.revalidate();
        active.setText("Gestion des stocks");
    }//GEN-LAST:event_buttonOutLine4ActionPerformed

    private void buttonOutLine5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine5ActionPerformed
        super.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_buttonOutLine5ActionPerformed

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
//        new Functions().getImage(profile);
    }//GEN-LAST:event_profileMouseClicked

    private void nomTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomTxtActionPerformed

    private void prenomTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomTxtActionPerformed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtActionPerformed

    private void categorieComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorieComboActionPerformed

    private void imageAvatarUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatarUserMouseClicked
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            ImageIcon icon = new ImageIcon(imagePath);
            imageAvatarUser.setImage(icon);
        }
    }//GEN-LAST:event_imageAvatarUserMouseClicked

    private void adduserbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adduserbtnActionPerformed
        String nom = nomTxt.getText();
        String prenom = prenomTxt.getText();
        String service = serviceCombo.getSelectedItem().toString();
        String password = passwordTxt.getText();
        String type = categorieCombo.getSelectedItem().toString();
        if (nom.isEmpty() || prenom.isEmpty() || service.isEmpty() || password.isEmpty() || type.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            //int ajouter = new UtilisateurDAO().ajouter(new Utilisateur(nom, prenom, service, password, type));
            int ajouter = new Functions().ajouter(new Utilisateur(nom, prenom, service, password, type), imagePath);
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherUtilisateur();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_adduserbtnActionPerformed

    private void addServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServiceActionPerformed
        String service = serviceTxt.getText();
        if (service.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Les champs est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int ajouter = new ServiceController().ajouter(new Service(service));
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherService();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addServiceActionPerformed

    private void servModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servModActionPerformed
        String service = serviceTxt.getText();
        int ligne = serviceTable.getSelectedRow();
        int id = Integer.parseInt(serviceTable.getValueAt(ligne, 0).toString());
        if (service.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le champs est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la modification?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                int modifier = new ServiceController().modifier(new Service(id, service));
                if (modifier == 1) {
                    JOptionPane.showMessageDialog(this, "Modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherService();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de modification", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_servModActionPerformed

    private void servSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servSuppActionPerformed
        int delete;
        int ligne = serviceTable.getSelectedRow();
        int id = Integer.parseInt(serviceTable.getValueAt(ligne, 0).toString());
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                delete = new ServiceController().supprimer(id);
                if (delete == 1) {
                    JOptionPane.showMessageDialog(this, "Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherService();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de suppression", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_servSuppActionPerformed

    private void serviceTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceTableMouseReleased
        int ligne = serviceTable.getSelectedRow();
        serviceTxt.setText(serviceTable.getValueAt(ligne, 1).toString());
    }//GEN-LAST:event_serviceTableMouseReleased

    private void updateUserbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserbtnActionPerformed
        String nom = nomTxt.getText();
        String prenom = prenomTxt.getText();
        String service = serviceCombo.getSelectedItem().toString();
        String password = passwordTxt.getText();
        String type = categorieCombo.getSelectedItem().toString();
        int ligne = tableUser.getSelectedRow();
        int id = Integer.parseInt(tableUser.getValueAt(ligne, 0).toString());
        if (nom.isEmpty() || prenom.isEmpty() || service.isEmpty() || password.isEmpty() || type.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                int modifier = new Functions().modifier(new Utilisateur(id, nom, prenom, service, password, type), imagePath);
                if (modifier == 1) {
                    JOptionPane.showMessageDialog(this, "Modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherUtilisateur();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de modification", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_updateUserbtnActionPerformed

    private void deleteuserbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteuserbtnActionPerformed
        int delete;
        int ligne = tableUser.getSelectedRow();
        int id = Integer.parseInt(tableUser.getValueAt(ligne, 0).toString());
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                delete = new UtilisateurDAO().supprimer(id);
                if (delete == 1) {
                    JOptionPane.showMessageDialog(this, "Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherService();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de suppression", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_deleteuserbtnActionPerformed

    private void resetuserbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetuserbtnActionPerformed
        nomTxt.setText("");
        prenomTxt.setText("");
        serviceCombo.setSelectedItem(null);
        passwordTxt.setText("");
        categorieCombo.setSelectedItem(null);
    }//GEN-LAST:event_resetuserbtnActionPerformed

    private void tableUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseReleased
        int ligne = tableUser.getSelectedRow();
        nomTxt.setText(tableUser.getValueAt(ligne, 1).toString());
        prenomTxt.setText(tableUser.getValueAt(ligne, 2).toString());
        serviceCombo.setSelectedItem(tableUser.getValueAt(ligne, 3).toString());
        passwordTxt.setText(tableUser.getValueAt(ligne, 4).toString());
        categorieCombo.setSelectedItem(tableUser.getValueAt(ligne, 5).toString());
    }//GEN-LAST:event_tableUserMouseReleased

    private void servResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servResActionPerformed
        serviceTxt.setText("");
    }//GEN-LAST:event_servResActionPerformed

    private void addtypDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtypDActionPerformed
        String type = typedossierTxt.getText();
        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le champs est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int ajouter = new TypeDossierController().ajouter(new TypeDossier(type));
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherTypes();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addtypDActionPerformed

    private void addDossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDossActionPerformed
        String type = typeTxt.getSelectedItem().toString();
        String reference = refTxt.getText();
        String objet = objetTxt.getText();
        String source = srcTxt.getText();
        String destination = destiCombo.getSelectedItem().toString();
        Date datedossier = dateDossier.getDate();
        Date dateRec = dateRecu.getDate();
        if (reference.isEmpty() || objet.isEmpty() || source.isEmpty() || destination.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int ajouter = new DossierDAO().ajouter(new Dossier(type, datedossier, dateRec, reference, objet, source, destination));
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherDossiers();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addDossActionPerformed

    private void modDossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modDossActionPerformed
        String type = typeTxt.getSelectedItem().toString();
        String reference = refTxt.getText();
        String objet = objetTxt.getText();
        String source = srcTxt.getText();
        String destination = destiCombo.getSelectedItem().toString();
        Date datedossier = dateDossier.getDate();
        Date dateRec = dateRecu.getDate();
        int ligne = tableDossier.getSelectedRow();
        int id = Integer.parseInt(tableDossier.getValueAt(ligne, 0).toString());
        if (reference.isEmpty() || objet.isEmpty() || source.isEmpty() || destination.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                int modifier = new DossierDAO().modifier(new Dossier(id, type, datedossier, dateRec, reference, objet, source, destination));
                if (modifier == 1) {
                    JOptionPane.showMessageDialog(this, "Modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherDossiers();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de modification", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_modDossActionPerformed

    private void suppDossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppDossActionPerformed
        int delete;
        int ligne = tableDossier.getSelectedRow();
        int id = Integer.parseInt(tableDossier.getValueAt(ligne, 0).toString());
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                delete = new DossierDAO().supprimer(id);
                if (delete == 1) {
                    JOptionPane.showMessageDialog(this, "Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherDossiers();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de suppression", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_suppDossActionPerformed

    private void restDossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restDossActionPerformed
        refTxt.setText("");
        objetTxt.setText("");
        srcTxt.setText("");
        destiCombo.setSelectedItem(null);
        dateDossier.setDate(null);
        dateRecu.setDate(null);
        typeTxt.setSelectedItem(null);
    }//GEN-LAST:event_restDossActionPerformed

    private void tableDossierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDossierMouseReleased
        int ligne = tableDossier.getSelectedRow();
        refTxt.setText(tableDossier.getValueAt(ligne, 2).toString());
        objetTxt.setText(tableDossier.getValueAt(ligne, 3).toString());
        typeTxt.setSelectedItem(tableDossier.getValueAt(ligne, 1).toString());
        srcTxt.setText(tableDossier.getValueAt(ligne, 4).toString());
        destiCombo.setSelectedItem(tableDossier.getValueAt(ligne, 5).toString());
        JTextField date1 = (JTextField) dateDossier.getDateEditor().getUiComponent();
        date1.setText(tableDossier.getValueAt(ligne, 6).toString());
        JTextField date2 = (JTextField) dateRecu.getDateEditor().getUiComponent();
        date2.setText(tableDossier.getValueAt(ligne, 7).toString());
    }//GEN-LAST:event_tableDossierMouseReleased

    private void typedossierTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typedossierTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typedossierTxtActionPerformed

    private void dossierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dossierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dossierIDActionPerformed

    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorActionPerformed

    private void categorieSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorieSearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dossier = Integer.parseInt(dossierID.getText());
        String couleur = color.getSelectedItem().toString();
        Date date = dateStock.getDate();
        String etageres = etagere.getSelectedItem().toString();
        if (couleur.isEmpty() || etageres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (!Validations.dossierExist(dossierID.getText())) {
            JOptionPane.showMessageDialog(this, "Le dossier n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (Validations.stockExist(dossierID.getText())) {
            JOptionPane.showMessageDialog(this, "Le dossier deja en stock", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int ajouter = new StockDAO().ajouter(new Stock(dossier, couleur, etageres, date));
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherStocks();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dossier = Integer.parseInt(dossierID.getText());
        String couleur = color.getSelectedItem().toString();
        Date date = dateStock.getDate();
        String etageres = etagere.getSelectedItem().toString();
        int ligne = tableStock.getSelectedRow();
        int id = Integer.parseInt(tableStock.getValueAt(ligne, 0).toString());
        if (couleur.isEmpty() || etageres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                int modifier = new StockDAO().modifier(new Stock(id, dossier, couleur, etageres, date));
                if (modifier == 1) {
                    JOptionPane.showMessageDialog(this, "Modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherDossiers();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de modification", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int delete;
        int ligne = tableStock.getSelectedRow();
        int id = Integer.parseInt(tableStock.getValueAt(ligne, 0).toString());
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                delete = new StockDAO().supprimer(id);
                if (delete == 1) {
                    JOptionPane.showMessageDialog(this, "Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherDossiers();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de suppression", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dossierID.setText("");
        color.setSelectedItem(null);
        dateStock.setDate(null);
        etagere.setSelectedItem(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableStockMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockMouseReleased
        int ligne = tableStock.getSelectedRow();
        dossierID.setText(tableStock.getValueAt(ligne, 1).toString());
        color.setSelectedItem(tableStock.getValueAt(ligne, 2).toString());
        JTextField date1 = (JTextField) dateStock.getDateEditor().getUiComponent();
        date1.setText(tableStock.getValueAt(ligne, 3).toString());
        etagere.setSelectedItem(tableStock.getValueAt(ligne, 4).toString());
    }//GEN-LAST:event_tableStockMouseReleased

    private void addtypeuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtypeuserActionPerformed
        String type = typeUSERTXT.getText();
        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Les champs est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            int ajouter = new TypeUtilisateurController().ajouter(new TypeUtiisateur(type));
            if (ajouter == 1) {
                JOptionPane.showMessageDialog(this, "Enregistrement Reussi", "Succes", JOptionPane.INFORMATION_MESSAGE);
                afficherTypeUtilisateur();
            } else {
                JOptionPane.showMessageDialog(this, "Echec d'enregistrement", "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addtypeuserActionPerformed

    private void modTypeuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modTypeuserActionPerformed
        String type = typeUSERTXT.getText();
        int ligne = serviceTable.getSelectedRow();
        int id = Integer.parseInt(serviceTable.getValueAt(ligne, 0).toString());
        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le champs est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la modification?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                int modifier = new TypeUtilisateurController().modifier(new TypeUtiisateur(id, type));
                if (modifier == 1) {
                    JOptionPane.showMessageDialog(this, "Modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherTypeUtilisateur();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de modification", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_modTypeuserActionPerformed

    private void suppTypeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppTypeUserActionPerformed
        int delete;
        int ligne = serviceTable.getSelectedRow();
        int id = Integer.parseInt(serviceTable.getValueAt(ligne, 0).toString());
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez la ligne a modifier ", "Erreur!", JOptionPane.ERROR_MESSAGE);
        } else {
            int y = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiement faire la suppression?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (y == JOptionPane.OK_OPTION) {
                delete = new TypeUtilisateurController().supprimer(id);
                if (delete == 1) {
                    JOptionPane.showMessageDialog(this, "Supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    afficherService();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de suppression", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_suppTypeUserActionPerformed

    private void restTypeuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restTypeuserActionPerformed
        typeUSERTXT.setText("");
    }//GEN-LAST:event_restTypeuserActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatArcDarkIJTheme.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(null).setVisible(true);
            }
        });
    }

    private void afficherValeurs() {
        for (Couleur couleur : Couleur.values()) {
            color.addItem(couleur);
        }
        for (Etagere etager : Etagere.values()) {
            etagere.addItem(etager);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel ServicePanel;
    private javax.swing.JLabel active;
    private javax.swing.JButton addDoss;
    private javax.swing.JButton addService;
    private javax.swing.JButton addtypD;
    private javax.swing.JButton addtypeuser;
    private javax.swing.JButton adduserbtn;
    private swing.ButtonOutLine buttonOutLine1;
    private swing.ButtonOutLine buttonOutLine2;
    private swing.ButtonOutLine buttonOutLine3;
    private swing.ButtonOutLine buttonOutLine4;
    private swing.ButtonOutLine buttonOutLine5;
    private javax.swing.JComboBox categorieCombo;
    private javax.swing.JComboBox<Couleur> categorieSearch;
    private javax.swing.JComboBox<Couleur> color;
    private com.toedter.calendar.JDateChooser dateDossier;
    private com.toedter.calendar.JDateChooser dateRecu;
    private com.toedter.calendar.JDateChooser dateStock;
    private javax.swing.JButton deleteuserbtn;
    private javax.swing.JComboBox destiCombo;
    private javax.swing.JTextField dossierID;
    private javax.swing.JPanel dossierPanel;
    private javax.swing.JComboBox<Etagere> etagere;
    private swing.ImageAvatar imageAvatarUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private swing.LabelSplit labelSplit1;
    private swing.LabelSplit labelSplit2;
    private javax.swing.JButton modDoss;
    private javax.swing.JButton modTypeuser;
    private javax.swing.JTextField nomTxt;
    private javax.swing.JTextField objetTxt;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField prenomTxt;
    private swing.ImageAvatar profile;
    private javax.swing.JTextField refTxt;
    private javax.swing.JButton resetuserbtn;
    private javax.swing.JButton restDoss;
    private javax.swing.JButton restTypeuser;
    private swing.TextFieldAnimation searchTxt;
    private javax.swing.JButton servMod;
    private javax.swing.JButton servRes;
    private javax.swing.JButton servSupp;
    private javax.swing.JComboBox serviceCombo;
    private javax.swing.JTable serviceTable;
    private javax.swing.JTextField serviceTxt;
    private javax.swing.JLabel session;
    private javax.swing.JTextField srcTxt;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JButton suppDoss;
    private javax.swing.JButton suppTypeUser;
    private javax.swing.JTable tableDossier;
    private javax.swing.JTable tableStock;
    private javax.swing.JTable tableUser;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JComboBox typeTxt;
    private javax.swing.JTextField typeUSERTXT;
    private javax.swing.JTextField typedossierTxt;
    private javax.swing.JButton updateUserbtn;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
