/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.User;
/**
 *
 * @author HNIA
 */
public interface IUserDao {

    /**
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     *
     * @param login
     * @return
     */
    User findUserByLogin(String login);

    /**
     *
     * @param login
     * @param password
     * @return
     */
    boolean authenticate(String login, String password);

    /**
     *
     * @param login
     * @return
     */
    boolean userExists(String login);

    /**
     *
     * @param login
     * @param newPassword
     * @return
     */
    boolean updatePassword(String login, String newPassword);
}