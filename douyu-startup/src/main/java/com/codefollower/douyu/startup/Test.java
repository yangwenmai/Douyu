/*
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codefollower.douyu.startup;

public class Test {
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		Connector ajp = new AjpConnector();
		Connector http = new HttpConnector();
		// server.addConnectors(ajp, http);

		server.addConnector(ajp);
		server.addConnector(http);

		String srcDir = "E:\\Douyu\\trunk\\douyu-examples\\WEB-INF\\src";
		String classesDir = "E:\\Douyu\\douyu-examples-classes";
		server.init("myApp", "UTF-8", srcDir, classesDir, true, null);

		server.start();

		System.out.println("http server started");
	}
}
