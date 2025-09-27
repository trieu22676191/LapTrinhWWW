package iuh.fit.se.bai2_jsp.beans;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static final List<Product> ds = new ArrayList<>();

    static {
        initData();
    }

    public static List<Product> queryProducts() {
        return ds;
    }

    private static void initData() {
        Product sp;

        sp = new Product("PRO01", "Nokia Lumia", "", 10, 99000, "images/dt1.png");
        ds.add(sp);

        sp = new Product("PRO02", "BlackBerry Passport", "", 10, 48000, "images/dt2.png");
        ds.add(sp);

        sp = new Product("PRO03", "Sony Xperia Z5", "", 10, 52000, "images/dt3.png");
        ds.add(sp);

        sp = new Product("PRO04", "HTC One M9", "", 10, 83000, "images/dt4.png");
        ds.add(sp);

        sp = new Product("PRO05", "Samsung Galaxy Note 5", "", 10, 71000, "images/dt5.png");
        ds.add(sp);

        sp = new Product("PRO06", "iPhone 7 jet-black Plus", "", 10, 120000, "images/dt6.png");
        ds.add(sp);
    }
}