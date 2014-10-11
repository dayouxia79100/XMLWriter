
public class Run {

    public static void main(String[] argv){

        // comment this and uncomment the corresponding method you want to test
        XML.writeSample();
        final String connectionXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/connection.xml";
        final String sensorXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/sensors.xml";
        TrackingDataXmlWriter.writeConnectionsElement(connectionXML);
        TrackingDataXmlWriter.writeSensorsElement(sensorXML);
    }
}
