//package com.temzu.market_project.msproduct.model.newEntitty;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "products")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "price")
//    private int price;
//
//    @Column(name = "image")
//    private String imageUrl;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "products_categories",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private List<Category> customers;
//
//    @OneToMany(mappedBy = "product")
//    private List<ProductItem> productItems;
//
////    @OneToMany(mappedBy = "product")
////    private List<ProductComment> productComments;
//}
