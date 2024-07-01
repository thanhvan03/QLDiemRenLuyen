/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "hocky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hocky.findAll", query = "SELECT h FROM Hocky h"),
    @NamedQuery(name = "Hocky.findById", query = "SELECT h FROM Hocky h WHERE h.id = :id"),
    @NamedQuery(name = "Hocky.findByHocky", query = "SELECT h FROM Hocky h WHERE h.hocky = :hocky"),
    @NamedQuery(name = "Hocky.findByNamhoc", query = "SELECT h FROM Hocky h WHERE h.namhoc = :namhoc")})
public class Hocky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "hocky")
    private String hocky;
    @Column(name = "namhoc")
    private Integer namhoc;
    @OneToMany(mappedBy = "idHocKy")
    @JsonIgnore
    private Set<Hoatdong> hoatdongSet;
    @OneToMany(mappedBy = "idHocKy")
    @JsonIgnore
    private Set<Thanhtich> thanhtichSet;

    public Hocky() {
    }

    public Hocky(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHocky() {
        return hocky;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public Integer getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(Integer namhoc) {
        this.namhoc = namhoc;
    }

    @XmlTransient
    public Set<Hoatdong> getHoatdongSet() {
        return hoatdongSet;
    }

    public void setHoatdongSet(Set<Hoatdong> hoatdongSet) {
        this.hoatdongSet = hoatdongSet;
    }

    @XmlTransient
    public Set<Thanhtich> getThanhtichSet() {
        return thanhtichSet;
    }

    public void setThanhtichSet(Set<Thanhtich> thanhtichSet) {
        this.thanhtichSet = thanhtichSet;
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
        if (!(object instanceof Hocky)) {
            return false;
        }
        Hocky other = (Hocky) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Hocky[ id=" + id + " ]";
    }
    
}
