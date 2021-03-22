package entities;

import entities.Brands;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-21T12:27:02")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, Float> unitPrice;
    public static volatile SingularAttribute<Products, String> productImage;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Integer> productId;
    public static volatile SingularAttribute<Products, Brands> brandId;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile SingularAttribute<Products, String> productType;

}