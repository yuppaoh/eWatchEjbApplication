/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yup
 */
@Entity
@Table(name = "customers", catalog = "ewatch_data", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c")
    , @NamedQuery(name = "Customers.findByCustomerId", query = "SELECT c FROM Customers c WHERE c.customerId = :customerId")
    , @NamedQuery(name = "Customers.findByCustomerPassword", query = "SELECT c FROM Customers c WHERE c.customerPassword = :customerPassword")
    , @NamedQuery(name = "Customers.findByCustomerName", query = "SELECT c FROM Customers c WHERE c.customerName = :customerName")
    , @NamedQuery(name = "Customers.findByCustomerAddress", query = "SELECT c FROM Customers c WHERE c.customerAddress = :customerAddress")
    , @NamedQuery(name = "Customers.findByPhoneNo", query = "SELECT c FROM Customers c WHERE c.phoneNo = :phoneNo")
    , @NamedQuery(name = "Customers.findByCustomerStatus", query = "SELECT c FROM Customers c WHERE c.customerStatus = :customerStatus")})
public class Customers implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "customerId")
    private List<Orders> ordersList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CustomerId", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CustomerPassword", nullable = false, length = 100)
    private String customerPassword;
//    @Basic(optional = false)
//    @NotNull
    @Size(max = 45)   //min = 1, 
    @Column(name = "CustomerName", length = 45)  //, nullable = false
    private String customerName;
    @Size(max = 45)
    @Column(name = "CustomerAddress", length = 45)
    private String customerAddress;
    @Size(max = 18)
    @Column(name = "PhoneNo", length = 18)
    private String phoneNo;
    @Size(max = 10)
    @Column(name = "CustomerStatus", length = 10)
    private String customerStatus;

    public Customers() {
    }

    public Customers(String customerId) {
        this.customerId = customerId;
    }

    public Customers(String customerId, String customerPassword, String customerName) {
        this.customerId = customerId;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Customers[ customerId=" + customerId + " ]";
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
    
}
