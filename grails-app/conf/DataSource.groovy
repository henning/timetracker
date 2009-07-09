dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {

    development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:devDB"
		}
	}

    test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
    	}
	}

    production {
		dataSource {
			dbCreate = "update"

			// HSQLDB
			//url = "jdbc:hsqldb:file:/var/lib/prodDb;shutdown=true"

            // MySQL:
            username = "root"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/TimeTracker?autoreconnect=true"
		}
	}

    dbtest {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"

            username = "root"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/TimeTrackerTest?autoreconnect=true"
		}
	}
}