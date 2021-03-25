/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author nganl
 */
@Entity
@Table(name = "brands")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brands.findAll", query = "SELECT b FROM Brands b")
    , @NamedQuery(name = "Brands.findByBrandId", query = "SELECT b FROM Brands b WHERE b.brandId = :brandId")
    , @NamedQuery(name = "Brands.findByBrandName", query = "SELECT b FROM Brands b WHERE b.brandName = :brandName")})
public class Brands implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BrandId")
    private Integer brandId;
    @Size(max = 100)
    @Column(name = "BrandName")
    private String brandName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brandId")
    private List<Products> productsList;

    public Brands() {
    }

    public Brands(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @XmlTransient
    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandId != null ? brandId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brands)) {
            return false;
        }
        Brands other = (Brands) object;
        if ((this.brandId == null && other.brandId != null) || (this.brandId != null && !this.brandId.equals(other.brandId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Brands[ brandId=" + brandId + " ]";
    }
    
}
