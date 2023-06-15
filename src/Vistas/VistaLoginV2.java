package Vistas;

import Controlador.CtrlLogin;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class VistaLoginV2 extends javax.swing.JFrame {
    
    CardLayout cardLayout;

    public VistaLoginV2() {
        initComponents();
        //setLocationRelativeTo(null);
        //setSize(566,340);
        
        cardLayout = (CardLayout)(panelCards.getLayout());
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCards = new javax.swing.JPanel();
        PanelIngreso = new javax.swing.JPanel();
        btnEntrarP1 = new javax.swing.JButton();
        btnRegistrarseP1 = new javax.swing.JButton();
        pdwUsuario = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PanelRegistro = new javax.swing.JPanel();
        pdwUsuarioP2 = new javax.swing.JPasswordField();
        btnEntrarP2 = new javax.swing.JButton();
        btnRegistrarseP2 = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        txtUsuario1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCards.setLayout(new java.awt.CardLayout());

        PanelIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelIngreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEntrarP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/entrar.png"))); // NOI18N
        btnEntrarP1.setContentAreaFilled(false);
        btnEntrarP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarP1ActionPerformed(evt);
            }
        });
        PanelIngreso.add(btnEntrarP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 40, 40));

        btnRegistrarseP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/registrar.png"))); // NOI18N
        btnRegistrarseP1.setContentAreaFilled(false);
        btnRegistrarseP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseP1ActionPerformed(evt);
            }
        });
        PanelIngreso.add(btnRegistrarseP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 40, 40));
        PanelIngreso.add(pdwUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 170, 30));

        btnIngresar.setBackground(new java.awt.Color(255, 255, 255));
        btnIngresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 153));
        btnIngresar.setText("Ingresar.");
        btnIngresar.setBorder(null);
        PanelIngreso.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 140, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña:");
        PanelIngreso.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));
        PanelIngreso.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 170, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario:");
        PanelIngreso.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginWelcome.png"))); // NOI18N
        PanelIngreso.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 110, 120));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginBackground1.jpg"))); // NOI18N
        PanelIngreso.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        panelCards.add(PanelIngreso, "card2");

        PanelRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        PanelRegistro.add(pdwUsuarioP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 170, 30));

        btnEntrarP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/entrar.png"))); // NOI18N
        btnEntrarP2.setContentAreaFilled(false);
        PanelRegistro.add(btnEntrarP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 40, 40));

        btnRegistrarseP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/registrar.png"))); // NOI18N
        btnRegistrarseP2.setContentAreaFilled(false);
        PanelRegistro.add(btnRegistrarseP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 40, 40));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(0, 0, 153));
        btnRegistrar.setText("Registrar.");
        PanelRegistro.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 140, 30));
        PanelRegistro.add(txtUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 170, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contraseña:");
        PanelRegistro.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id:");
        PanelRegistro.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginWelcome.png"))); // NOI18N
        PanelRegistro.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 110, 120));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginBackground1.jpg"))); // NOI18N
        PanelRegistro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        panelCards.add(PanelRegistro, "card3");

        getContentPane().add(panelCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEntrarP1ActionPerformed

    private void btnRegistrarseP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseP1ActionPerformed
        /*panelCards.removeAll();
        panelCards.add(PanelRegistro);
        panelCards.repaint();
        panelCards.revalidate();*/
    }//GEN-LAST:event_btnRegistrarseP1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(VistaLoginV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaLoginV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaLoginV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaLoginV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaLoginV2().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelIngreso;
    public javax.swing.JPanel PanelRegistro;
    public javax.swing.JButton btnEntrarP1;
    public javax.swing.JButton btnEntrarP2;
    public javax.swing.JButton btnIngresar;
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JButton btnRegistrarseP1;
    public javax.swing.JButton btnRegistrarseP2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel panelCards;
    public javax.swing.JPasswordField pdwUsuario;
    public javax.swing.JPasswordField pdwUsuarioP2;
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JTextField txtUsuario1;
    // End of variables declaration//GEN-END:variables
}
