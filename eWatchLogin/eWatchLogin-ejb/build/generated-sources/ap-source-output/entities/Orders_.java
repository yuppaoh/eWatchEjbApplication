package entities;

import entities.Customers;
import entities.Orderdetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-05T17:07:34")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Integer> orderId;
    public static volatile ListAttribute<Orders, Orderdetails> orderdetailsList;
    public static volatile SingularAttribute<Orders, Customers> customerId;
    public static volatile SingularAttribute<Orders, String> orderStatus;
    public static volatile SingularAttribute<Orders, String> description;
    public static volatile SingularAttribute<Orders, Date> deliveryDate;
    public static volatile SingularAttribute<Orders, Date> orderDate;

}