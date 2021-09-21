/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

public enum Reason {
    BUYER_COMPLAINT,
    CHARGEBACK,
    ECHECK,
    INTERNATIONAL_WITHDRAWAL,
    OTHER,
    PENDING_REVIEW,
    RECEIVING_PREFERENCE_MANDATES_MANUAL_ACTION,
    REFUNDED,
    UNILATERAL,
    VERIFICATION_REQUIRED,
}
