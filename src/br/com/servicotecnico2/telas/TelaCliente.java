package br.com.servicotecnico2.telas;
//Pacotes para auxiliar os metedos do classe.
import br.com.servicotecnico2.conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {
    //Variaveis para ajudar os metodos da classe.
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    //Metedo construtor.
    public TelaCliente() {
        initComponents();
        conn = Conexao.abrirBanco();
    }

    //Esse metedo altera os dados do cliente.
     private void alterar() {
        String alterando = "update clientes set nome =?, email =?, telefone =?, "
                + "CPF =? where id_cliente =?;";

        try {
            st = conn.prepareStatement(alterando);
            st.setString(1, TxtNome.getText().trim());
            st.setString(2, TxtEmail.getText().trim());
            st.setString(3, TxtTelefone.getText().trim());
            st.setString(4, TxtCpf.getText().trim());
            st.setString(5, TxtId.getText().trim());

            if ((TxtNome.getText().isEmpty())
                    || (TxtTelefone.getText().isEmpty()) || (TxtCpf.getText().isEmpty())) {
                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {
                String comcluido = "Dados do cliente alterados com sucesso!";
                
                st.executeUpdate();
                JOptionPane.showMessageDialog(this, comcluido);
                BtnCriar.setEnabled(true);
                TxtPesquisa.setText(null);
                TxtNome.setText(null);
                TxtEmail.setText(null);
                TxtTelefone.setText(null);
                TxtCpf.setText(null);
                ((DefaultTableModel) TabClientes.getModel()).setRowCount(0);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            ///System.out.println(e);
        }

    }
    //Esse metedo cria um novo cliente.
    private void Criar() {
        String criando = "insert into clientes (nome, email, telefone, CPF)"
                + " values (?,?,?,?);";

        try {
            st = conn.prepareStatement(criando);
            st.setString(1, TxtNome.getText().trim());
            st.setString(2, TxtEmail.getText().trim());
            st.setString(3, TxtTelefone.getText().trim());
            st.setString(4, TxtCpf.getText().trim());

            if ((TxtNome.getText().isEmpty())
                    || (TxtTelefone.getText().isEmpty()) || (TxtCpf.getText().isEmpty())) {
                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {
                String comcluido = "Cliente cadastrado com sucesso!";

                st.executeUpdate();
                JOptionPane.showMessageDialog(this, comcluido);
                TxtNome.setText(null);
                TxtEmail.setText(null);
                TxtTelefone.setText(null);
                TxtCpf.setText(null);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    //Esse metedo deleta o cliente.
    private void Deletar() {
        String confirmando = "Deseja deletar este cliente?";
        String confirmando2 = "Atenção!";
        int confirmar = JOptionPane.showConfirmDialog(null, confirmando, confirmando2, JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            String deletando = "delete from clientes where id_cliente=?;";

            try {
                st = conn.prepareStatement(deletando);
                st.setString(1, TxtId.getText().trim());
                
                if (TxtId.getText().isEmpty()) {
                    String informacao = "Preencha o campo Id, para excluir cliente!";
                    JOptionPane.showMessageDialog(this, informacao);

                } else {
                    String comcluido = "Cliente excluído com sucesso!";

                    st.executeUpdate();
                    
                    JOptionPane.showMessageDialog(this, comcluido);
                    BtnCriar.setEnabled(true);
                    TxtId.setText(null);
                    TxtNome.setText(null);
                    TxtEmail.setText(null);
                    TxtTelefone.setText(null);
                    TxtCpf.setText(null);
                    TxtPesquisa.setText(null);
                    ((DefaultTableModel) TabClientes.getModel()).setRowCount(0);

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                //System.out.println(e);
            }
        } else {

        }

    }

    //Esse metedo pega as informações da tabela e coloca nos campos.
    private void Mostrar() {
        try {
            int setar = TabClientes.getSelectedRow();
            TxtId.setText(TabClientes.getModel().getValueAt(setar, 0).toString().trim());
            TxtNome.setText(TabClientes.getModel().getValueAt(setar, 1).toString().trim());
            TxtEmail.setText(TabClientes.getModel().getValueAt(setar, 2).toString().trim());
            TxtTelefone.setText(TabClientes.getModel().getValueAt(setar, 3).toString().trim());
            TxtCpf.setText(TabClientes.getModel().getValueAt(setar, 4).toString().trim());
            BtnCriar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "O Campo está vazio.");
        }

    }

    /*Esse metedo busca um cliente, comforme o usuário vai digitdando na caixa de texto
    e mostra os dados do cliente na tabela.
    */
    private void Pesquisar() {
        String pesquisa = "select id_cliente as Id, nome as Nome, email as Email, telefone as Telefone, CPF as CPF from clientes where nome like ?";
        String pesquisa2 = "%";

        try {
            st = conn.prepareStatement(pesquisa);
            st.setString(1, TxtPesquisa.getText().trim() + pesquisa2);

            rs = st.executeQuery();
            TabClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LblPesquisar = new javax.swing.JLabel();
        TxtPesquisa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){

                return false;

            }
        };
        LblId = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BtnCriar = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        LblNome = new javax.swing.JLabel();
        LblEmail = new javax.swing.JLabel();
        LblTelefone = new javax.swing.JLabel();
        LblCpf = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        TxtTelefone = new javax.swing.JTextField();
        TxtCpf = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela cliente");

        jPanel1.setBackground(new java.awt.Color(24, 18, 30));

        LblPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        LblPesquisar.setText("Pesquisar:");

        TxtPesquisa.setBackground(new java.awt.Color(231, 223, 221));
        TxtPesquisa.setForeground(new java.awt.Color(0, 0, 0));
        TxtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPesquisaActionPerformed(evt);
            }
        });
        TxtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPesquisaKeyReleased(evt);
            }
        });

        TabClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Email", "Telefone", "CPF"
            }
        ));
        TabClientes.setFocusable(false);
        TabClientes.setSelectionBackground(new java.awt.Color(14, 11, 22));
        TabClientes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TabClientes.getTableHeader().setResizingAllowed(false);
        TabClientes.getTableHeader().setReorderingAllowed(false);
        TabClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabClientes);

        LblId.setForeground(new java.awt.Color(255, 255, 255));
        LblId.setText("*Id:");

        TxtId.setBackground(new java.awt.Color(231, 223, 221));
        TxtId.setForeground(new java.awt.Color(0, 0, 0));
        TxtId.setEnabled(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("*Campos obrigatórios");

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria um novo cliente");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera o cliente no banco de dados");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(0, 0, 0));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta o cliente no banco de dados");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        LblNome.setForeground(new java.awt.Color(255, 255, 255));
        LblNome.setText("*Nome:");

        LblEmail.setForeground(new java.awt.Color(255, 255, 255));
        LblEmail.setText("Email:");

        LblTelefone.setForeground(new java.awt.Color(255, 255, 255));
        LblTelefone.setText("*Telefone:");

        LblCpf.setForeground(new java.awt.Color(255, 255, 255));
        LblCpf.setText("*CPF:");

        TxtNome.setBackground(new java.awt.Color(231, 223, 221));
        TxtNome.setForeground(new java.awt.Color(0, 0, 0));

        TxtEmail.setBackground(new java.awt.Color(231, 223, 221));
        TxtEmail.setForeground(new java.awt.Color(0, 0, 0));

        TxtTelefone.setBackground(new java.awt.Color(231, 223, 221));
        TxtTelefone.setForeground(new java.awt.Color(0, 0, 0));

        TxtCpf.setBackground(new java.awt.Color(231, 223, 221));
        TxtCpf.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LblCpf)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnDeletar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblTelefone)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(LblEmail)
                            .addGap(41, 41, 41)
                            .addComponent(TxtEmail))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(LblNome)
                                    .addGap(34, 34, 34))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LblId)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(69, 69, 69)
                                    .addComponent(jLabel1))
                                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LblPesquisar)
                            .addGap(18, 18, 18)
                            .addComponent(TxtPesquisa))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblPesquisar)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblId)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblNome))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTelefone))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCpf))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnCriar))
                .addContainerGap(22, Short.MAX_VALUE))
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

    private void TxtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPesquisaActionPerformed

    private void TxtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisaKeyReleased
        Pesquisar();
    }//GEN-LAST:event_TxtPesquisaKeyReleased

    private void TabClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabClientesMouseClicked
        Mostrar();
    }//GEN-LAST:event_TabClientesMouseClicked

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        Criar();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        Deletar();
    }//GEN-LAST:event_BtnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JLabel LblCpf;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblNome;
    private javax.swing.JLabel LblPesquisar;
    private javax.swing.JLabel LblTelefone;
    private javax.swing.JTable TabClientes;
    private javax.swing.JTextField TxtCpf;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
