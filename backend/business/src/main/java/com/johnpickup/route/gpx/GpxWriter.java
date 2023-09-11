package com.johnpickup.route.gpx;

import com.johnpickup.route.domain.Gpx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class GpxWriter {
    public String writeGpx(Gpx gpxType) throws JAXBException {
        if (gpxType == null) return null;

        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.route.domain");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(gpxType, stringWriter);
        return stringWriter.toString();
    }
}
