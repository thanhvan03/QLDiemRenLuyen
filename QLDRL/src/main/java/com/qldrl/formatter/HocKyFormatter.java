/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.formatter;

import com.qldrl.pojo.Hocky;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DELL
 */
public class HocKyFormatter implements Formatter<Hocky>{

    @Override
    public String print(Hocky hk, Locale locale) {
        return String.valueOf(hk.getId());
    }

    @Override
    public Hocky parse(String id, Locale locale) throws ParseException {
        Hocky hk = new Hocky();
        hk.setId(Integer.parseInt(id));
        return hk;
    }
}
