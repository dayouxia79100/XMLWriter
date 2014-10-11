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

/**
 * Created by dayouxia on 10/9/14.
 */
public class TrackingDataXmlWriter {




    public static void writeSensorsElement(String locationToSaveTheFile){

        // you need to write the code to write everything under <Sensors> tag, it is ok to hardcode it, but do put comments
        // name ths output file Sensors.xml

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("TrackingData");
            doc.appendChild(rootElement);

            Element sensors = doc.createElement("Sensors");
            rootElement.appendChild(sensors);

            Element sensor = doc.createElement("Sensor");
            sensors.appendChild(sensor);

            sensor.setAttribute("Type","FeatureBasedSensorSource");
            sensor.setAttribute("Subtype","Fast");

            Element sensorId = doc.createElement("SensorID");
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

    public static void writeConnectionsElement(String locationToSaveTheFile){

        // you need to write the code to write everything under <Connections> tag, it is ok to hardcode it, but do put comments
        // name this output file Connections.xml

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("TrackingData");
            doc.appendChild(rootElement);

            Element connections = doc.createElement("Connections");
            rootElement.appendChild(connections);

            Element cos = doc.createElement("COS");
            connections.appendChild(cos);

            Element name= doc.createElement("Name");
            cos.appendChild(name);

            Element fuser= doc.createElement("Fuser");
            fuser.setAttribute("Type","fast");
            cos.appendChild(fuser);

            Element parameters = doc.createElement("Parameters");
            fuser.appendChild(parameters);

            Element keepPoseForNumberOfFrames = doc.createElement("KeepPoseForNumberOfFrames");
            keepPoseForNumberOfFrames.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(keepPoseForNumberOfFrames);

            Element gravityAssistance = doc.createElement("GravityAssistance");
            gravityAssistance.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(gravityAssistance);

            Element alphaTranslation = doc.createElement("AlphaTransition");
            alphaTranslation.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(alphaTranslation);

            Element gammaTranslation = doc.createElement("GammaTranslation");
            gammaTranslation.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(gammaTranslation);

            Element alphaRotation = doc.createElement("AlphaRotation");
            alphaRotation.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(alphaRotation);

            Element gammaRotation = doc.createElement("GammaRotation");
            gammaRotation.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(gammaRotation);

            Element orientationSensor = doc.createElement("ContinueLostTrackingWithOrientationSensor");
            orientationSensor.appendChild(doc.createTextNode("balnks"));
            parameters.appendChild(orientationSensor);

            Element sensorSource = doc.createElement("SensorSource");
            cos.appendChild(sensorSource);

            Element sensorId = doc.createElement("SensorID");
            sensorSource.appendChild(sensorId);

            Element handEyeCalibration = doc.createElement("HandEyeCalibration");
            sensorSource.appendChild(handEyeCalibration);

            Element cosOffset = doc.createElement("COSOffset");
            sensorSource.appendChild(cosOffset);

            //Offset variables
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Element translationOffset = doc.createElement("TranslationOffset");
            handEyeCalibration.appendChild(translationOffset);
            cosOffset.appendChild(translationOffset);

            Element rotationOffset = doc.createElement("RotationOffset");
            handEyeCalibration.appendChild(rotationOffset);
            cosOffset.appendChild(rotationOffset);

            Element x = doc.createElement("X");
            translationOffset.appendChild(x);
            rotationOffset.appendChild(x);

            Element y = doc.createElement("Y");
            translationOffset.appendChild(y);
            rotationOffset.appendChild(y);

            Element z = doc.createElement("Z");
            translationOffset.appendChild(z);
            rotationOffset.appendChild(z);

            Element w = doc.createElement("W");
            translationOffset.appendChild(w);
            rotationOffset.appendChild(w);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

}
