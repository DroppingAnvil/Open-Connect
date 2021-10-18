/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import java.io.Serializable;

public class Configuration implements Serializable {
    public static String SDF_FORMAT = "S-m-H-a-EEE-F-M-y";
    public static String netID = "TESTNET-PRE-ALPHA-0";
    public static Boolean active = true;
    public static final Integer rateLimit = 15;
    public static final Integer rateLimitSleep = 1000;
    //
    public static Long IO_THREAD_SLEEP = 100L;
    public static Integer IO_WRITE_BYTE_BUFFER = 2048;
    public static Integer IO_READ_BYTE_BUFFER = 2048;
    public static Integer IO_REVERSE_BYTE_BUFFER = 2048;
    //TODO
    public static Integer IO_INPUT_SKIP = 2048;
    public static Integer IO_MAX_INPUT = 10000000;

    //NMI Data
    public static final String NMI_KEY = "-----BEGIN PGP PUBLIC KEY BLOCK-----\n" +
            "\n" +
            "mQINBGFtvUsBEACy7LEIsYKeHXSStUK4g1iQ/P0mBKw4LZCLjMd1wwQGp47YJIi9\n" +
            "KaX2wmeRibKMCZWhm3dBMPTxGsLPLEhob4TunPtt5QrpqabGSgCuBWYSoyQnaM9H\n" +
            "JKv+Ok1h3oXeq8vcGVl68HBqbiNi49089PtFxVcqmlsFDIfGpBFh0/RakcNd22Dd\n" +
            "LTqmYOWUMhBOa/iMHOMdhX5KDpx6vzsjVt44HOUE8JvWhpjCzsCDWQDGY2rZljCx\n" +
            "UpO8Ui460oIEKZGVY7yuCtu8rbi5YCmVsdglrymML94iX4BijoVoq8qjrlBlJCPL\n" +
            "zh7xwOpaNGPrqf1qIpbeJiLH+D16G01hHEOj86xG5dnU2rHiZQ+mCV4lseYB93yP\n" +
            "gataf6X0i4SMPV0H5ArhrGgfVNGB61lfeYFpdZp/suH49Y3J175xh16SRIK9HCfp\n" +
            "O/eVjfFDxNd6DKxOZSWl3bRFguGjP7sKvJEjlI3GPnifU45t5jXzEo9Wd2NNZkGJ\n" +
            "2tYUFPP8cr+2/4Y2FR3GpEcLdCRacwk3rHaiMm3PxQv5u4yZaMw1tMwcbyMDU7XX\n" +
            "Pnq1YD3uG7v+4ryBN4UNFwIwlzftqowXxYLC6YxxI7LMbdg8XL4IZI+jIgAcWH6Q\n" +
            "cajoRhlj3368NvLNiZS142aJqpLXv7KnVe7l+umoKmLKlFEgyafPUyiMRwARAQAB\n" +
            "tC9DaHJpc3RvcGhlciBXaWxsZXR0IDxjaHJpc0BhbnZpbGRldmVsb3BtZW50LnVz\n" +
            "PokCTgQTAQgAOBYhBLB3Kq+XSVTvGTpjR2t9/N4oqAtlBQJhbb1LAhsjBQsJCAcC\n" +
            "BhUKCQgLAgQWAgMBAh4BAheAAAoJEGt9/N4oqAtlUsMP/RZcJJG7/s5EvcJqJIvJ\n" +
            "YdFND/Q8dO5lFVbxGrpI/8cJzOu3xiHm/zWbROh60Mf8TD0AusPhZti7UqEmp58C\n" +
            "IPccQUprYy6XnCyMsEE9VAdfK6oOW0AHo/H7NEncazn2+IMtJi2D6y2W+q1Oo1cB\n" +
            "9Alr4dOZx1Frq7jIo00R6SH39l7Eka0ESb0wwrKXh2TI/jiybJVOIHzCuEUz4Vff\n" +
            "nkyDcCUVHv3mxZzgdigfHj3h+Hewl8IknGbN06ZEmWU+yC5HnW+XEoHvrsLXovLm\n" +
            "w2RpvK7H0F38o9owrr6MJJLfVLkTiAAUd0jrzYhhbvw9Q7kR7k0fYJhbKPXutOEF\n" +
            "rXbCyJkRrLiYy6Ts3QdMwN5PnmLmdZiIHKoXYV4N5CHyriswL/3S2SG2LFFSoJjR\n" +
            "LRS4M7gr/7v71KY6MRUIUzJd4ElL7MgegD03xdd5vzl4nIo4PTh+G6u78o4CnkLj\n" +
            "/tGfApbiRf2gS2wEmPF0auKMISRH4Rhmij8Eph3Cb8Z1TuJUMymrPaNJUeUwEvuJ\n" +
            "fUkFrcGEnUFd0UHOQ7HJq74fMQxDH/TrWb9SPBBc/iYs+LtOiifBalE4KTUSmqzl\n" +
            "w+mJUti29SIH3vg12I4E0H/u8DpAI4iug24s5Gbh5O0NDvgWVIxwMee0IH9f2Tdd\n" +
            "LjUzBaU8r57Sew/A5kW35oXUuQINBGFtvUsBEADU2KJoBhB7iCAnCxCXi6DdhaIT\n" +
            "3SlW4gRr6RUioDc5HmaRZYcZSDa3lye/QvZvWe/JGWG/Og6GHehLIyjzBK2UOjTr\n" +
            "mxEwF0q/rBg+t3NI0qc9jKKqpzOrgy4hNKppDBf3v1CILnohgxI0taQ7DsKfV2KV\n" +
            "x080GNg9DXhjRu2tW4kHtZll6OAF40vBEwihGH4Jsa9qamCJcmgON4hzDhTSHiZg\n" +
            "a+oafqS1KJkb9bH7quP4CEGPvsUfFLjhWs4FGf2RUt341LzR81iPa/SfkmeovCYu\n" +
            "XAAxDiyOIH6Su8KgWPti5mXxIp/JIocmKkHh+Sm7WxVqWc/YeXJsyt7UKxRjCVAO\n" +
            "8jDtcqx5oxVocM9DAmJRe5vbW8dHwAMK2S7nFNJddLVDFaOsXAntoTsqFDussZU2\n" +
            "HdrAxa/L6aez/VtcdYPUlPxRnndHxm/5+zBPIJ9gvQ7pNhyLQvzPGvOG8X+BCcTo\n" +
            "dj66jD0BYejb/gMtijytjblpK9gry9/9WcyOqUfxF99rFdtxnKgjfH/MMpzU/d7n\n" +
            "RlErN9M3g2b4tCLjYdKN7dj62OibQLJlNm4TVvTqOPGnIbSrWEli1YSu1yyZpNcI\n" +
            "4ypVmx1Ll47rx4oITx1ymkMmbZnEYEEB5PKq19JuAwIfCk7i5cpUNOsnG/z/42O+\n" +
            "5J+CC+nsAmX10xrtVwARAQABiQI2BBgBCAAgFiEEsHcqr5dJVO8ZOmNHa3383iio\n" +
            "C2UFAmFtvUsCGwwACgkQa3383iioC2WS7Q/7Bjd8qrDyzMteLOoDqkax3ojDNBL8\n" +
            "5LAIWcdxFgwYQUxc291Wms8sKMlD0PgiWg7SXmb5lZvGEqSA0ezTtVSdD8l07LsJ\n" +
            "3frpNHRntPB5zrjTuLT8y5S1cHmu/bpTyaKoDev9/V/OhYD1jNpH5rtq/nelPfJX\n" +
            "9a4M+N9gaDQyvROKKpi6TKZMjk+o+p/qb4qrlMIaYoNkR97w5CWNex3ygtfxL6PJ\n" +
            "FfNJuZP+vGsnH4MeDj8X4ihT3N5lJXWxZUF3FwxjxEfSIwp3jBRfW1GA4dzBJW78\n" +
            "ctbhLGKh+NF8O23/zXMCjqF2tLsKIeKlCCx1k0+ZMksGiELDU5q4euZ5+DG/pFOL\n" +
            "AMJ27dB346cmL/XYUIEjmp5hn0QiB8ezlQxna8aN45+rDItb+MeDS63bkhdnbYL0\n" +
            "UX4JC6l590Vq6V2BQd8a1ZNNupnL3uu8XJmdYXN8wu9JbdkXGt8AfLe5Q9fY7VvR\n" +
            "TtmBm/bPEM7dH0XjIk/en93zWHrgvm4h4i+zWx6NJHWKZC15efSE5eWyTCPtLT7v\n" +
            "qxu7FUm/haNDn3qjuSgTkrGT6zJnh+0G3y7a5l6ndrfuXwHfDsusVV7TN3Kdx7IO\n" +
            "MSRqDZsaXO1KfwBudqcfheD5EezgtKt5F63e6UpK1S4vwYzOqbOUHjhwfbu9+K56\n" +
            "2fdncbCixiV/sck=\n" +
            "=chz5\n" +
            "-----END PGP PUBLIC KEY BLOCK-----";


}
