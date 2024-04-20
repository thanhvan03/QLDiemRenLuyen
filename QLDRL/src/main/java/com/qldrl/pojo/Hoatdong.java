/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "hoatdong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoatdong.findAll", query = "SELECT h FROM Hoatdong h"),
    @NamedQuery(name = "Hoatdong.findById", query = "SELECT h FROM Hoatdong h WHERE h.id = :id"),
    @NamedQuery(name = "Hoatdong.findByTenhoatdong", query = "SELECT h FROM Hoatdong h WHERE h.tenhoatdong = :tenhoatdong"),
    @NamedQuery(name = "Hoatdong.findByNoidung", query = "SELECT h FROM Hoatdong h WHERE h.noidung = :noidung"),
    @NamedQuery(name = "Hoatdong.findByTieuchi", query = "SELECT h FROM Hoatdong h WHERE h.tieuchi = :tieuchi"),
    @NamedQuery(name = "Hoatdong.findByNgaytao", query = "SELECT h FROM Hoatdong h WHERE h.ngaytao = :ngaytao"),
    @NamedQuery(name = "Hoatdong.findByNgaybatdau", query = "SELECT h FROM Hoatdong h WHERE h.ngaybatdau = :ngaybatdau"),
    @NamedQuery(name = "Hoatdong.findByNgayketthuc", query = "SELECT h FROM Hoatdong h WHERE h.ngayketthuc = :ngayketthuc")})
public class Hoatdong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "tenhoatdong")
    private String tenhoatdong;
    @Size(max = 255)
    @Column(name = "noidung")
    private String noidung;
    @Size(max = 5)
    @Column(name = "tieuchi")
    private String tieuchi;
    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;
    @Column(name = "ngaybatdau")
    @Temporal(TemporalType.DATE)
    private Date ngaybatdau;
    @Column(name = "ngayketthuc")
    @Temporal(TemporalType.DATE)
    private Date ngayketthuc;
    @OneToMany(mappedBy = "idHoatDong")
    private Set<Dangkyhoatdong> dangkyhoatdongSet;
    @OneToMany(mappedBy = "idHoatDong")
    private Set<Diemrenluyen> diemrenluyenSet;
    @OneToMany(mappedBy = "idHoatDong")
    private Set<Binhluan> binhluanSet;
    @OneToMany(mappedBy = "idHoatDong")
    private Set<Baothieu> baothieuSet;
    @OneToMany(mappedBy = "idHoatDong")
    private Set<Thich> thichSet;
    @JoinColumn(name = "idHocKy", referencedColumnName = "id")
    @ManyToOne
    private Hocky idHocKy;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public Hoatdong() {
    }

    public Hoatdong(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenhoatdong() {
        return tenhoatdong;
    }

    public void setTenhoatdong(String tenhoatdong) {
        this.tenhoatdong = tenhoatdong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieuchi() {
        return tieuchi;
    }

    public void setTieuchi(String tieuchi) {
        this.tieuchi = tieuchi;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    @XmlTransient
    public Set<Dangkyhoatdong> getDangkyhoatdongSet() {
        return dangkyhoatdongSet;
    }

    public void setDangkyhoatdongSet(Set<Dangkyhoatdong> dangkyhoatdongSet) {
        this.dangkyhoatdongSet = dangkyhoatdongSet;
    }

    @XmlTransient
    public Set<Diemrenluyen> getDiemrenluyenSet() {
        return diemrenluyenSet;
    }

    public void setDiemrenluyenSet(Set<Diemrenluyen> diemrenluyenSet) {
        this.diemrenluyenSet = diemrenluyenSet;
    }

    @XmlTransient
    public Set<Binhluan> getBinhluanSet() {
        return binhluanSet;
    }

    public void setBinhluanSet(Set<Binhluan> binhluanSet) {
        this.binhluanSet = binhluanSet;
    }

    @XmlTransient
    public Set<Baothieu> getBaothieuSet() {
        return baothieuSet;
    }

    public void setBaothieuSet(Set<Baothieu> baothieuSet) {
        this.baothieuSet = baothieuSet;
    }

    @XmlTransient
    public Set<Thich> getThichSet() {
        return thichSet;
    }

    public void setThichSet(Set<Thich> thichSet) {
        this.thichSet = thichSet;
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
        if (!(object instanceof Hoatdong)) {
            return false;
        }
        Hoatdong other = (Hoatdong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Hoatdong[ id=" + id + " ]";
    }
    
}
