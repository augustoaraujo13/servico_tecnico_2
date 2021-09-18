package br.com.servicotecnico2.telas;
//Pacotes para auxiliar os metedos do classe.
import br.com.servicotecnico2.conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {
    //Variaveis para ajudar os metodos da classe.
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conn = Conexao.abrirBanco();
    }

    //Esse metedo altera os dados do usuário.
    private void Alterar() {
        String alterando = "update usuarios set usuario =?, email =?, login =?,"
                + " senha =?, perfil =? where id_user =?;";

        try {
            st = conn.prepareStatement(alterando);
            st.setString(1, TxtUsuario.getText().trim());
            st.setString(2, TxtEmail.getText().trim());
            st.setString(3, TxtLogin.getText().trim());
            st.setString(4, TxtSenha.getText().trim());
            st.setString(5, CbPerfil.getSelectedItem().toString());
            st.setString(6, TxtId.getText().trim());

            if ((TxtId.getText().isEmpty()) || (TxtUsuario.getText().isEmpty())
                    || (TxtLogin.getText().isEmpty()) || (TxtSenha.getText().isEmpty())) {
                String informacao2 = "Preencha os campos obrigatórios!!!!";
                JOptionPane.showMessageDialog(this, informacao2);

            } else {
                String comcluido = "Dados alteras com sucesso!";

                st.executeUpdate();

                BtnCriar.setEnabled(true);
                JOptionPane.showMessageDialog(this, comcluido);
                TxtId.setText(null);
                TxtUsuario.setText(null);
                TxtEmail.setText(null);
                TxtLogin.setText(null);
                TxtSenha.setText(null);
                CbPerfil.setSelectedItem(null);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            ///System.out.println(e);
        }

    }

    //Esse metedo busca os dados do usuário.
    private void Buscar() {
        String busca = "select * from usuarios where id_user =?;";

        try {
            st = conn.prepareStatement(busca);
            st.setString(1, TxtId.getText());
            rs = st.executeQuery();

            if (rs.next()) {
                TxtUsuario.setText(rs.getString(2));
                TxtEmail.setText(rs.getString(3));
                TxtLogin.setText(rs.getString(4));
                TxtSenha.setText(rs.getString(5));
                CbPerfil.setSelectedItem(rs.getString(6));
                BtnCriar.setEnabled(false);

            } else if (TxtId.getText().isEmpty()) {
                String informacao3 = "O campo Id precisa ser informado,"
                        + " para executar a busca!";
                JOptionPane.showMessageDialog(this, informacao3);

            } else {
                String resposta = "O usuario que você pesquisou, não existe";

                JOptionPane.showMessageDialog(this, resposta);
                TxtId.setText(null);
                TxtUsuario.setText(null);
                TxtEmail.setText(null);
                TxtLogin.setText(null);
                TxtSenha.setText(null);
                CbPerfil.setSelectedItem(null);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            ///System.out.println(e);

        }

    }

    //Esse metedo cria um novo usuário.
    private void Criar() {
        String criando = "insert into usuarios(id_user, usuario, email,login,senha,perfil)"
                + "values(?,?,?,?,?,?);";
        String comcluido = "Usuário cadastrado!";
        String resposta2 = "";
        String resposta3 = "Admin";

        try {
            st = conn.prepareStatement(criando);
            st.setString(1, TxtId.getText().trim());
            st.setString(2, TxtUsuario.getText().trim());
            st.setString(3, TxtEmail.getText().trim());
            st.setString(4, TxtLogin.getText().trim());
            st.setString(5, TxtSenha.getText().trim());
            st.setString(6, CbPerfil.getSelectedItem().toString());

            if ((TxtId.getText().isEmpty()) || (TxtUsuario.getText().isEmpty())
                    || (TxtLogin.getText().isEmpty()) || (TxtSenha.getText().isEmpty())) {
                String informacao2 = "Preencha os campos obrigatórios!!!!";
                JOptionPane.showMessageDialog(this, informacao2);

            } else {
                st.executeUpdate();
                JOptionPane.showMessageDialog(this, comcluido);
                TxtId.setText(resposta2);
                TxtUsuario.setText(resposta2);
                TxtEmail.setText(resposta2);
                TxtLogin.setText(resposta2);
                TxtSenha.setText(resposta2);
                CbPerfil.setSelectedItem(resposta3);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    //Esse metedo deleta um usuário.
    private void Deletar() {
        String confirmando = "Deseja deletar esse usuário?";
        String confirmando2 = "Atenção";
        int confirmar = JOptionPane.showConfirmDialog(null, confirmando, confirmando2, JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            String deletando = "delete from usuarios where id_user=?;";

            try {
                st = conn.prepareStatement(deletando);
                st.setString(1, TxtId.getText().trim());

                if (TxtId.getText().isEmpty()) {
                    String informacao = "Preencha o campo Id, para excluir usuario!";
                    JOptionPane.showMessageDialog(this, informacao);

                } else {
                    String comcluido = "Usuário excluído com sucesso!";
                    st.executeUpdate();

                    JOptionPane.showMessageDialog(this, comcluido);
                    TxtId.setText(null);
                    TxtUsuario.setText(null);
                    TxtEmail.setText(null);
                    TxtLogin.setText(null);
                    TxtSenha.setText(null);
                    CbPerfil.setSelectedItem(null);

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Houve um erro, tente novamente");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LblSenha = new javax.swing.JLabel();
        LblPerfil = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        TxtUsuario = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        TxtLogin = new javax.swing.JTextField();
        TxtSenha = new javax.swing.JTextField();
        LblId = new javax.swing.JLabel();
        CbPerfil = new javax.swing.JComboBox<>();
        LblUsuario = new javax.swing.JLabel();
        BtnCriar = new javax.swing.JButton();
        LblEmail = new javax.swing.JLabel();
        BtnBuscar = new javax.swing.JButton();
        LblLogin = new javax.swing.JLabel();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela usuário");

        jPanel1.setBackground(new java.awt.Color(24, 18, 30));

        LblSenha.setForeground(new java.awt.Color(255, 255, 255));
        LblSenha.setText("*Senha");

        LblPerfil.setForeground(new java.awt.Color(255, 255, 255));
        LblPerfil.setText("*Perfil");

        TxtId.setBackground(new java.awt.Color(231, 223, 221));

        TxtUsuario.setBackground(new java.awt.Color(231, 223, 221));

        TxtEmail.setBackground(new java.awt.Color(231, 223, 221));

        TxtLogin.setBackground(new java.awt.Color(231, 223, 221));

        TxtSenha.setBackground(new java.awt.Color(231, 223, 221));

        LblId.setForeground(new java.awt.Color(255, 255, 255));
        LblId.setText("*Id");

        CbPerfil.setBackground(new java.awt.Color(71, 20, 246));
        CbPerfil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CbPerfil.setForeground(new java.awt.Color(255, 255, 255));
        CbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        LblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        LblUsuario.setText("*Usuário");

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria um novo usuário");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        LblEmail.setForeground(new java.awt.Color(255, 255, 255));
        LblEmail.setText("Email");

        BtnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        BtnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("Busca o usuário no banco de dados");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        LblLogin.setForeground(new java.awt.Color(255, 255, 255));
        LblLogin.setText("*Login");

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera o usuário no banco de dados");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(0, 0, 0));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta o usuário no banco de dados");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("*Campos obrigatórios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(jLabel1)
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LblUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnDeletar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TxtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TxtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CbPerfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2))
                                        .addComponent(TxtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblId))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblLogin))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblSenha))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblPerfil)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriar)
                    .addComponent(BtnBuscar)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        Criar();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        Alterar();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        Deletar();
    }//GEN-LAST:event_BtnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JComboBox<String> CbPerfil;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblLogin;
    private javax.swing.JLabel LblPerfil;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtLogin;
    private javax.swing.JTextField TxtSenha;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
