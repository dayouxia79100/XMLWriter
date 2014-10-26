import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dayouxia on 10/9/14.
 */
public class TrackingDataXmlWriter {

    public static void headerFooter(String locationToSaveTheFile, ArrayList<String> images){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element root = doc.createElement("TrackingData");
            doc.appendChild(root);

            //xml part
            writeSensorsElement(images,root,doc);
            writeConnectionsElement(root,images.size(),doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File outputFile = new File(locationToSaveTheFile);
            File[] list = outputFile.listFiles();
            StreamResult result = new StreamResult(outputFile);

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, result);
            System.out.println("File saved at "+ outputFile.toString());
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    public static void writeSensorsElement(ArrayList<String> images,Element root, Document doc){

        // you need to write the code to write everything under <Sensors> tag, it is ok to hardcode it, but do put comments
        // name ths output file Sensors.xml

            int i=1;
            String patch = "Patch";

            Element sensors = doc.createElement("Sensors");
            root.appendChild(sensors);

            Element sensor = doc.createElement("Sensor");
            sensors.appendChild(sensor);

            sensor.setAttribute("Type","FeatureBasedSensorSource");
            sensor.setAttribute("Subtype","Fast");

            Element sensorId = doc.createElement("SensorID");
            sensorId.appendChild(doc.createTextNode("FeatureTracking1"));
            sensor.appendChild(sensorId);

            Element parameters = doc.createElement("Parameters");
            sensor.appendChild(parameters);

            Element featureDescriptorAlignment = doc.createElement("FeatureDescriptorAlignment");
            featureDescriptorAlignment.appendChild(doc.createTextNode("Regular"));
            parameters.appendChild(featureDescriptorAlignment);

            Element maxObjectsToDetectPerFrame = doc.createElement("MaxObjectsToDetectPerFrame");
            maxObjectsToDetectPerFrame.appendChild(doc.createTextNode("5"));
            parameters.appendChild(maxObjectsToDetectPerFrame);

            Element maxObjectsToTrackInParallel = doc.createElement("MaxObjectsToTrackInParallel");
            maxObjectsToTrackInParallel.appendChild(doc.createTextNode("1"));
            parameters.appendChild(maxObjectsToTrackInParallel);

            Element similarityThreshold = doc.createElement("SimilarityThreshold");
            similarityThreshold.appendChild(doc.createTextNode("0.7"));
            parameters.appendChild(similarityThreshold);
            if(images.size()!=0 && images!=null) {
                for (String image : images) //for each string token in the array
                {
                    Element sensorCOS = doc.createElement("SensorCOS");
                    sensor.appendChild(sensorCOS);

                    Element sensorCOSId = doc.createElement("SensorCOSID");
                    sensorCOSId.appendChild(doc.createTextNode(patch+i));
                    sensorCOS.appendChild(sensorCOSId);

                    Element sensorCOSIdParameter = doc.createElement("Parameters");
                    sensorCOS.appendChild(sensorCOSIdParameter);

                    Element referenceImage = doc.createElement("ReferenceImage"); //create the reference image tag element
                    referenceImage.appendChild(doc.createTextNode(image));  // this will print out the image name
                    sensorCOSIdParameter.appendChild(referenceImage);

                    Element sensorSimilarity = doc.createElement("SimilarityThreshold");
                    sensorSimilarity.appendChild(doc.createTextNode("0.7"));
                    sensorCOSIdParameter.appendChild(sensorSimilarity);

                    i++;
                  }
            }
    }

    public static void writeConnectionsElement(Element root, int array_size, Document doc) {

        // you need to write the code to write everything under <Connections> tag, it is ok to hardcode it, but do put comments
        // name this output file Connections.xml

        String marker = "MarkerlessCOS";

        Element connections = doc.createElement("Connections");
        root.appendChild(connections);
        if (array_size != 0) {
            for (int i = 1; i < array_size; i++) {
                Element cos = doc.createElement("COS");
                connections.appendChild(cos);

                //Create an
                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(marker + i));
                cos.appendChild(name);

                Element fuser = doc.createElement("Fuser");
                fuser.setAttribute("Type", "SmoothingFuser");
                cos.appendChild(fuser);

                Element parameters = doc.createElement("Parameters");
                fuser.appendChild(parameters);

                Element keepPoseForNumberOfFrames = doc.createElement("KeepPoseForNumberOfFrames");
                keepPoseForNumberOfFrames.appendChild(doc.createTextNode("2"));
                parameters.appendChild(keepPoseForNumberOfFrames);

                Element gravityAssistance = doc.createElement("GravityAssistance");
                gravityAssistance.appendChild(doc.createTextNode(" "));
                parameters.appendChild(gravityAssistance);

                Element alphaTranslation = doc.createElement("AlphaTransition");
                alphaTranslation.appendChild(doc.createTextNode("1.0"));
                parameters.appendChild(alphaTranslation);

                Element gammaTranslation = doc.createElement("GammaTranslation");
                gammaTranslation.appendChild(doc.createTextNode("1.0"));
                parameters.appendChild(gammaTranslation);

                Element alphaRotation = doc.createElement("AlphaRotation");
                alphaRotation.appendChild(doc.createTextNode("0.8"));
                parameters.appendChild(alphaRotation);

                Element gammaRotation = doc.createElement("GammaRotation");
                gammaRotation.appendChild(doc.createTextNode("0.8"));
                parameters.appendChild(gammaRotation);

                Element orientationSensor = doc.createElement("ContinueLostTrackingWithOrientationSensor");
                orientationSensor.appendChild(doc.createTextNode("false"));
                parameters.appendChild(orientationSensor);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Element sensorSource = doc.createElement("SensorSource");
                cos.appendChild(sensorSource);

                Element sensorId = doc.createElement("SensorID");
                sensorId.appendChild(doc.createTextNode("FeatureTracking1"));
                sensorSource.appendChild(sensorId);

                Element sensorCosId = doc.createElement("SensorCosID");
                sensorCosId.appendChild(doc.createTextNode("Patch" + i));
                sensorSource.appendChild(sensorCosId);

                Element handEyeCalibration = doc.createElement("HandEyeCalibration");
                sensorSource.appendChild(handEyeCalibration);

                Element cosOffset = doc.createElement("COSOffset");
                sensorSource.appendChild(cosOffset);

                //Offset variables
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                generateXYZ(handEyeCalibration, doc);

                generateXYZW(handEyeCalibration, doc);

                generateXYZ(cosOffset, doc);

                generateXYZW(cosOffset, doc);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
    }

    private static void generateXYZ(Element root, Document doc)
    {
        Element translationOffset = doc.createElement("TranslationOffset");
        root.appendChild(translationOffset);

        Element x = doc.createElement("X");
        x.appendChild(doc.createTextNode("0"));
        translationOffset.appendChild(x);

        Element y = doc.createElement("Y");
        y.appendChild(doc.createTextNode("0"));
        translationOffset.appendChild(y);

        Element z = doc.createElement("Z");
        z.appendChild(doc.createTextNode("0"));
        translationOffset.appendChild(z);
    }

    private static void generateXYZW(Element root, Document doc)
    {

        Element rotationOffset = doc.createElement("RotationOffset");
        root.appendChild(rotationOffset);

        Element x = doc.createElement("X");
        x.appendChild(doc.createTextNode("0"));
        rotationOffset.appendChild(x);

        Element y = doc.createElement("Y");
        y.appendChild(doc.createTextNode("0"));
        rotationOffset.appendChild(y);

        Element z = doc.createElement("Z");
        z.appendChild(doc.createTextNode("0"));
        rotationOffset.appendChild(z);

        Element w = doc.createElement("W");
        w.appendChild(doc.createTextNode("1"));
        rotationOffset.appendChild(w);
    }

}
