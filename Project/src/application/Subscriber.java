package application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber implements MqttCallback {
	
    String value1, value2, value3, value4, value5, value6, value7;

	public void client() {
	    MqttClient sampleClient;
		String topic		= "test/#";
	    //String broker       = "tcp://192.168.55.105:1883";
	    String broker       = "tcp://127.0.0.1:1883";
	    String clientId     = MqttAsyncClient.generateClientId();
	    MemoryPersistence persistence = new MemoryPersistence();
	
	    try {
	    	sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        connOpts.setAutomaticReconnect(true);
	        connOpts.setConnectionTimeout(10);
	        System.out.println("Connecting to broker: " + broker);
	        sampleClient.connect(connOpts);
	        System.out.println("Connected");
	        sampleClient.setCallback(this);
	        //MqttMessage message = new MqttMessage();
	        //message.setQos(qos);
	        sampleClient.subscribe(topic);
	        //sampleClient.disconnect();
	        //System.exit(0);

	    } catch(MqttException me) {
	        System.out.println("reason " + me.getReasonCode());
	        System.out.println("msg " + me.getMessage());
	        System.out.println("loc " + me.getLocalizedMessage());
	        System.out.println("cause " + me.getCause());
	        System.out.println("excep " + me);
	        me.printStackTrace();
	        JFrame frame = new JFrame("Error");
	        JOptionPane.showMessageDialog(frame, me.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/*public void sendMessage(String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        this.sampleClient.publish(this.topics, message);
    }*/
	
	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		 System.out.println("Connection lost: " + arg0);
	     System.exit(1);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(String.format("[%s] %s", arg0, new String(arg1.getPayload())));
		
		if(arg0.equals("test/1"))
			this.value1 = new String(arg1.getPayload());
		else if(arg0.equals("test/2"))
			this.value2 = new String(arg1.getPayload());
		else if(arg0.equals("test/3"))
			this.value3 = new String(arg1.getPayload());
		else if(arg0.equals("test/3"))
			this.value4 = new String(arg1.getPayload());
		else if(arg0.equals("test/3"))
			this.value5 = new String(arg1.getPayload());
		else if(arg0.equals("test/3"))
			this.value6 = new String(arg1.getPayload());
		else if(arg0.equals("test/3"))
			this.value7 = new String(arg1.getPayload());
	}
}