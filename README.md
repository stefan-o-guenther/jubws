JUBWS 
=====

Description
-----------

A webservice for executing junit benchmarks.
For the following instructions you need to have Maven installed.

Documentation
-------------

###Doxygen

The Doxygen documentation can be generated using `doxygen` in the JUBWS directory.
Than it can be found at `doc/generated/html/index.html`.

###JavaDoc

The JavaDocs can be generated using Maven with `mvn site` and can then be found at `/target/site/index.html`. This site provides an overview of the maven project and links to the JavaDoc under `Project Reports`.

Compiling
---------

The tests for the JUBWS Project depend on a subproject to be packaged first, but since this subproject is dependent on JUBWS to be packaged and installed in the local Maven repo. You need to build and install JUBWS first and skip its tests.

	mvn install -DskipTests
	
Than you can package the subproject in de benchmarks directory.

	cd benchmarks
	mvn package

After you successfully packaged the benchmarks project you can test JUBWS

	cd ..
	mvn test


Starting the Server
-------------------

	mvn -Pserver -DskipTests

When the server is ready you should be able to browse 
[http://localhost:9000/junitservice?wsdl](http://localhost:9000/junitservice?wsdl) and see the WSDL file for the service.


Starting the Client
-------------------

	mvn -Pclient -DskipTests

* On the **client** side you will see the result of the benchmark as XML.
* On the **server** side you will see debugging output and the output of the benchmark itself.


Using the Service with Intalio
------------------------------

###Downloading the Software

You can find the Software at
[http://bpms.intalio.com/downloads.html](http://bpms.intalio.com/downloads.html)

###Deploying the Service

First start the Intalio BPMS server.
The JUBWS_BP contains a Intalio Designer Project you can import with the designer.
After you imported it you can deploy it to the server with the designer.

###Run a Benchmark from BPMS

Go to the BPMS Management Console at [http://localhost:8080/bpms-console](http://localhost:8080/bpms-console) and login *(default login: admin/changeit)*.
![](https://raw.github.com/opensourceprojects/jubws/master/doc/images/intalio_console_processes.png)



Click on runBenchmark.
![](https://raw.github.com/opensourceprojects/jubws/master/doc/images/intalio_console_runbenchmark.png)

And than on Start.
![](https://raw.github.com/opensourceprojects/jubws/master/doc/images/intalio_console_input.png)


* **path:** file:PATH_TO_JUBWS/jubws/benchmarks/target/benchmarks-1.0.0.jar
* **classname:** de.htwaalen.benchmarks.FileAccessBenchmark
* Click **Submit**.

![](https://raw.github.com/opensourceprojects/jubws/master/doc/images/intalio_console_result.png)









