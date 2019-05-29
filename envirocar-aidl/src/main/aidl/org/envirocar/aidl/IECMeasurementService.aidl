// IECMeasurementService.aidl
package org.envirocar.aidl;
import org.envirocar.aidl.ECMeasurement;

// Declare any non-default types here with import statements

interface IECMeasurementService {

    ECMeasurement getLatestMeasurement();

}
