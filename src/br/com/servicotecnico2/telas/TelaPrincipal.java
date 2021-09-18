package br.com.servicotecnico2.telas;
//Pacotes para auxiliar os metedos do classe.
import br.com.servicotecnico2.conexao.Conexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.plaf.basic.BasicMenuBarUI;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends javax.swing.JFrame {

    private Connection conn = null;

    //Variaveis para ajudar os metodos da classe.
    public TelaPrincipal() {
        initComponents();
        conn = Conexao.abrirBanco();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
        Date data = new Date();

        LblData2.setText(df.format(data));
        LblData2.setForeground(new java.awt.Color(255, 255, 0));
    }

    /*Metodo para imprimir um relatorio de clientes com ajuda do jaspersoft
    que o usuário pediu.
    */
    public void ImprimirClientes() {
        try {
            JasperPrint print = JasperFillManager.fillReport("C:\\Report\\Relatorios\\Clientes.jasper", null, conn);
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }

    }

     /*Metodo para imprimir um relatorio de todos as ordens de serviços
    com ajuda do jaspersoft que o usuário pediu.
    */
    public void ImprimirServicos() {

        try {
            JasperPrint print = JasperFillManager.fillReport("C:\\Report\\Relatorios\\Servicos.jasper", null, conn);
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/servicotecnico2/imagens/Fundo.png"));
        Image image = icon.getImage();
        Desktop = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }

        }
        ;
        LblUsuario = new javax.swing.JLabel();
        LblUsuario2 = new javax.swing.JLabel();
        LblData = new javax.swing.JLabel();
        LblData2 = new javax.swing.JLabel();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCadastro = new javax.swing.JMenu();
        MenuCliente = new javax.swing.JMenuItem();
        MenuUsuario = new javax.swing.JMenuItem();
        MenuOS = new javax.swing.JMenuItem();
        MenuRelatorio = new javax.swing.JMenu();
        MenuRelatorioServiços = new javax.swing.JMenuItem();
        MenuRelatorioClientes = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MenuSobre = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela principal");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(14, 11, 22));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicotecnico2/imagens/Tecnologia.png"))); // NOI18N

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        LblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        LblUsuario.setText("Usuário:");

        LblUsuario2.setForeground(new java.awt.Color(255, 255, 255));
        LblUsuario2.setText("-----");

        LblData.setForeground(new java.awt.Color(255, 255, 255));
        LblData.setText("Data:");

        LblData2.setForeground(new java.awt.Color(255, 255, 255));
        LblData2.setText("-----");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel1)
                .addContainerGap(536, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Logo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblUsuario)
                            .addComponent(LblData))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblData2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblUsuario2))))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(336, 336, 336)
                    .addComponent(jLabel2)
                    .addContainerGap(536, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblUsuario)
                            .addComponent(LblUsuario2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblData)
                            .addComponent(LblData2))
                        .addGap(198, 198, 198)
                        .addComponent(Logo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(jLabel2)
                    .addContainerGap(152, Short.MAX_VALUE)))
        );

        MenuCadastro.setText("Cadastro");
        MenuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroActionPerformed(evt);
            }
        });

        MenuCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        MenuCliente.setText("Cliente");
        MenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuClienteActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuCliente);

        MenuUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        MenuUsuario.setText("Usuário");
        MenuUsuario.setEnabled(false);
        MenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuUsuarioActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuUsuario);

        MenuOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        MenuOS.setText("OS");
        MenuOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOSActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuOS);

        MenuPrincipal.add(MenuCadastro);

        MenuRelatorio.setText("Relatório");
        MenuRelatorio.setEnabled(false);

        MenuRelatorioServiços.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        MenuRelatorioServiços.setText("Serviço");
        MenuRelatorioServiços.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioServiçosActionPerformed(evt);
            }
        });
        MenuRelatorio.add(MenuRelatorioServiços);

        MenuRelatorioClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        MenuRelatorioClientes.setText("Clientes");
        MenuRelatorioClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioClientesActionPerformed(evt);
            }
        });
        MenuRelatorio.add(MenuRelatorioClientes);

        MenuPrincipal.add(MenuRelatorio);

        jMenu1.setText("Ajuda");

        MenuSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        MenuSobre.setText("Sobre");
        MenuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSobreActionPerformed(evt);
            }
        });
        jMenu1.add(MenuSobre);

        MenuPrincipal.add(jMenu1);

        jMenu3.setText("Opções");

        MenuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        MenuSair.setText("Sair");
        MenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSairActionPerformed(evt);
            }
        });
        jMenu3.add(MenuSair);

        MenuPrincipal.add(jMenu3);

        customizeMenuBar(MenuPrincipal);

        setJMenuBar(MenuPrincipal);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuClienteActionPerformed
        TelaCliente tc = new TelaCliente();
        tc.setVisible(true);
        Desktop.add(tc);
    }//GEN-LAST:event_MenuClienteActionPerformed

    private void MenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuUsuarioActionPerformed
        TelaUsuario ts = new TelaUsuario();
        ts.setVisible(true);
        Desktop.add(ts);
    }//GEN-LAST:event_MenuUsuarioActionPerformed

    private void MenuOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOSActionPerformed
        TelaOs to = new TelaOs();
        to.setVisible(true);
        Desktop.add(to);
    }//GEN-LAST:event_MenuOSActionPerformed

    private void MenuCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroActionPerformed

    }//GEN-LAST:event_MenuCadastroActionPerformed

    private void MenuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSobreActionPerformed
        TelaSobre ts = new TelaSobre();
        ts.setVisible(true);
        Desktop.add(ts);
    }//GEN-LAST:event_MenuSobreActionPerformed

    private void MenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSairActionPerformed
        String saindo = "Tem certeza que quer sair?";
        String titulo = "Atenção!";
        int sair = JOptionPane.showConfirmDialog(null, saindo, titulo, JOptionPane.YES_NO_OPTION);

        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }

    }//GEN-LAST:event_MenuSairActionPerformed

    private void MenuRelatorioClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioClientesActionPerformed
        String subtitulo = "Deseja imprimir um relatório dos clientes?";
        String titulo = "Atenção!";
        int imprimir = JOptionPane.showConfirmDialog(null, subtitulo, titulo, JOptionPane.YES_NO_OPTION);

        if (imprimir == JOptionPane.YES_OPTION) {
            ImprimirClientes();
        } else {
        }

    }//GEN-LAST:event_MenuRelatorioClientesActionPerformed

    private void MenuRelatorioServiçosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioServiçosActionPerformed
        String subtitulo = "Deseja imprimir um relatório dos serviços?";
        String titulo = "Atenção!";
        int imprimir = JOptionPane.showConfirmDialog(null, subtitulo, titulo, JOptionPane.YES_NO_OPTION);

        if (imprimir == JOptionPane.YES_OPTION) {
            ImprimirServicos();
        } else {
        }

    }//GEN-LAST:event_MenuRelatorioServiçosActionPerformed
    private void customizeMenuBar(JMenuBar menuBar) {
        menuBar.setUI(new BasicMenuBarUI() {

            public void paint(Graphics g, JComponent c) {
                //[220,199,170]
                //[107,122,143]
                //[6,47,79]
                g.setColor(new java.awt.Color(162, 57, 202));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }

        });

        MenuElement[] menus = menuBar.getSubElements();

        for (MenuElement menuElement : menus) {
            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu);
            menu.setOpaque(false);

            MenuElement[] menuElements = menu.getSubElements();

            for (MenuElement popupMenuElement : menuElements) {
                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);
                MenuElement[] menuItens = popupMenuElement.getSubElements();

                for (MenuElement menuItemElement : menuItens) {
                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    changeComponentColors(menuItem);
                    menuItem.setOpaque(true);

                }
            }
        }
    }

    //Metodo auxiliar que ajuda a dar cor aop Jmenu
    private void changeComponentColors(Component comp) {
        comp.setBackground(new java.awt.Color(162, 57, 202));
        comp.setForeground(Color.WHITE);
    }

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JLabel LblData;
    private javax.swing.JLabel LblData2;
    private javax.swing.JLabel LblUsuario;
    public static javax.swing.JLabel LblUsuario2;
    private javax.swing.JLabel Logo;
    private javax.swing.JMenu MenuCadastro;
    private javax.swing.JMenuItem MenuCliente;
    private javax.swing.JMenuItem MenuOS;
    private javax.swing.JMenuBar MenuPrincipal;
    public static javax.swing.JMenu MenuRelatorio;
    private javax.swing.JMenuItem MenuRelatorioClientes;
    private javax.swing.JMenuItem MenuRelatorioServiços;
    private javax.swing.JMenuItem MenuSair;
    private javax.swing.JMenuItem MenuSobre;
    public static javax.swing.JMenuItem MenuUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
