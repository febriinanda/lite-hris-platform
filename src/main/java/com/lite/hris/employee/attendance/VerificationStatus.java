package com.lite.hris.employee.attendance;

import lombok.Getter;

@Getter
public enum VerificationStatus {
    UNPROCESSED,
    AUTO_VERIFIED,
    PENDING,
    VERIFIED
}
