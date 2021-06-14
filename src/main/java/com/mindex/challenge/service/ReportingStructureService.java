/*
* Interface for the ReportingStructureServiceImpl. Only has one read method 
* with a single string parameter.
*/

package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface ReportingStructureService {
    ReportingStructure read(String id);
}
