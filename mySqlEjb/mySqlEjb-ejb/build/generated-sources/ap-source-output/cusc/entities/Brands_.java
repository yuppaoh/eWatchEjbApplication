package cusc.entities;

import cusc.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-08T00:19:31")
@StaticMetamodel(Brands.class)
public class Brands_ { 

    public static volatile SingularAttribute<Brands, String> brandName;
    public static volatile ListAttribute<Brands, Products> productsList;
    public static volatile SingularAttribute<Brands, Integer> brandId;

}