/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author Gustavo
 */
public class TestConexion {
    public static void main(String[] args){
        MyConexion db = new MyConexion();
        db.getConnection();
    }
}
