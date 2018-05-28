package lab7;

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.uddi.v3_service.UDDISecurityPortType;

public class SimplePublishPortable {
    private static UDDISecurityPortType security = null;
    private static UDDIPublicationPortType publish = null;
    public SimplePublishPortable() {
        try {
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
            Transport transport = uddiClient.getTransport("default");
            security = transport.getUDDISecurityService();
            publish = transport.getUDDIPublishService();
        } catch (Exception e) { 
			e.printStackTrace(); 
		}
    }

    // publish service
    public void publish(String username, String password, String businessName, String serviceName) {
        try {
            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(username);              
            getAuthTokenMyPub.setCred(password);                       
            AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
            System.out.println(getAuthTokenMyPub.getUserID() + " never log auth tokens!");
            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(businessName);
            myBusEntity.getName().add(myBusName);
            SaveBusiness sb = new SaveBusiness();
            sb.getBusinessEntity().add(myBusEntity);
            sb.setAuthInfo(myPubAuthToken.getAuthInfo());
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
            System.out.println("myBusiness key:  " + myBusKey);
            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(serviceName);
            myService.getName().add(myServName);
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue("http://example.org/services/myservice?wsdl");
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
            myService.setBindingTemplates(myBindingTemplates);
            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(myPubAuthToken.getAuthInfo());
            ServiceDetail sd = publish.saveService(ss);
            String myServKey = sd.getBusinessService().get(0).getServiceKey();
            System.out.println("myService key: " + myServKey);
            security.discardAuthToken(new DiscardAuthToken(myPubAuthToken.getAuthInfo()));
        } catch (Exception e) { 
			e.printStackTrace(); 
		}
    }
}