package br.com.servicotecnico2.telas;
//Pacotes para auxiliar os metedos do classe.
import br.com.servicotecnico2.conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaOs extends javax.swing.JInternalFrame {
    //Variaveis para ajudar os metodos da classe.

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private String tipo;
    private String situacao;

    //Metedo construtor.
    public TelaOs() {
        initComponents();
        conn = Conexao.abrirBanco();
    }

    //Esse metedo altera os dados da OS.
    private void AlterarOs() {
        String alterandoOs = "update os set tipo =?, situação =?, "
                + "equipamento =?, defeito =?, servico =?,"
                + " tecnico =?, valor =? where os =?;";

        try {
            st = conn.prepareStatement(alterandoOs);
            st.setString(1, tipo);
            st.setString(2, CbSituação.getSelectedItem().toString());
            st.setString(3, TxtEquipamento.getText().trim());
            st.setString(4, TxtDefeito.getText().trim());
            st.setString(5, TxtServico.getText().trim());
            st.setString(6, TxtTecnico.getText().trim());
            st.setString(7, TxtValorTotal.getText().trim().replace(",", "."));
            st.setString(8, TxtNumeroOs.getText().trim());

            if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
                    || (TxtTecnico.getText().isEmpty())) {
                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {
                String comcluido = "Ordem de serviço alterada!";
                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);
                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);
                TxtIdClienteOs.setText(null);
                TxtDataHora.setText(null);
                TxtNumeroOs.setText(null);
                BtnCriar.setEnabled(true);
                TxtPesquisarOs.setEnabled(true);
                ((DefaultTableModel) TabPesquisaOs.getModel()).setRowCount(0);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    //Esse metedo busca a OS.
    private void BuscarOs() {
        String mensagem = "Informe o numero da OS.";
        String numeroOs = JOptionPane.showInputDialog(mensagem);
        String buscaOs = "select * from os where os =" + numeroOs;

        try {
            st = conn.prepareStatement(buscaOs);
            rs = st.executeQuery();

            if (rs.next()) {
                TxtNumeroOs.setText(rs.getString(1));
                TxtDataHora.setText(rs.getString(2));
                String RadTipo = rs.getString(3);

                if (RadTipo.equals("OS")) {
                    RadOs.setSelected(true);
                    tipo = "OS";

                } else {
                    RadOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }

                CbSituação.setSelectedItem(rs.getString(4));
                TxtEquipamento.setText(rs.getString(5));
                TxtDefeito.setText(rs.getString(6));
                TxtServico.setText(rs.getString(7));
                TxtTecnico.setText(rs.getString(8));
                TxtValorTotal.setText(rs.getString(9));
                TxtIdClienteOs.setText(rs.getString(10));
                BtnCriar.setEnabled(false);
                TxtPesquisarOs.setEnabled(false);
                TabPesquisaOs.setVisible(false);

            } else {
                String informacao = "OS não existe!";
                JOptionPane.showMessageDialog(this, informacao);

                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);
                TxtIdClienteOs.setText(null);
                TxtDataHora.setText(null);
                TxtNumeroOs.setText(null);
                BtnCriar.setEnabled(true);
                TxtPesquisarOs.setEnabled(true);
                ((DefaultTableModel) TabPesquisaOs.getModel()).setRowCount(0);

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "OS Inválida!");
            // System.out.println(e);
            TxtEquipamento.setText(null);
            TxtDefeito.setText(null);
            TxtServico.setText(null);
            TxtTecnico.setText(null);
            TxtValorTotal.setText(null);
            TxtIdClienteOs.setText(null);
            TxtDataHora.setText(null);
            TxtNumeroOs.setText(null);
            BtnCriar.setEnabled(true);
            TxtPesquisarOs.setEnabled(true);
            ((DefaultTableModel) TabPesquisaOs.getModel()).setRowCount(0);

        }

    }

    //Esse metedo cria uma nova OS.
    private void CriarOs() {
        String criando = "insert into os (situação, tipo, equipamento, defeito, "
                + "servico, tecnico, valor, id_cli) values(?,?,?,?,?,?,?,?);";

        try {
            st = conn.prepareStatement(criando);
            st.setString(1, tipo);
            st.setString(2, CbSituação.getSelectedItem().toString());
            st.setString(3, TxtEquipamento.getText().trim());
            st.setString(4, TxtDefeito.getText().trim());
            st.setString(5, TxtServico.getText().trim());
            st.setString(6, TxtTecnico.getText().trim());
            st.setString(7, TxtValorTotal.getText().trim().replace(",", "."));
            st.setString(8, TxtIdClienteOs.getText().trim());

            if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
                    || (TxtTecnico.getText().isEmpty())) {
                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {
                String comcluido = "Ordem de serviço criada.";
                String resposta = "Na bancada";
                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);
                TxtPesquisarOs.setText(null);
                CbSituação.setSelectedItem(resposta);
                TxtIdClienteOs.setText(null);
                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    //Esse metedo deleta uma OS.
    private void DeletarOs() {
        String confirmando = "Deseja deletar esta OS?";
        String confirmando2 = "Atenção!";
        int confirmar = JOptionPane.showConfirmDialog(null, confirmando, confirmando2, JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            String deletando = "delete from os where os=?;";
            try {
                st = conn.prepareStatement(deletando);
                st.setString(1, TxtNumeroOs.getText());

                if (TxtNumeroOs.getText().isEmpty()) {
                    String informacao = "Preencha o campo número OS, "
                            + "para excluir a ordem de serviço!";
                    JOptionPane.showMessageDialog(this, informacao);

                } else {
                    String comcluido = "OS excluído com sucesso!";
                    JOptionPane.showMessageDialog(this, comcluido);

                    st.executeUpdate();
                    TxtEquipamento.setText(null);
                    TxtDefeito.setText(null);
                    TxtServico.setText(null);
                    TxtTecnico.setText(null);
                    TxtValorTotal.setText(null);
                    TxtIdClienteOs.setText(null);
                    TxtDataHora.setText(null);
                    TxtNumeroOs.setText(null);
                    BtnCriar.setEnabled(true);
                    TxtPesquisarOs.setEnabled(true);
                    ((DefaultTableModel) TabPesquisaOs.getModel()).setRowCount(0);

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                //System.out.println(e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Houve um erro, tente novamente!");
        }

    }

    //Esse metedo imprimi com a ajuda do jaspersoft uma ordem de serviço que o usuário escolheu.
    public void ImprimirOrdem() {
        try {
            HashMap filtro = new HashMap();
            filtro.put("NumeroOs", Integer.parseInt(TxtNumeroOs.getText()));

            JasperPrint print = JasperFillManager.fillReport("C:\\Report\\Relatorios\\OrdemServico.jasper", filtro, conn);
            JasperViewer.viewReport(print, false);

        } catch (JRException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Esse metedo pega as informações da tabela e coloca nos campos.
    private void Mostrar() {
        try {
            int setar = TabPesquisaOs.getSelectedRow();
            TxtIdClienteOs.setText(TabPesquisaOs.getModel().getValueAt(setar, 0).toString().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "O Campo está vazio.");
        }

    }

    /*Esse metedo busca um cliente, comforme o usuário vai digitdando na caixa de texto
    e mostra os dados do cliente na tabela.
    */
    private void Pesquisar() {
        String pesquisa = "select id_cliente as Id, nome as Nome, email as Email from clientes where nome like ?";
        String pesquisa2 = "%";

        try {
            st = conn.prepareStatement(pesquisa);
            st.setString(1, TxtPesquisarOs.getText().trim() + pesquisa2);
            rs = st.executeQuery();

            TabPesquisaOs.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LblNumeroOs = new javax.swing.JLabel();
        TxtNumeroOs = new javax.swing.JTextField();
        RadOrcamento = new javax.swing.JRadioButton();
        RadOs = new javax.swing.JRadioButton();
        LblDataHora = new javax.swing.JLabel();
        TxtDataHora = new javax.swing.JTextField();
        LabSituação = new javax.swing.JLabel();
        CbSituação = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        LblPesquisarOs = new javax.swing.JLabel();
        TxtPesquisarOs = new javax.swing.JTextField();
        LblIdOs = new javax.swing.JLabel();
        TxtIdClienteOs = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabPesquisaOs = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex){

                return false;

            }

        };
        LblEquipamento = new javax.swing.JLabel();
        TxtEquipamento = new javax.swing.JTextField();
        LblDefeito = new javax.swing.JLabel();
        TxtDefeito = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TxtServico = new javax.swing.JTextField();
        LblTecnico = new javax.swing.JLabel();
        TxtTecnico = new javax.swing.JTextField();
        LblValorTotal = new javax.swing.JLabel();
        TxtValorTotal = new javax.swing.JTextField();
        BtnCriar = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        BtnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela ordem de serviço");

        jPanel1.setBackground(new java.awt.Color(24, 18, 30));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(24, 18, 30));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ordem de serviço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        LblNumeroOs.setForeground(new java.awt.Color(255, 255, 255));
        LblNumeroOs.setText("Número OS:");

        TxtNumeroOs.setBackground(new java.awt.Color(231, 223, 221));
        TxtNumeroOs.setEnabled(false);

        RadOrcamento.setBackground(new java.awt.Color(24, 18, 30));
        RadOrcamento.setForeground(new java.awt.Color(255, 255, 255));
        RadOrcamento.setText("Orçamento");
        RadOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOrcamentoActionPerformed(evt);
            }
        });

        RadOs.setBackground(new java.awt.Color(24, 18, 30));
        RadOs.setForeground(new java.awt.Color(255, 255, 255));
        RadOs.setText("OS");
        RadOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOsActionPerformed(evt);
            }
        });

        LblDataHora.setForeground(new java.awt.Color(255, 255, 255));
        LblDataHora.setText("Data e hora:");

        TxtDataHora.setBackground(new java.awt.Color(231, 223, 221));
        TxtDataHora.setEnabled(false);
        TxtDataHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDataHoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(RadOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblNumeroOs)
                    .addComponent(LblDataHora))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDataHora, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TxtNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblNumeroOs))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblDataHora)
                    .addComponent(TxtDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadOs)
                    .addComponent(RadOrcamento)))
        );

        LabSituação.setForeground(new java.awt.Color(255, 255, 255));
        LabSituação.setText("Situação:");

        CbSituação.setBackground(new java.awt.Color(162, 57, 202));
        CbSituação.setForeground(new java.awt.Color(255, 255, 255));
        CbSituação.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Entrega ok", "Orçamento reprovado", "Aguardando aprovação", "Aguardando peças", "Abandonado pela cliente", "Retornou", " ", " " }));

        jPanel3.setBackground(new java.awt.Color(24, 18, 30));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(470, 450));

        LblPesquisarOs.setForeground(new java.awt.Color(255, 255, 255));
        LblPesquisarOs.setText("Pesquisar:");

        TxtPesquisarOs.setBackground(new java.awt.Color(231, 223, 221));
        TxtPesquisarOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPesquisarOsKeyReleased(evt);
            }
        });

        LblIdOs.setForeground(new java.awt.Color(255, 255, 255));
        LblIdOs.setText("*Id Cliente:");

        TxtIdClienteOs.setBackground(new java.awt.Color(231, 223, 221));
        TxtIdClienteOs.setEnabled(false);

        TabPesquisaOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Email"
            }
        ));
        TabPesquisaOs.setFocusable(false);
        TabPesquisaOs.getTableHeader().setResizingAllowed(false);
        TabPesquisaOs.getTableHeader().setReorderingAllowed(false);
        TabPesquisaOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPesquisaOsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabPesquisaOs);

        LblEquipamento.setForeground(new java.awt.Color(255, 255, 255));
        LblEquipamento.setText("*Equipamento:");

        TxtEquipamento.setBackground(new java.awt.Color(231, 223, 221));

        LblDefeito.setForeground(new java.awt.Color(255, 255, 255));
        LblDefeito.setText("*Defeito:");

        TxtDefeito.setBackground(new java.awt.Color(231, 223, 221));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Serviço:");

        TxtServico.setBackground(new java.awt.Color(231, 223, 221));

        LblTecnico.setForeground(new java.awt.Color(255, 255, 255));
        LblTecnico.setText("*Técnico:");

        TxtTecnico.setBackground(new java.awt.Color(231, 223, 221));
        TxtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTecnicoActionPerformed(evt);
            }
        });

        LblValorTotal.setForeground(new java.awt.Color(255, 255, 255));
        LblValorTotal.setText("Valor total:");

        TxtValorTotal.setBackground(new java.awt.Color(231, 223, 221));
        TxtValorTotal.setText("0");

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria uma nova ordem de serviço");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera a ordem de serviço");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(0, 0, 0));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta a ordem de serviço");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        BtnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        BtnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("Busca a ordem de serviço");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        BtnImprimir.setBackground(new java.awt.Color(255, 255, 0));
        BtnImprimir.setForeground(new java.awt.Color(0, 0, 0));
        BtnImprimir.setText("Imprimir");
        BtnImprimir.setToolTipText("Cuidado!! Deleta o cliente no banco de dados");
        BtnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEquipamento)
                    .addComponent(jLabel1)
                    .addComponent(LblValorTotal)
                    .addComponent(LblIdOs)
                    .addComponent(LblPesquisarOs))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TxtValorTotal)
                                .addComponent(TxtIdClienteOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtEquipamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtServico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(LblTecnico)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                    .addComponent(TxtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(LblDefeito)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnDeletar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnImprimir)
                        .addGap(0, 34, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblPesquisarOs))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIdClienteOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblIdOs))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblEquipamento)
                            .addComponent(TxtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(LblDefeito)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblValorTotal)
                            .addComponent(TxtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblTecnico))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriar)
                    .addComponent(BtnBuscar)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnImprimir))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabSituação)
                            .addComponent(CbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabSituação)
                        .addGap(18, 18, 18)
                        .addComponent(CbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void RadOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadOrcamentoActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_RadOrcamentoActionPerformed

    private void RadOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadOsActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_RadOsActionPerformed

    private void TxtDataHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDataHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDataHoraActionPerformed

    private void TxtPesquisarOsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisarOsKeyReleased
        Pesquisar();
    }//GEN-LAST:event_TxtPesquisarOsKeyReleased

    private void TabPesquisaOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPesquisaOsMouseClicked
        Mostrar();
    }//GEN-LAST:event_TabPesquisaOsMouseClicked

    private void TxtTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTecnicoActionPerformed

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        CriarOs();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AlterarOs();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        DeletarOs();
    }//GEN-LAST:event_BtnDeletarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        BuscarOs();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed
        String subtitulo = "Deseja imprimir essa OS?";
        String titulo = "Atenção!";
        int imprimir = JOptionPane.showConfirmDialog(null, subtitulo, titulo, JOptionPane.YES_NO_OPTION);

        if (imprimir == JOptionPane.YES_OPTION) {
            ImprimirOrdem();
        } else {
            //System.out.println("Erro");
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_BtnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JComboBox<String> CbSituação;
    private javax.swing.JLabel LabSituação;
    private javax.swing.JLabel LblDataHora;
    private javax.swing.JLabel LblDefeito;
    private javax.swing.JLabel LblEquipamento;
    private javax.swing.JLabel LblIdOs;
    private javax.swing.JLabel LblNumeroOs;
    private javax.swing.JLabel LblPesquisarOs;
    private javax.swing.JLabel LblTecnico;
    private javax.swing.JLabel LblValorTotal;
    private javax.swing.JRadioButton RadOrcamento;
    private javax.swing.JRadioButton RadOs;
    private javax.swing.JTable TabPesquisaOs;
    private javax.swing.JTextField TxtDataHora;
    private javax.swing.JTextField TxtDefeito;
    private javax.swing.JTextField TxtEquipamento;
    private javax.swing.JTextField TxtIdClienteOs;
    private javax.swing.JTextField TxtNumeroOs;
    private javax.swing.JTextField TxtPesquisarOs;
    private javax.swing.JTextField TxtServico;
    private javax.swing.JTextField TxtTecnico;
    private javax.swing.JTextField TxtValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
