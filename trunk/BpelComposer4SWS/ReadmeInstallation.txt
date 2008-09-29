Installation Guide for Semantic Web Service Composer for BPEL:
==============================================================

1. Download and install Java 5

2. Download and install ANT 1.6 or later
- add  environment variable ANT_HOME=$ANT_HOME
  e.g. ANT_HOME=C:\Programme\apache-ant-1.7.1
- add %ANT_HOME%\bin to environment variable PATH

3. Checkout the project SWSComposer4BPEL (https://developer.berlios.de/projects/swsc4bpel/)
   from BerliOS (for instructions see https://developer.berlios.de/svn/?group_id=10109)
   into a local folder (further called %COMPOSER_HOME%)
  
4. Download and unzip the pre-configured Semantic BPEL Engine (based on Apache Tomcat 5.5.26 and ActiveBPEL-engine 4.1)
   from http://prdownload.berlios.de/sembpelext/SemanticBPELengine.zip 
   into a local folder (further called %ENGINE_HOME%)

   a. set the environment variable CATALINA_HOME to point to the directory %ENGINE_HOME%

   b. Get a trial or full-licensed version of Jess7.0p2 from http://www.jessrules.com/jess/download.shtml
      and copy the file jess.jar into %ENGINE_HOME%\common\lib 

   c. Also add the file jess.jar to %COMPOSER_HOME%\lib\protege-lib 
      
5. Start Semantic BPEL Engine engine (including Tomcat)
- run %ENGINE_HOME%\start-bpel-engine.bat

6. Check availability of test Semantic Web Services for demo scenario
- check 'http://localhost:8080/PublicServices/servlet/AxisServlet'
  to see if all services are available (ResidentRegistry, PublicServicePayment, etc.) 

7. Configure Semantic Web Service Composer
- set process deployment path to BPEL engine´s deployment folder
  edit %COMPOSER_HOME%\defaults.properties
  DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR=%HOME%\\SemanticBPELengine\\bpr
  e.g. DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR=C:\\pre-configured-installation\\SemanticBPELengine\\bpr

8. Start composer from within development environment
   or via the command "ant composer"
   or via script "start-composer.bat".
   (Make sure to assign at least 256M memory via jvm parameter -Xms256M
    For better performance 512M is pre-configured in build.xml)
    
A help file that explains the usage of the composer is located at docs/help.html.
This file can also be displayed from within the composer via the Help-menu.