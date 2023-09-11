package com.johnpickup.route.gpx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.johnpickup.route.domain.Gpx;
public class GpxParser {
    public Gpx readGpxStream(InputStream stream) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.route.domain");
        Unmarshaller um = jc.createUnmarshaller();
        Gpx gpxType = (Gpx)JAXBIntrospector.getValue(um.unmarshal(stream));
        return gpxType;
    }
}
