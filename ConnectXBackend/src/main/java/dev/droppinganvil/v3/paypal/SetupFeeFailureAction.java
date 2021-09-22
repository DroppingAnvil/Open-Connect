/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public enum SetupFeeFailureAction implements Serializable {
    CONTINUE,
    CANCEL
}
