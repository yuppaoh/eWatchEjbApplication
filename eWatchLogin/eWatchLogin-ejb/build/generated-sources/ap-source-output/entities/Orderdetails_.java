package entities;

import entities.Orders;
import entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-05T17:07:34")
@StaticMetamodel(Orderdetails.class)
public class Orderdetails_ { 

    public static volatile SingularAttribute<Orderdetails, Float> unitPrice;
    public static volatile SingularAttribute<Orderdetails, Integer> quantity;
    public static volatile SingularAttribute<Orderdetails, Products> productId;
    public static volatile SingularAttribute<Orderdetails, Orders> orderId;
    public static volatile SingularAttribute<Orderdetails, Integer> id;

}