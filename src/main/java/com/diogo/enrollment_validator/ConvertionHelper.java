/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diogo.enrollment_validator;

/**
 *
 * @author diogosilva
 */
public class ConvertionHelper {

    /**
     * Return a Hexadecimal string from Int value
     *
     * @param value the int value you want to convert
     * @return hexadecimal string
     *
     */
    public static String intToHexString(int value){
        return Integer.toHexString(value);
    }
}