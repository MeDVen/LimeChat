package org.klaptech.limechat.shared.enums;

/**
 * Answer type
 * SUCCESS - when user successfully login to the server
 * USER_ALREADY_CON - user has already connected to the server
 * INCORRECT_PASSWORD - user input incorrrect pwd
 * USER_NOT_EXISTS - first user should register
 */
public enum LoginAnswerType {
    SUCCESS,
    USER_ALREADY_CON,
    INCORRECT_PASSWORD,
    USER_NOT_EXISTS
}
