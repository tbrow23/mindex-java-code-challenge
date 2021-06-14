/**
 * Interface for CompensationServiceImpl. Has a read and create method.
 */

package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String employee);
}