/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaebanista;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import conexion.MyConexion;
/**
 *
 * @author Gustavo
 */
public class UsuarioR extends javax.swing.JInternalFrame {
    MyConexion con = new MyConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;
    /**
     * Creates new form UsuarioR
     */
    public UsuarioR() {
        initComponents();
        listar();
    }
    public void listar(){
        String sql= "SELECT*FROM usuario";
        try{
            modelo= (DefaultTableModel) tablausuario.getModel();
            cn=con.getConnection();
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            rs.first();
            /*Object[] persona= new Object[9];
            while(rs.next()){
                persona[0]= rs.getInt("Id_usuario");
                persona[1]= rs.getString("Nombre");
                persona[2]= rs.getString("Apellido");
                persona[3]= rs.getString("Email");
                persona[4]= rs.getString("Genero");
                persona[5]= rs.getInt("Telefono");
                persona[6]= rs.getString("Provincia");
                persona[7]= rs.getBoolean("esCliente");
                persona[8]= rs.getBoolean("esEbanista");
                modelo.addRow(persona);
                
            }
            */
            do{  String[] fila= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
                modelo.addRow(fila);
            
            }while(rs.next());
            cn.close();
            
            
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void agregar(){
        String genero="";
        boolean tipouser1=true;
        boolean tipouser2=true;
        //boolean notiposelecionado1 = Boolean.parseBoolean(esEbanista.getText());
        //boolean notiposelecionado2 = Boolean.parseBoolean(escliente.getText());
        int telefono= Integer.parseInt(telefonousuario.getText());
        String nombreuser= nombreusuario.getText();
        String apellidouser= apellidousuario.getText();
        if(masculino.isSelected()){
            genero="M";
        }else{
           if(femenino.isSelected()){ genero="F";}
        }
        if(escliente.isSelected()){
            tipouser1=true;
            tipouser2=false;        
        }
        if(esEbanista.isSelected()){
            tipouser2=true;
            tipouser1=false;
        }
        if(nombreuser.equals("")||apellidouser.equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese Nombre y Apellido, campo sin ingresar");
        }
        else{
            JOptionPane.showMessageDialog(null, "No llene el campo Id_usuario");
            String sql= "INSERT INTO usuario(Nombre,Apellido,Email,Género,Telefono,Provincia,esCliente,esEbanista) values ('"+nombreusuario.getText()+"','"+apellidousuario.getText()+"','"+emailusuario.getText()+"','"+genero+"',"+telefono+",'"+provincia.getText()+"',"+tipouser1+","+tipouser2+");";
            try{
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
                limpiartabla();
            }catch(Exception ex){
            
            }
        
        
        }
        
    }
    public void limpiartabla(){
        for(int i=0; i<=tablausuario.getRowCount(); i++){
            modelo.removeRow(i);
        }
    }
    public void editar(){
        boolean tipouser1=true;
        boolean tipouser2=true;
        String name= nombreusuario.getText();
        String lastname= apellidousuario.getText();
        String email = emailusuario.getText();
        int telefono= Integer.parseInt(telefonousuario.getText());
        String gener ="";
        String prov= provincia.getText();
        if(masculino.isSelected()){
            gener="M";
        }else{
           if(femenino.isSelected()){ gener="F";}
        }
        if(escliente.isSelected()){
            tipouser1=true;
            tipouser2=false;        
        }
        if(esEbanista.isSelected()){
            tipouser2=true;
            tipouser1=false;
        }
        String sql = "UPDATE  usuario set Nombre ='"+name+"',Apellido='"+lastname+"',Email='"+email+"',Género='"+gener+"',Telefono="+telefono+",Provincia='"+prov+"',esCliente="+tipouser1+", esEbanista= "+tipouser2+" where id_usuario="+id+";";
        if(name.equals("")|| lastname.equals("")||email.equals("")){
            JOptionPane.showMessageDialog(null, "Hay algun campo vacio");
        }else{
            try{
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                limpiartabla();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    
    }
    public void eliminar(){
        int filaseleccionada= tablausuario.getSelectedRow();
        if(filaseleccionada==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }else{
            String sql="DELETE FROM usuario WHERE Id_usuario="+id+";";
            try{
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO EXITOSAMENTE");
                limpiartabla();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Id_usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombreusuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellidousuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailusuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        femenino = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        telefonousuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        escliente = new javax.swing.JRadioButton();
        esEbanista = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        provincia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        agregarusuario = new javax.swing.JButton();
        consultarusuario = new javax.swing.JButton();
        editarusuario = new javax.swing.JButton();
        eliminarusuario = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();

        jLabel1.setText("CRUD USUARIO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("Id_usuario");

        Id_usuario.setEditable(false);
        Id_usuario.setEnabled(false);
        Id_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Id_usuarioActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Email");

        jLabel6.setText("Genero");

        masculino.setText("Masculino");

        femenino.setText("Femenino");

        jLabel7.setText("Telefono");

        jLabel8.setText("Tipo");

        escliente.setText("Cliente");
        escliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esclienteActionPerformed(evt);
            }
        });

        esEbanista.setText("Ebanista");

        jLabel9.setText("Provincia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Id_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(nombreusuario)
                    .addComponent(emailusuario)
                    .addComponent(apellidousuario))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masculino)
                        .addGap(10, 10, 10)
                        .addComponent(femenino))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(telefonousuario, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(provincia))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(escliente)
                    .addComponent(jLabel8)
                    .addComponent(esEbanista))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(escliente)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masculino)
                            .addComponent(femenino))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(esEbanista))
                            .addComponent(telefonousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(provincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operacion"));

        agregarusuario.setText("AGREGAR");
        agregarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarusuarioActionPerformed(evt);
            }
        });

        consultarusuario.setText("CONSULTAR");
        consultarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarusuarioActionPerformed(evt);
            }
        });

        editarusuario.setText("EDITAR");
        editarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarusuarioActionPerformed(evt);
            }
        });

        eliminarusuario.setText("ELIMINAR");
        eliminarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarusuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(agregarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consultarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(eliminarusuario)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consultarusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(agregarusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion"));

        tablausuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_usuario", "Nombre", "Apellido", "Email", "Genero", "Telefono", "Provincia", "TipoCliente", "TipoEbanista"
            }
        ));
        tablausuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablausuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablausuario);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Id_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Id_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Id_usuarioActionPerformed

    private void esclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_esclienteActionPerformed

    private void consultarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarusuarioActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_consultarusuarioActionPerformed

    private void agregarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarusuarioActionPerformed
        // TODO add your handling code here:
        agregar();
        listar();
    }//GEN-LAST:event_agregarusuarioActionPerformed

    private void tablausuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablausuarioMouseClicked
        // TODO add your handling code here:
        int fila = tablausuario.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "usuario no seleccionado");
        }
        else{
             id= Integer.parseInt((String)tablausuario.getValueAt(fila,0).toString());
            String name= (String)tablausuario.getValueAt(fila,1);
            String apell= (String)tablausuario.getValueAt(fila,2);
            String email= (String)tablausuario.getValueAt(fila,3);
            String masc = (String)tablausuario.getValueAt(fila,4);
            int telf = Integer.parseInt((String)tablausuario.getValueAt(fila,5).toString());
            String prov = (String)tablausuario.getValueAt(fila,6);
            //boolean escl= Boolean.parseBoolean((String)tablausuario.getValueAt(fila,7).toString());
            //boolean eseb= Boolean.parseBoolean((String)tablausuario.getValueAt(fila,8).toString());
            Id_usuario.setText(""+id);
            nombreusuario.setText(name);
            apellidousuario.setText(apell);
            emailusuario.setText(email);
            telefonousuario.setText(""+telf);
            provincia.setText(prov);
            //escliente.setText(""+escl);
            //esEbanista.setText(""+eseb);
        }
    }//GEN-LAST:event_tablausuarioMouseClicked

    private void editarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarusuarioActionPerformed
        // TODO add your handling code here:
        editar();
        listar();
    }//GEN-LAST:event_editarusuarioActionPerformed

    private void eliminarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarusuarioActionPerformed
        // TODO add your handling code here:
        eliminar();
        listar();
    }//GEN-LAST:event_eliminarusuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Id_usuario;
    private javax.swing.JButton agregarusuario;
    private javax.swing.JTextField apellidousuario;
    private javax.swing.JButton consultarusuario;
    private javax.swing.JButton editarusuario;
    private javax.swing.JButton eliminarusuario;
    private javax.swing.JTextField emailusuario;
    private javax.swing.JRadioButton esEbanista;
    private javax.swing.JRadioButton escliente;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JTextField nombreusuario;
    private javax.swing.JTextField provincia;
    private javax.swing.JTable tablausuario;
    private javax.swing.JTextField telefonousuario;
    // End of variables declaration//GEN-END:variables
}
