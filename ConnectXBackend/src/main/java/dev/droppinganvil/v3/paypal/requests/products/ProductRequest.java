/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal.requests.products;

import dev.droppinganvil.v3.paypal.Product;
import dev.droppinganvil.v3.paypal.requests.Credentials;
import dev.droppinganvil.v3.paypal.requests.ObjectBuilders;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class ProductRequest {
    public ProductList getProducts(Integer page, Integer pageSize) throws IOException {
        Response response =
        Credentials.client.newCall(new Request.Builder()
        .url(Credentials.url + "/v1/catalogs/products?page_size="+pageSize+"&page="+page+"&total_required=true")
                .addHeader("Authorization", Credentials.auth)
                .addHeader("Content-Type", "application/json")
                .build()
        ).execute();
        return ObjectBuilders.productListJsonAdapter.fromJson(response.body().string());
    }
    public Product createProduct(Product product) throws IOException {
        Response response =
                Credentials.client.newCall(new Request.Builder()
                        .url(Credentials.url + "/v1/catalogs/products")
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .post(RequestBody.create(null, ObjectBuilders.productJsonAdapter.toJson(product)))
                        .build()
                ).execute();
        return ObjectBuilders.productJsonAdapter.fromJson(response.body().string());
    }
}
