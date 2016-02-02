package edu.cmu.iot.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.iot.IoTConnection;

public class IoTConnectionTest {

    @Before
    public void setUp() throws Exception {
        IoTConnection.setConnection(null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected=NullPointerException.class)
    public void testGetConnectionWithNullAddrAndNotYetConnectionCreated() {
        System.out.println("testGetConnectionWithNullAddrAndNotYetConnectionCreated");

        IoTConnection.getConnection(null);
    }

    @Test
    public void testGetConnectionWithAddr() {
        System.out.println("testGetConnectionWithAddr");

        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("isConnected")
                .addMockedMethod("getAddress")
                .addMockedMethod("connect", String.class)
                .createMock();

        //TC2 mock-up setup
        expect(conn.isConnected()).andReturn(true);
        expect(conn.getAddress()).andReturn("test.com");

        //TC3 mock-up setup
        expect(conn.isConnected()).andReturn(true);
        expect(conn.getAddress()).andReturn("test1.com");
        expect(conn.connect("test.com")).andReturn(true);
        expect(conn.isConnected()).andReturn(false);

        //TC4 mock-up setup
        expect(conn.isConnected()).andReturn(false);
        expect(conn.connect("test.com")).andReturn(true);
        expect(conn.isConnected()).andReturn(false);

        //TC5 mock-up setup
        expect(conn.isConnected()).andReturn(true);

        replay(conn);

        //TC1
        assertEquals(null, IoTConnection.getConnection("test.com"));

        //TC2~4 setup
        IoTConnection.setConnection(conn);

        //TC2
        assertEquals(conn, IoTConnection.getConnection("test.com"));
        //TC3
        assertEquals(null, IoTConnection.getConnection("test.com"));
        //TC4
        assertEquals(null, IoTConnection.getConnection("test.com"));
        //TC5
        assertEquals(conn, IoTConnection.getConnection(null));

        verify(conn);
    }

    @Test
    public void testIsConnected() {
        IoTConnection conn = IoTConnection.getConnection("test.com");
        assertEquals(null, conn);
    }

    @Test(expected=NullPointerException.class)
    public void testSendMessageToHouseWithNullInput() {
        System.out.println("testSendMessageToHouseWithNullInput");

        //TC mock-up setup
        IoTConnection connMock = partialMockBuilder(IoTConnection.class)
                .createMock();

        replay(connMock);

        //TC setup
        IoTConnection.setConnection(connMock);

        //TC
        connMock.sendMessageToHouse(null);
    }

    @Test
    public void testSendMessageToHouseWithInput() {
        fail("Not yet implemented");
        //not testable. it should be tested in real
    }

    @Test
    public void testDisconnect() {
        fail("Not yet implemented");
        //not testable. it should be tested in real
    }

}
