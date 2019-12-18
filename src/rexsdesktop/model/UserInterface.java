/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

/**
 *
 * @author Carlos Herrera
 */
public interface UserInterface {
    void register(String name, String email, String password, String type,String state);
    void login();
}
