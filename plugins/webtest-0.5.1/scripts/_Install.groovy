
//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'Ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
// Ant.mkdir(dir:"/Developer/grails-plugins/webtest/grails-app/jobs")
//

Ant.property(environment:"env")
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"

// Unpack the webtest distribution into "webtest/home", after removing
// any existing WebTest installation.
def webtestHome = "webtest/home"
Ant.delete(dir: webtestHome)
Ant.mkdir(dir: webtestHome)
Ant.unzip(src: "$webtestPluginDir/lib/build.zip", dest: webtestHome)
