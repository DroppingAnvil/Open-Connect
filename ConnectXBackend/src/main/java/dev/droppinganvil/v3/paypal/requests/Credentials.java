/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal.requests;

import okhttp3.OkHttpClient;

public class Credentials {
    public static OkHttpClient client = new OkHttpClient();
    public static String auth = "Basic QWJFU3dIZlM1czdKLVc3bGlDZUNkZzFSNUd6NV9xV1FiaGFIYmVFelIzUndHMFdOM0tiUVltOFExSHVyY0dXd0hzZDdPX0FnSHdHWFpyVFQ6RU5WODY2SXF3QllLVlZBR0h4SWdHSHJZLVdXVGJhVWtVMWxLc1dhajZfM210TnQ4R3BQSTRPV0ZMNWw5TFp4NlVhOHdEYUJERVkteGh4aUQ=";
    public static String clientID = "AbESwHfS5s7J-W7liCeCdg1R5Gz5_qWQbhaHbeEzR3RwG0WN3KbQYm8Q1HurcGWwHsd7O_AgHwGXZrTT";
    public static String url = "https://api-m.sandbox.paypal.com";
    public static String account = "sb-mbg8i4143634@business.example.com";
}
