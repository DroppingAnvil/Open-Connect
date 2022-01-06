/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.utils.obj;

import java.io.IOException;

public interface RequiresLogin {
    boolean attemptLogin() throws IOException;
}
