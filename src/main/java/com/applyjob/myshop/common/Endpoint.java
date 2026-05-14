package com.applyjob.myshop.common;

public final class Endpoint {

    private Endpoint() {
    }

    public static final class Api {

        private Api() {
        }

        public static final String ROOT = "/api";

        public static final String PRODUCTS = "/products";

        public static final String ORDERS = "/orders";
    }

    public static final class Admin {

        private Admin() {
        }

        public static final String ROOT = "/admin";

        public static final String PRODUCTS = "/products";

        public static final String ORDERS = "/orders";
    }

    public static final class User {

        private User() {
        }

        public static final String PRODUCTS = "/products";

        public static final String CART = "/cart";

        public static final String CHECKOUT = "/checkout";
    }
}