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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "diemrenluyen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diemrenluyen.findAll", query = "SELECT d FROM Diemrenluyen d"),
    @NamedQuery(name = "Diemrenluyen.findById", query = "SELECT d FROM Diemrenluyen d WHERE d.id = :id"),
    @NamedQuery(name = "Diemrenluyen.findByDiem", query = "SELECT d FROM Diemrenluyen d WHERE d.diem = :diem"),
    @NamedQuery(name = "Diemrenluyen.findByNgaycapnhat", query = "SELECT d FROM Diemrenluyen d WHERE d.ngaycapnhat = :ngaycapnhat")})
public class Diemrenluyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "diem")
    private Integer diem;
    @Column(name = "ngaycapnhat")
    @Temporal(TemporalType.DATE)
    private Date ngaycapnhat;
    @JoinColumn(name = "idHoatDong", referencedColumnName = "id")
    @ManyToOne
    private Hoatdong idHoatDong;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public Diemrenluyen() {
    }

    public Diemrenluyen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Date ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public Hoatdong getIdHoatDong() {
        return idHoatDong;
    }

    public void setIdHoatDong(Hoatdong idHoatDong) {
        this.idHoatDong = idHoatDong;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Diemrenluyen)) {
            return false;
        }
        Diemrenluyen other = (Diemrenluyen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Diemrenluyen[ id=" + id + " ]";
    }
    
}
