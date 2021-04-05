package entities;

import entities.Comments;
import entities.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-05T17:07:34")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> customerAddress;
    public static volatile SingularAttribute<Customers, String> customerStatus;
    public static volatile ListAttribute<Customers, Comments> commentsList;
    public static volatile SingularAttribute<Customers, String> customerPassword;
    public static volatile SingularAttribute<Customers, String> customerId;
    public static volatile ListAttribute<Customers, Orders> ordersList;
    public static volatile SingularAttribute<Customers, String> customerName;
    public static volatile SingularAttribute<Customers, String> phoneNo;

}