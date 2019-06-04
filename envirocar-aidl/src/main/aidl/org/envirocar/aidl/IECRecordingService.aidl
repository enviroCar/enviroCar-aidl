// IECRecordingService.aidl
package org.envirocar.aidl;
import org.envirocar.aidl.ECMeasurement;
import org.envirocar.aidl.ECRawObdValue;

// Declare any non-default types here with import statements

interface IECRecordingService {

    /**
     * Returns true if the enviroCar is currently recording a track.
     */
    boolean isRecordingTrack();

    /**
     * Returns true if the enviroCar is recording and the OBD adapter is connected.
     */
    boolean isOBDConnected();

    /**
     * Returns the start time of the recording as an ISO8601 timestamp.
     */
    String isRecordingSince();

    /**
     * Returns the latest measurement that has been recorded.
     *
     * @note ECMeasurement usually taken at certain recording periods (default every 5 seconds).
     */
    ECMeasurement getLatestMeasurement();

    /**
     * Lists the phenomenas that are supported by the current car
     *
     * @note only returns supported phenomenons when the enviroCar is connected and records a trip.
     */
    String[] listSupportedPhenomenons();

    /**
     *  Returns the latest value that has been recorded for a certain phenomenon.
     *
     *  @param phenomenon - The requested phenomenon type.
     */
    ECRawObdValue getLatestObdValue(String phenomenon);

}
