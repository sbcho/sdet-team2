package edu.cmu.iot.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.iot.IoTConnectManager;
import edu.cmu.iot.IoTConnection;
import edu.cmu.iot.IoTValues;

public class IoTConnectionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected=NullPointerException.class)
    public void testGetConnectionWithNullAddrAndNotYetConnectionCreated() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("isConnected").createMock();

        expect(conn.isConnected()).andReturn(false);

        replay(conn);

        IoTConnection.getConnection(null);

        verify(conn);
    }

    @Test
    public void testGetConnectionWithNullAddrAndConnectionCreated() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("isConnected")
                .addMockedMethod("connect", String.class)
                .createMock();

        expect(conn.isConnected()).andReturn(false);
        expect(conn.isConnected()).andReturn(false);

        replay(conn);

        assertFalse(null == IoTConnection.getConnection("test.com"));
        assertTrue(null == IoTConnection.getConnection(null));

        verify(conn);
    }

    @Test(expected=NullPointerException.class)
    public void testGetConnection() {
        IoTConnection conn;

        conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("isConnected").createMock();

        expect(conn.isConnected()).andReturn(false);

        replay(conn);

        IoTConnection.getConnection(null);

        verify(conn);
    }

    @Test
    public void testIsConnected() {
//        fail("Not yet implemented");
    }

    @Test
    public void testSendMessageToHouse() {
//        fail("Not yet implemented");
    }

    @Test
    public void testDisconnect() {
//        fail("Not yet implemented");
    }

}
