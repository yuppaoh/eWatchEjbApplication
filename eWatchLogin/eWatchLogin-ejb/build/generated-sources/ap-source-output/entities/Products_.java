package entities;

import entities.Brands;
import entities.Orderdetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-30T23:56:22")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, Float> unitPrice;
    public static volatile SingularAttribute<Products, String> productImage;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Integer> productId;
    public static volatile SingularAttribute<Products, Brands> brandId;
    public static volatile ListAttribute<Products, Orderdetails> orderdetailsList;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile SingularAttribute<Products, String> productType;

}