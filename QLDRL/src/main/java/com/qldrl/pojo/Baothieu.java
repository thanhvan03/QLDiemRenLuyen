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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "baothieu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baothieu.findAll", query = "SELECT b FROM Baothieu b"),
    @NamedQuery(name = "Baothieu.findById", query = "SELECT b FROM Baothieu b WHERE b.id = :id"),
    @NamedQuery(name = "Baothieu.findByMinhchung", query = "SELECT b FROM Baothieu b WHERE b.minhchung = :minhchung"),
    @NamedQuery(name = "Baothieu.findByNgaybao", query = "SELECT b FROM Baothieu b WHERE b.ngaybao = :ngaybao")})
public class Baothieu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "minhchung")
    private String minhchung;
    @Column(name = "ngaybao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaybao;
    @JoinColumn(name = "idHoatDong", referencedColumnName = "id")
    @ManyToOne
    private Hoatdong idHoatDong;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public Baothieu() {
    }

    public Baothieu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMinhchung() {
        return minhchung;
    }

    public void setMinhchung(String minhchung) {
        this.minhchung = minhchung;
    }

    public Date getNgaybao() {
        return ngaybao;
    }

    public void setNgaybao(Date ngaybao) {
        this.ngaybao = ngaybao;
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
        if (!(object instanceof Baothieu)) {
            return false;
        }
        Baothieu other = (Baothieu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Baothieu[ id=" + id + " ]";
    }
    
}
