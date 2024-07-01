/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "thanhtich")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thanhtich.findAll", query = "SELECT t FROM Thanhtich t"),
    @NamedQuery(name = "Thanhtich.findById", query = "SELECT t FROM Thanhtich t WHERE t.id = :id"),
    @NamedQuery(name = "Thanhtich.findByThanhtich", query = "SELECT t FROM Thanhtich t WHERE t.thanhtich = :thanhtich")})
public class Thanhtich implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "thanhtich")
    private String thanhtich;
    @JoinColumn(name = "idHocKy", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Hocky idHocKy;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private User idUser;

    public Thanhtich() {
    }

    public Thanhtich(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThanhtich() {
        return thanhtich;
    }

    
    public void setThanhtich(String thanhtich) {
        this.thanhtich = thanhtich;
    }

    public Hocky getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(Hocky idHocKy) {
        this.idHocKy = idHocKy;
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
        if (!(object instanceof Thanhtich)) {
            return false;
        }
        Thanhtich other = (Thanhtich) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Thanhtich[ id=" + id + " ]";
    }
    
}
