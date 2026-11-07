module com.andrusoft.planning.product.app {
    requires javafx.controls;
    requires com.h2database;
    requires java.sql;
    requires java.naming;
    requires odfdom.java;
    requires org.mybatis;
    requires poi;
    exports com.andrusoft.planning.product.app;
    exports com.andrusoft.planning.product.app.model;    
}
