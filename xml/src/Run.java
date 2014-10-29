import java.util.ArrayList;

public class Run {

    public static void main(String[] argv){

        // comment this and uncomment the corresponding method you want to test
        //XML.writeSample();
        ArrayList<String> images = new ArrayList<String>();
        images.add("dog.jpg");
        images.add("wine.jpg");
        images.add("octocat.png");
        ArrayList<String> imgAttr = new ArrayList<String>();
        imgAttr.add("SmoothingFuser");
        imgAttr.add("BestQualityFuser");
        imgAttr.add("BestQualityFuser");
        final String connectionXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/connection.xml";
        //final String sensorXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/sensors.xml";
        TrackingDataXmlWriter.headerFooter(imgAttr,connectionXML,images);
    }
}
