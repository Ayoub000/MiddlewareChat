package clientTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelFX.DataMonitor;

public class DataMonitorTest {

	@Test
	public void instanciationTest() {
		assertNotNull(DataMonitor.getClientList());
		assertNotNull(DataMonitor.getMessages());
	}

}
