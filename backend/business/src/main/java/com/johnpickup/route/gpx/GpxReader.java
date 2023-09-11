package com.johnpickup.route.gpx;

import com.johnpickup.route.domain.Gpx;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GpxReader {
    public Gpx readGpxFile(File file) throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.route.domain");
        Unmarshaller um = jc.createUnmarshaller();
        Gpx gpx = (Gpx)JAXBIntrospector.getValue(um.unmarshal(new FileInputStream(file)));
        return gpx;
    }
}
