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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByTen", query = "SELECT u FROM User u WHERE u.ten = :ten"),
    @NamedQuery(name = "User.findByMasinhvien", query = "SELECT u FROM User u WHERE u.masinhvien = :masinhvien"),
    @NamedQuery(name = "User.findByNgaysinh", query = "SELECT u FROM User u WHERE u.ngaysinh = :ngaysinh"),
    @NamedQuery(name = "User.findByGioitinh", query = "SELECT u FROM User u WHERE u.gioitinh = :gioitinh"),
    @NamedQuery(name = "User.findByNgaytao", query = "SELECT u FROM User u WHERE u.ngaytao = :ngaytao"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "ten")
    private String ten;
    @Size(max = 20)
    @Column(name = "masinhvien")
    private String masinhvien;
    @Column(name = "ngaysinh")
    @Temporal(TemporalType.DATE)
    private Date ngaysinh;
    @Size(max = 10)
    @Column(name = "gioitinh")
    private String gioitinh;
    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;
    @Size(max = 100)
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(mappedBy = "idUser")
    private Set<Dangkyhoatdong> dangkyhoatdongSet;
    @OneToMany(mappedBy = "idUser")
    private Set<Diemrenluyen> diemrenluyenSet;
    @OneToMany(mappedBy = "idUser")
    private Set<Binhluan> binhluanSet;
    @OneToMany(mappedBy = "idUser")
    private Set<Baothieu> baothieuSet;
    @OneToMany(mappedBy = "idUser")
    private Set<Thich> thichSet;
    @OneToMany(mappedBy = "idUser")
    private Set<Hoatdong> hoatdongSet;
    @JoinColumn(name = "idChucVu", referencedColumnName = "id")
    @ManyToOne
    private Chucvu idChucVu;
    @JoinColumn(name = "idKhoa", referencedColumnName = "id")
    @ManyToOne
    private Khoa idKhoa;
    @JoinColumn(name = "idLopHoc", referencedColumnName = "id")
    @ManyToOne
    private Lophoc idLopHoc;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    @XmlTransient
    public Set<Hoatdong> getHoatdongSet() {
        return hoatdongSet;
    }

    public void setHoatdongSet(Set<Hoatdong> hoatdongSet) {
        this.hoatdongSet = hoatdongSet;
    }

    public Chucvu getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(Chucvu idChucVu) {
        this.idChucVu = idChucVu;
    }

    public Khoa getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(Khoa idKhoa) {
        this.idKhoa = idKhoa;
    }

    public Lophoc getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(Lophoc idLopHoc) {
        this.idLopHoc = idLopHoc;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.User[ id=" + id + " ]";
    }
    
}
