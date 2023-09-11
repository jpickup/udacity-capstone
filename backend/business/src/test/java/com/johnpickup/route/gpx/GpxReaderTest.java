package com.johnpickup.route.gpx;

import com.johnpickup.route.domain.Gpx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GpxReaderTest {
    private GpxReader gpxReader;

    @BeforeEach
    void setUp() {
        gpxReader = new GpxReader();
    }

    @Test
    void readGpxFile() throws JAXBException, FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test.gpx");
        File gpxFile = new File(resource.getFile());
        Gpx gpxType = gpxReader.readGpxFile(gpxFile);
        assertThat(gpxType.getMetadata().getName(), is("Test GPX"));
        assertThat(gpxType.getTrks().size(), is(1));
        assertThat(gpxType.getTrks().get(0).getTrksegs().size(), is(1));
        assertThat(gpxType.getTrks().get(0).getTrksegs().get(0).getTrkpts().size(), is(349));
    }
}