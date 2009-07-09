package grails.util

// Superclass skeleton for fuctional tests.
// Subclasses must implement the suite() method.

class WebTest {

    def ant = new AntBuilder()    // may be initialized lazily for memory opt. in subclasses

    /** @deprecated not used any longer (only there for backward compatibility with older test bases) */
    def configMap

    /** used from subclasses to nest the steps into */
    void webtest(String name, Closure yield){
        ant.webtest(name:name){
            steps {
                yield.delegate = ant
                yield()
            }
        }
    }

    /**
        Subclasses override this method to call all their test methods.
    */
    void suite() {}

    /** Main entry point to run the whole suite of tests. */
    void runTests (){
        prepare()
        suite()     // template method call
        finish()
    }

    Map initProps () {
        // obey local properties file if available
        File propFile = new File('webtest/conf/webtest.properties')
        if (propFile.exists()){
            ant.property(file: propFile)
            println propFile.absolutePath +" added."
        } else{
            println propFile.absolutePath +" not found: running without."
        }

        // Load the application properties.
        ant.property(file: 'application.properties')

        def props = ant.project.properties

        // find dir names that change with installation root and plugin version
        def pluginHome = new File("./plugins").listFiles().find { it.name.startsWith("webtest")}
        props.webtestHome = new File("webtest/home").absolutePath
        props.projectName = new File('.').absolutePath.tokenize('./\\')[-1]
        if (! props.webtest_basepath) props.webtest_basepath = props.'app.name'
        println 'Testing ' + props.'app.name'
        return props
    }

    // prepare the ant taskdef, classpath and filesystem for reporting
    void prepare() {
        def props = initProps()
        // map local (old) "webtest_*" props to new "wt.config.*" props for backward compatibility     
        props.findAll{it.key.startsWith('webtest_')}.each { key, value ->
            ant.project.setUserProperty('wt.config.' + key - 'webtest_',  value)
        }
        def webtestXmlFile = new File("${props.webtestHome}/webtest.xml")
        ant.'import' (file: webtestXmlFile.absolutePath)   // sets properties into current ant project
        ant.project.executeTarget 'wt.before.testInWork'
    }

    def finish() {
        ant.project.executeTarget 'wt.after.testInWork'
    }
}
