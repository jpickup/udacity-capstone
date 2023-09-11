package com.johnpickup.route.gpx;

import com.johnpickup.route.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GpxWriterTest {
    private GpxWriter gpxWriter;

    @BeforeEach
    void setUp() {
        gpxWriter = new GpxWriter();
    }

    @Test
    void writeEmptyGpx() throws JAXBException {
        Gpx gpx = new Gpx();
        gpx.setCreator("Creator");
        gpx.setVersion("1.0");
        MetadataType metadata = new MetadataType();
        metadata.setName("Name");
        metadata.setDesc("Description");
        gpx.setMetadata(metadata);
        String xml = gpxWriter.writeGpx(gpx);
            assertThat(xml, is("""
                    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                    <gpx version="1.0" creator="Creator" xmlns="http://www.topografix.com/GPX/1/1">
                        <metadata>
                            <name>Name</name>
                            <desc>Description</desc>
                        </metadata>
                    </gpx>
                    """));
    }

    @Test
    void writeRouteGpx() throws JAXBException {
        Gpx gpx = new Gpx();
        gpx.setCreator("Creator");
        gpx.setVersion("1.0");
        MetadataType metadata = new MetadataType();
        metadata.setName("Name");
        metadata.setDesc("Description");
        gpx.setMetadata(metadata);
        RteType route = new RteType();
        WptType waypoint = new WptType();
        waypoint.setLat(BigDecimal.ONE);
        waypoint.setLon(BigDecimal.ZERO);
        waypoint.setEle(BigDecimal.TEN);
        waypoint.setDesc("Waypoint");
        waypoint.setName("WPT");
        route.getRtepts().add(waypoint);
        gpx.getRtes().add(route);
        String xml = gpxWriter.writeGpx(gpx);
        assertThat(xml, is("""
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <gpx version="1.0" creator="Creator" xmlns="http://www.topografix.com/GPX/1/1">
                    <metadata>
                        <name>Name</name>
                        <desc>Description</desc>
                    </metadata>
                    <rte>
                        <rtept lat="1" lon="0">
                            <ele>10</ele>
                            <name>WPT</name>
                            <desc>Waypoint</desc>
                        </rtept>
                    </rte>
                </gpx>
                """));
    }
}