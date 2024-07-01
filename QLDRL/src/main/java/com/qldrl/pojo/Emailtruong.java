/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "emailtruong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emailtruong.findAll", query = "SELECT e FROM Emailtruong e"),
    @NamedQuery(name = "Emailtruong.findById", query = "SELECT e FROM Emailtruong e WHERE e.id = :id"),
    @NamedQuery(name = "Emailtruong.findByEmail", query = "SELECT e FROM Emailtruong e WHERE e.email = :email"),
    @NamedQuery(name = "Emailtruong.findByTensinhvien", query = "SELECT e FROM Emailtruong e WHERE e.tensinhvien = :tensinhvien"),
    @NamedQuery(name = "Emailtruong.findByMasinhvien", query = "SELECT e FROM Emailtruong e WHERE e.masinhvien = :masinhvien"),
    @NamedQuery(name = "Emailtruong.findByNgaysinh", query = "SELECT e FROM Emailtruong e WHERE e.ngaysinh = :ngaysinh"),
    @NamedQuery(name = "Emailtruong.findByQuequan", query = "SELECT e FROM Emailtruong e WHERE e.quequan = :quequan"),
    @NamedQuery(name = "Emailtruong.findByGioitinh", query = "SELECT e FROM Emailtruong e WHERE e.gioitinh = :gioitinh")})
public class Emailtruong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "tensinhvien")
    private String tensinhvien;
    @Size(max = 20)
    @Column(name = "masinhvien")
    private String masinhvien;
    @Column(name = "ngaysinh")  
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngaysinh;
    @Size(max = 255)
    @Column(name = "quequan")
    private String quequan;
    @Size(max = 10)
    @Column(name = "gioitinh")
    private String gioitinh;

    public Emailtruong() {
    }

    public Emailtruong(Integer id) {
        this.id = id;
    }

    public Emailtruong(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTensinhvien() {
        return tensinhvien;
    }

    public void setTensinhvien(String tensinhvien) {
        this.tensinhvien = tensinhvien;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailtruong)) {
            return false;
        }
        Emailtruong other = (Emailtruong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Emailtruong[ id=" + id + " ]";
    }
    
}
