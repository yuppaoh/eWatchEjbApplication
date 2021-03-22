/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yup
 */
@Entity
@Table(name = "carts", catalog = "ewatch_data", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carts.findAll", query = "SELECT c FROM Carts c")
    , @NamedQuery(name = "Carts.findByCartId", query = "SELECT c FROM Carts c WHERE c.cartId = :cartId")
    , @NamedQuery(name = "Carts.findByCustomerId", query = "SELECT c FROM Carts c WHERE c.customerId = :customerId")
    , @NamedQuery(name = "Carts.findByProductId", query = "SELECT c FROM Carts c WHERE c.productId = :productId")
    , @NamedQuery(name = "Carts.findByQuantity", query = "SELECT c FROM Carts c WHERE c.quantity = :quantity")})
public class Carts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CartId", nullable = false)
    private Integer cartId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CustomerId", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductId", nullable = false)
    private int productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity", nullable = false)
    private int quantity;

    public Carts() {
    }

    public Carts(Integer cartId) {
        this.cartId = cartId;
    }

    public Carts(Integer cartId, String customerId, int productId, int quantity) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carts)) {
            return false;
        }
        Carts other = (Carts) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Carts[ cartId=" + cartId + " ]";
    }
    
}
