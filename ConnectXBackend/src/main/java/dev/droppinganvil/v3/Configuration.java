/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import java.io.Serializable;
import java.util.List;

public class Configuration implements Serializable {
    public String SDF_FORMAT = "S-m-H-a-EEE-F-M-y";
    public String netID;
    public String nmi_pub;
    public List<String> backendSet;
    public Boolean active = true;
    public Boolean unlimitedUpload = false;

}
