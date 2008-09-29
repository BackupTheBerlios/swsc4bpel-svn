to every wsdd:
------------------
<requestFlow> 
       <handler type="soapmonitor"/> 
     </requestFlow> 
     <responseFlow> 
       <handler type="soapmonitor"/> 
     </responseFlow> 
<parameter name="sendMultiRefs" value="false"/>

to build.xml:
------------------
<target name="deployMonitor">
		<java className="org.apache.axis.client.AdminClient">
			<arg value="deploy-monitor.wsdd"/>
			<arg value="-lhttp://localhost:8080/active-bpel/services/AdminClient"/>
			<classpath refid="classpath"/>
		</java>
	</target>
	<target name="undeployMonitor">
		<java className="org.apache.axis.client.AdminClient">
			<arg value="undeploy-monitor.wsdd"/>
			<arg value="-lhttp://localhost:8080/active-bpel/services/AdminClient"/>
			<classpath refid="classpath"/>
		</java>
	</target>
	
	
SOAPMonitorApplet$ServiceFilterPanel.class und Konsorten nach webapps/PublicServices kopieren!