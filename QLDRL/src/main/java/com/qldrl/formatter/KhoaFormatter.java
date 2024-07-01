/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.formatter;

import com.qldrl.pojo.Khoa;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author PC
 */
public class KhoaFormatter implements Formatter<Khoa>{

    @Override
    public String print(Khoa k, Locale locale) {
        return String.valueOf(k.getId());
    }

    @Override
    public Khoa parse(String id, Locale locale) throws ParseException {
        Khoa k = new Khoa();
        k.setId(Integer.parseInt(id));

        return k;
    }
    
}
