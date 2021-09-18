package br.com.servicotecnico2.telas;
//Pacotes para auxiliar os metedos do classe.
import br.com.servicotecnico2.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    //Variaveis para ajudar na verificação de usuários no sistema.
    private Connection conn = null;
    private final String conectado = "Conectado!";
    private final String desconectado = "Não conectou!";
    private PreparedStatement st = null;
    private ResultSet rs = null;

    //Metodo construtor da classe com parametros.
    public TelaLogin() {
        initComponents();
        //Chama classe conexao para poder conectar o programa ao banco de dados.
        conn = Conexao.abrirBanco();
        
        /*Desvio condicional para exboir na tela login se o program etsá cocnectado ao 
        banco ou não*/
        if (conn != null) {
            LblStatus2.setText(conectado);
            LblStatus2.setForeground(new java.awt.Color(102, 255, 0));
        } else {
            LblStatus2.setText(desconectado);
            LblStatus2.setForeground(new java.awt.Color(255, 51, 51));
        }
    }

    //Metodo para verificação de usuários.
    public void Logar() {
        String comando = "select * from usuarios where login = ? and senha = ?;";

        try {
            //Variaveis para auxiliar o metedo na verificação
            st = conn.prepareStatement(comando);
            st.setString(1, TxtUsuario.getText());
            st.setString(2, JpSenha.getText());
            rs = st.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(6);

                if (perfil.equals("Admin")) {
                    //Se for administrador
                    TelaPrincipal tp = new TelaPrincipal();
                    tp.setVisible(true);
                    TelaPrincipal.MenuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuUsuario.setEnabled(true);
                    TelaPrincipal.LblUsuario2.setText(rs.getString(2));
                    TelaPrincipal.LblUsuario2.setForeground(new java.awt.Color(255, 255, 0));
                    this.dispose();

                } else {
                    //Se fro usuário comum.
                    TelaPrincipal tp = new TelaPrincipal();
                    TelaPrincipal.LblUsuario2.setText(rs.getString(2));
                    tp.setVisible(true);
                    this.dispose();

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha Inválidos!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LblUsuario = new javax.swing.JLabel();
        TxtUsuario = new javax.swing.JTextField();
        LblSenha = new javax.swing.JLabel();
        JpSenha = new javax.swing.JPasswordField();
        LblStatus = new javax.swing.JLabel();
        LblStatus2 = new javax.swing.JLabel();
        BtnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(14, 11, 22));

        LblUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblUsuario.setForeground(new java.awt.Color(162, 57, 202));
        LblUsuario.setText("Usuário");

        TxtUsuario.setBackground(new java.awt.Color(231, 223, 221));
        TxtUsuario.setForeground(new java.awt.Color(0, 0, 0));

        LblSenha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblSenha.setForeground(new java.awt.Color(162, 57, 202));
        LblSenha.setText("Senha");

        JpSenha.setBackground(new java.awt.Color(231, 223, 221));
        JpSenha.setForeground(new java.awt.Color(0, 0, 0));

        LblStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblStatus.setForeground(new java.awt.Color(162, 57, 202));
        LblStatus.setText("Status:");

        LblStatus2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblStatus2.setForeground(new java.awt.Color(255, 255, 255));
        LblStatus2.setText("---------");

        BtnLogin.setBackground(new java.awt.Color(71, 20, 246));
        BtnLogin.setForeground(new java.awt.Color(0, 0, 0));
        BtnLogin.setText("Login");
        BtnLogin.setToolTipText("Clique no botão para logar.");
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblUsuario)
                    .addComponent(LblSenha)
                    .addComponent(LblStatus))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnLogin))
                    .addComponent(TxtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(JpSenha, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblUsuario))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSenha))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblStatus)
                    .addComponent(LblStatus2)
                    .addComponent(BtnLogin))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        Logar();
    }//GEN-LAST:event_BtnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JPasswordField JpSenha;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JLabel LblStatus;
    private javax.swing.JLabel LblStatus2;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
