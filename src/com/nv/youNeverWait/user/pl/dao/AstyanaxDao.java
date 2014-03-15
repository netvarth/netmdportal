/**
 * AstyanaxDao.java
 * March 12, 2014
 */
package com.nv.youNeverWait.user.pl.dao;

/**
 * @author Luciya
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;

public class AstyanaxDao {
    private static final Logger LOG = LoggerFactory.getLogger(AstyanaxDao.class);
    public static final String KEYSPACE = "northpole";
    private Keyspace keyspace;
    private AstyanaxContext<Keyspace> astyanaxContext;

    public AstyanaxDao()
    {
    	
    }
    public AstyanaxDao(String host, String keyspace) {
        try {
            this.astyanaxContext = new AstyanaxContext.Builder()
                    .forCluster("Test Cluster")
                    .forKeyspace(keyspace)
                    .withAstyanaxConfiguration(new AstyanaxConfigurationImpl().setDiscoveryType(NodeDiscoveryType.NONE))
                    .withConnectionPoolConfiguration(
                            new ConnectionPoolConfigurationImpl("MyConnectionPool").setMaxConnsPerHost(1)
                                    .setSeeds(host)).withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                    .buildKeyspace(ThriftFamilyFactory.getInstance());
            this.astyanaxContext.start();
            this.keyspace = this.astyanaxContext.getEntity();
           
            // test the connection
            this.keyspace.describeKeyspace();
        } catch (Throwable e) {
        	LOG.error("Could not connect to cassandra.", e);
            System.exit(-1);
        }
    }

    public void cleanup() {
        this.astyanaxContext.shutdown();
    }

	public Keyspace getKeyspace() {
		return keyspace;
	}

	public AstyanaxContext<Keyspace> getAstyanaxContext() {
		return astyanaxContext;
	}

	public void setAstyanaxContext(AstyanaxContext<Keyspace> astyanaxContext) {
		this.astyanaxContext = astyanaxContext;
	}

	public static Logger getLog() {
		return LOG;
	}

	public void setKeyspace(Keyspace keyspace) {
		this.keyspace = keyspace;
	}
}

