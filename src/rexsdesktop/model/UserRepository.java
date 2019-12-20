/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import rexsdesktop.controller.User_;

/**
 *
 * @author Eduardo
 */
public interface UserRepository {
    boolean userExists(String email);
    String getHash(String email);
    User_ getUser(String email);
    User_ getUser(int id);
}
