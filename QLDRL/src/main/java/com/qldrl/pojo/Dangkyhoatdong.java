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
 * @author DELL
 */
@Entity
@Table(name = "dangkyhoatdong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dangkyhoatdong.findAll", query = "SELECT d FROM Dangkyhoatdong d"),
    @NamedQuery(name = "Dangkyhoatdong.findById", query = "SELECT d FROM Dangkyhoatdong d WHERE d.id = :id"),
    @NamedQuery(name = "Dangkyhoatdong.findByNgaydangky", query = "SELECT d FROM Dangkyhoatdong d WHERE d.ngaydangky = :ngaydangky"),
    @NamedQuery(name = "Dangkyhoatdong.findByThamgia", query = "SELECT d FROM Dangkyhoatdong d WHERE d.thamgia = :thamgia")})
public class Dangkyhoatdong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngaydangky")
    @Temporal(TemporalType.DATE)
    private Date ngaydangky;
    @Column(name = "thamgia")
    private Boolean thamgia;
    @JoinColumn(name = "idHoatDong", referencedColumnName = "id")
    @ManyToOne
    private Hoatdong idHoatDong;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public Dangkyhoatdong() {
    }

    public Dangkyhoatdong(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgaydangky() {
        return ngaydangky;
    }

    public void setNgaydangky(Date ngaydangky) {
        this.ngaydangky = ngaydangky;
    }

    public Boolean getThamgia() {
        return thamgia;
    }

    public void setThamgia(Boolean thamgia) {
        this.thamgia = thamgia;
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
        if (!(object instanceof Dangkyhoatdong)) {
            return false;
        }
        Dangkyhoatdong other = (Dangkyhoatdong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Dangkyhoatdong[ id=" + id + " ]";
    }
    
}
