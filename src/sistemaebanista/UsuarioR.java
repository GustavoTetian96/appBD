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
            do{  String[] fila= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
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
        int año= fechanacimiento.getCalendar().get(Calendar.YEAR);
        int mes= fechanacimiento.getCalendar().get(Calendar.MONTH);
        int dia= fechanacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        int telefono= Integer.parseInt(telefonousuario.getText());
        String nombreuser= nombreusuario.getText();
        String apellidouser= apellidousuario.getText();
        String datafecha = ""+año+"-"+mes+"-"+dia+"";
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
            String sql= "INSERT INTO usuario(Nombre,Apellido,Email,Género,Telefono,Provincia,fechaNacimiento,esCliente,esEbanista) values ('"+nombreusuario.getText()+"','"+apellidousuario.getText()+"','"+emailusuario.getText()+"','"+genero+"',"+telefono+",'"+provincia.getText()+"','"+datafecha+"',"+tipouser1+","+tipouser2+");";
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
        int año= fechanacimiento.getCalendar().get(Calendar.YEAR);
        int mes= fechanacimiento.getCalendar().get(Calendar.MONTH);
        int dia= fechanacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        String datafecha = ""+año+"-"+mes+"-"+dia+"";
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
        
        String sql = "UPDATE  usuario set Nombre ='"+name+"',Apellido='"+lastname+"',Email='"+email+"',Género='"+gener+"',Telefono="+telefono+",Provincia='"+prov+"',fechaNacimiento='"+datafecha+"',esCliente="+tipouser1+", esEbanista= "+tipouser2+" where id_usuario="+id+";";
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
                System.out.println("error al tratar de eliminar");
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

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        agregarusuario = new javax.swing.JButton();
        editarusuario = new javax.swing.JButton();
        eliminarusuario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Id_usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        femenino = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        escliente = new javax.swing.JRadioButton();
        esEbanista = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fechanacimiento = new com.toedter.calendar.JDateChooser();
        nombreusuario = new javax.swing.JTextField();
        apellidousuario = new javax.swing.JTextField();
        provincia = new javax.swing.JTextField();
        emailusuario = new javax.swing.JTextField();
        telefonousuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(160, 94, 72));

        jPanel2.setBackground(new java.awt.Color(214, 77, 32));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operacion"));

        agregarusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agregarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/agregar2.png"))); // NOI18N
        agregarusuario.setText("AGREGAR");
        agregarusuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 185, 57)));
        agregarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarusuarioActionPerformed(evt);
            }
        });

        editarusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/editar2.png"))); // NOI18N
        editarusuario.setText("EDITAR");
        editarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarusuarioActionPerformed(evt);
            }
        });

        eliminarusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/eliminar.png"))); // NOI18N
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
                .addGap(49, 49, 49)
                .addComponent(agregarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(editarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(eliminarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 180, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(agregarusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarusuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(eliminarusuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(214, 77, 32));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id_usuario");

        Id_usuario.setEditable(false);
        Id_usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));
        Id_usuario.setEnabled(false);
        Id_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Id_usuarioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genero");

        masculino.setBackground(new java.awt.Color(214, 77, 32));
        masculino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        masculino.setText("Masculino");

        femenino.setBackground(new java.awt.Color(214, 77, 32));
        femenino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        femenino.setText("Femenino");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telefono");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo");

        escliente.setBackground(new java.awt.Color(214, 77, 32));
        escliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        escliente.setText("Cliente");
        escliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esclienteActionPerformed(evt);
            }
        });

        esEbanista.setBackground(new java.awt.Color(214, 77, 32));
        esEbanista.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        esEbanista.setText("Ebanista");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Provincia");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha de Nacimiento");

        fechanacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

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
                    .addComponent(Id_usuario)
                    .addComponent(nombreusuario)
                    .addComponent(apellidousuario)
                    .addComponent(emailusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(provincia)
                            .addComponent(telefonousuario, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                        .addGap(147, 147, 147)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechanacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masculino)
                        .addGap(10, 10, 10)
                        .addComponent(femenino)
                        .addGap(118, 118, 118)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(escliente)
                            .addComponent(esEbanista))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(escliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(esEbanista))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombreusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masculino)
                            .addComponent(femenino)))
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(telefonousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechanacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(apellidousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CRUD USUARIO");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

        jPanel3.setBackground(new java.awt.Color(214, 77, 32));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion"));

        tablausuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        tablausuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_usuario", "Nombre", "Apellido", "Email", "Genero", "Telefono", "Provincia", "fecha de nacimiento", "TipoCliente", "TipoEbanista"
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Id_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Id_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Id_usuarioActionPerformed

    private void esclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_esclienteActionPerformed

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
    private javax.swing.JButton editarusuario;
    private javax.swing.JButton eliminarusuario;
    private javax.swing.JTextField emailusuario;
    private javax.swing.JRadioButton esEbanista;
    private javax.swing.JRadioButton escliente;
    private com.toedter.calendar.JDateChooser fechanacimiento;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JTextField nombreusuario;
    private javax.swing.JTextField provincia;
    private javax.swing.JTable tablausuario;
    private javax.swing.JTextField telefonousuario;
    // End of variables declaration//GEN-END:variables
}
