package ui;

import auth.AuthFacade;
import javax.swing.JOptionPane;
import auth.AuthFacade;
import auth.Usuario;
import javax.swing.JOptionPane;

public class inicio_de_sesion extends javax.swing.JFrame {

    public inicio_de_sesion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UserField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PasField = new javax.swing.JPasswordField();
        MostrarC = new javax.swing.JCheckBox();
        Limp = new javax.swing.JButton();
        IS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 30)); // NOI18N
        jLabel2.setText("Inicio de Sesión");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 20)); // NOI18N
        jLabel3.setText("Usuario:");

        UserField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 20)); // NOI18N
        UserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 20)); // NOI18N
        jLabel4.setText("Contraseña:");

        PasField.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 20)); // NOI18N

        MostrarC.setText("Ver contraseña");
        MostrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarCActionPerformed(evt);
            }
        });

        Limp.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        Limp.setText("<html><div style='text-align: center;'>Limpiar<br>Datos</div></html>");
        Limp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpActionPerformed(evt);
            }
        });

        IS.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        IS.setText("<html><div style='text-align: center;'>Iniciar<br>Sesión</div></html>");
        IS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ISActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MostrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Limp, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(IS, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(PasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(MostrarC)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Limp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MostrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarCActionPerformed
        if (MostrarC.isSelected()) {
            PasField.setEchoChar((char)0);
        } else {
            PasField.setEchoChar('*');
        }
    }//GEN-LAST:event_MostrarCActionPerformed

    private void LimpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpActionPerformed
        UserField.setText("");
        PasField.setText("");
    }//GEN-LAST:event_LimpActionPerformed

    private AuthFacade auth = new AuthFacade();
    
    private void ISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ISActionPerformed
        String usuario = UserField.getText();
        String contrasena = new String(PasField.getPassword());

        if (auth.iniciarSesion(usuario, contrasena)) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + auth.getUsuarioActual().getUsername());

            // Aquí puedes usar el rol si quieres abrir interfaces distintas
            String rol = auth.getRolActual();
            menu_principal main = new menu_principal(auth.getUsuarioActual());
            main.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ISActionPerformed

    private void UserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserFieldActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio_de_sesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IS;
    private javax.swing.JButton Limp;
    private javax.swing.JCheckBox MostrarC;
    private javax.swing.JPasswordField PasField;
    private javax.swing.JTextField UserField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
