/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.io;

import java.io.InputStream;
/**
 * Object for initially reading network inputs
 */
public class NetworkInputIOJob extends IOJob {
    public String ina;
    public NetworkInputIOJob(InputStream is, String ina) {
        super(is, null, true);
        jt = JobType.NETWORK_READ;
        this.ina = ina;
    }

}
