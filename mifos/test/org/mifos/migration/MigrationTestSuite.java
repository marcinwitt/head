package org.mifos.migration;

import org.mifos.migration.mapper.TestAddressMapper;
import org.mifos.migration.mapper.TestCenterMapper;
import org.mifos.migration.mapper.TestMeetingMapper;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MigrationTestSuite extends TestSuite {

	public static Test suite() throws Exception {
		TestSuite suite = new MigrationTestSuite();
		suite.addTestSuite(TestMifosDataExchange.class);
		suite.addTestSuite(TestAddressMapper.class);		
		suite.addTestSuite(TestMeetingMapper.class);		
		suite.addTestSuite(TestCenterMapper.class);
		suite.addTestSuite(TestDataExchanger.class);
		return suite;
	}

}
