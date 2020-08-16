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
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Gustavo
 */
public class Pago extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pago
     */
    MyConexion con = new MyConexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id;
    public Pago() {
        initComponents();
        listar();
    }
    public void listar(){
        String sql= "SELECT*FROM pago";
        try{
            modelo= (DefaultTableModel) tablapago.getModel();
            cn=con.getConnection();
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            rs.first();
            do{  String[] fila= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                modelo.addRow(fila);
            
            }while(rs.next());
            cn.close();
            
            
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void agregar(){
        
        boolean tipopago1=true;
        boolean tipopago2=true;
        float montopago = Float.parseFloat(monto.getText());
        int idcustomer= Integer.parseInt(idcliente.getText());
        int ideb= Integer.parseInt(idebanista.getText());
        int año= micalendario.getCalendar().get(Calendar.YEAR);
        int mes= micalendario.getCalendar().get(Calendar.MONTH);
        int dia= micalendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        String datafecha = ""+año+"-"+mes+"-"+dia+"";
        if(efectivo.isSelected()){
            tipopago1=true;
            tipopago2=false;
        }else{
           if(tarjeta.isSelected()){ tipopago2=true; tipopago1=false;}
        }
        
        if(montopago==0||datafecha.equals("")){
            JOptionPane.showMessageDialog(null, "Campo sin INGRESAR!!!");
        }
        else{
            JOptionPane.showMessageDialog(null, "No llene el campo Id_usuario");
            String sql= "INSERT INTO PAGO(Id_cliente,Id_ebanista,cantidad,fecha,esEfectivo,esTarjeta) values("+idcustomer+","+ideb+","+montopago+",'"+datafecha+"',"+tipopago1+","+tipopago2+");";
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
    public void editar(){
        boolean tipopago1=true;
        boolean tipopago2=true;
        float montopago = Float.parseFloat(monto.getText());
        int idcustomer= Integer.parseInt(idcliente.getText());
        int ideb= Integer.parseInt(idebanista.getText());
        int año= micalendario.getCalendar().get(Calendar.YEAR);
        int mes= micalendario.getCalendar().get(Calendar.MONTH);
        int dia= micalendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        String datafecha = ""+año+"-"+mes+"-"+dia+"";
        if(efectivo.isSelected()){
            tipopago1=true;
            tipopago2=false;
        }else{
           if(tarjeta.isSelected()){ tipopago2=true; tipopago1=false;}
        }
        
        if(montopago==0||datafecha.equals("")){
            JOptionPane.showMessageDialog(null, "Campo sin INGRESAR!!!");
        }
        else{
            JOptionPane.showMessageDialog(null, "No llene el campo Id_usuario");
            String sql= "UPDATE pago set Id_cliente="+idcustomer+", Id_ebanista="+ideb+", cantidad= "+montopago+", fecha='"+datafecha+"',esEfectivo="+tipopago1+",esTarjeta= "+tipopago2+" where Id_pago="+id+";";
            try{
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "PAGO EDITADO EXITOSAMENTE!!");
                limpiartabla();
            }catch(Exception ex){
            
            }
        
        
        }
    
    }
    public void eliminar(){
        
        int filaseleccionada= tablapago.getSelectedRow();
        if(filaseleccionada==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }else{
            String sql="DELETE FROM pago WHERE Id_pago="+id+";";
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
    public void limpiartabla(){
        for(int i=0; i<=tablapago.getRowCount(); i++){
            modelo.removeRow(i);
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
        idpago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idebanista = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        efectivo = new javax.swing.JRadioButton();
        tarjeta = new javax.swing.JRadioButton();
        micalendario = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        agregarpago = new javax.swing.JButton();
        editarpago = new javax.swing.JButton();
        eliminarpago = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablapago = new javax.swing.JTable();

        jLabel1.setText("CRUD  PAGO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("Id_pago");

        idpago.setEditable(false);
        idpago.setEnabled(false);
        idpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idpagoActionPerformed(evt);
            }
        });

        jLabel3.setText("Id_cliente");

        jLabel4.setText("Id_ebanista");

        jLabel5.setText("Monto");

        jLabel6.setText("fecha de pago");

        jLabel7.setText("Tipo de pago");

        efectivo.setText("efectivo");

        tarjeta.setText("Tarjeta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idebanista, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(idcliente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(idpago, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(efectivo)
                        .addGap(34, 34, 34)
                        .addComponent(tarjeta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(micalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 105, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idpago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(monto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(efectivo)
                                            .addComponent(tarjeta)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idebanista, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(micalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opcion"));

        agregarpago.setText("AGREGAR");
        agregarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarpagoActionPerformed(evt);
            }
        });

        editarpago.setText("EDITAR");
        editarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarpagoActionPerformed(evt);
            }
        });

        eliminarpago.setText("ELIMINAR");
        eliminarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarpagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(agregarpago)
                .addGap(61, 61, 61)
                .addComponent(editarpago)
                .addGap(73, 73, 73)
                .addComponent(eliminarpago)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion"));

        tablapago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_pago", "Id_cliente", "Id_ebanista", "monto", "fecha", "Efectivo", "Tarjeta"
            }
        ));
        tablapago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapagoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablapago);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idpagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idpagoActionPerformed

    private void agregarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarpagoActionPerformed
        // TODO add your handling code here:
        agregar();
        listar();
    }//GEN-LAST:event_agregarpagoActionPerformed

    private void tablapagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablapagoMouseClicked
        // TODO add your handling code here:
        int fila = tablapago.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "PAGO NO SELECCIONADO");
        }
        else{
             id= Integer.parseInt((String)tablapago.getValueAt(fila,0).toString());
            int idcl = Integer.parseInt((String)tablapago.getValueAt(fila,1).toString());
            int idebs = Integer.parseInt((String)tablapago.getValueAt(fila,2).toString());
            float mcantidad= Float.parseFloat((String)tablapago.getValueAt(fila, 3).toString());
            
            idpago.setText(""+id);
            idcliente.setText(""+idcl);
            idebanista.setText(""+idebs);
            monto.setText(""+mcantidad);
        }
    }//GEN-LAST:event_tablapagoMouseClicked

    private void editarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarpagoActionPerformed
        // TODO add your handling code here:
        editar();
        listar();
    }//GEN-LAST:event_editarpagoActionPerformed

    private void eliminarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarpagoActionPerformed
        // TODO add your handling code here:
        eliminar();
        listar();
    }//GEN-LAST:event_eliminarpagoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarpago;
    private javax.swing.JButton editarpago;
    private javax.swing.JRadioButton efectivo;
    private javax.swing.JButton eliminarpago;
    private javax.swing.JTextField idcliente;
    private javax.swing.JTextField idebanista;
    private javax.swing.JTextField idpago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser micalendario;
    private javax.swing.JTextField monto;
    private javax.swing.JTable tablapago;
    private javax.swing.JRadioButton tarjeta;
    // End of variables declaration//GEN-END:variables
}
