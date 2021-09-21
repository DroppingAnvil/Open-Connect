/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2;

import java.io.IOException;

public interface RequiresLogin {
    void loginSuccess() throws IOException;
}
