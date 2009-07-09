                  /*
 * Copyright 2004-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Gant script that generates a new web test from a domain class
 * 
 * @author Graeme Rocher
 * @author Dierk Koenig
 *
 * @since 0.4
 */
import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"

includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )


target ('default': "Creates a skeleton of a Canoo WebTest (functional test) for a given domain name") {
    depends(checkVersion)
    def domain = args?.toString()
	if(!domain) {
		Ant.input(addProperty:"artifact.name", message:"WebTest name not specified. Please enter:")
		domain = Ant.antProject.properties."artifact.name"
	}
		      
	def className       = GCU.getClassNameRepresentation(domain)
	def propertyName    = GCU.getPropertyNameRepresentation(domain)
	def fileName        = "webtest/tests/${className}Test.groovy"
		
	Ant.sequential {  
		copy(todir:"${basedir}", overwrite:false) {
			fileset(dir:"${webtestPluginDir}/src/templates", includes:"webtest/**/*")
		}			
		copy(file:"${webtestPluginDir}/src/templates/artifacts/WebTest.groovy",
			 tofile:fileName) 
		replace(file:fileName, 
				token:"XclassNameX", value:"${className}" )
		replace(file:fileName, 
				token:"XpropertyNameX", value:"${propertyName}" )
	}	                                                                            
	println "Web Test generated at $fileName"  
}
