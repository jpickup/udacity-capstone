package com.johnpickup.garmin.fit;

import com.garmin.fit.BufferEncoder;
import com.garmin.fit.Fit;

public class FitWriter {
    public byte[] writeFit(FitGenerator fitGenerator) {
        BufferEncoder encoder = new BufferEncoder(Fit.ProtocolVersion.V1_0);
        encoder.write(fitGenerator.generate());
        return encoder.close();
    }
}
