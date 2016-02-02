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

public class IoTConnectManagerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // expected state, real state
    private void assertState(Object[] estate, Hashtable<String, Object> rstate) {
        if (estate == null || rstate == null) {
            assertEquals(estate, rstate);
        } else {
            final String[] KEY_SET = {
                    IoTValues.TEMP_READING,
                    IoTValues.HUMIDITY_READING,

                    IoTValues.DOOR_STATE,
                    IoTValues.LIGHT_STATE,
                    IoTValues.PROXIMITY_STATE,
                    IoTValues.ALARM_STATE,
                    IoTValues.HUMIDIFIER_STATE,
                    IoTValues.CHILLER_STATE,
                    IoTValues.HEATER_STATE,
                    IoTValues.HVAC_MODE
                    };

            for (int i=0; i<KEY_SET.length; i++) {
                if (estate[i] != null) {
                    assertEquals(estate[i], rstate.get(KEY_SET[i]));
                }
            }
        }
    }

    @Test(expected=NullPointerException.class)
    public void testGetStateWithNullConnectionMocking() {
        IoTConnectManager mngr = new IoTConnectManager(null);
        mngr.getState();
    }

    @Test
    public void testGetStateWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();

        // Humidity/Heater/Chiller sensor (HUS) enable
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=71;HR=51;DS=0;LS=0;PS=0;AS=0;HUS=0;CHS=0;HM=0;HES=0" + IoTValues.MSG_END);
        // Humidity/Heater/Chiller sensor (HUS) disable
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=71;HR=51;DS=1;LS=1;PS=1;AS=1;HUS=1;CHS=1;HES=1;HM=1;VS=2" + IoTValues.MSG_END);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:DS=0;TR=32;HR=2;LS=0;AS=0;PS=0" + IoTValues.MSG_END);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:DS=1;TR=0;HR=0;LS=1;AS=1;PS=1" + IoTValues.MSG_END);

        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("");
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SS:" + IoTValues.MSG_END);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:");
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:;");

        replay(conn);

        IoTConnectManager mngr;
        mngr = new IoTConnectManager(conn);
        Hashtable<String, Object> state;

        state = mngr.getState();
        assertState(new Object[]{71, 51, false, false, false, false, false, false, false, "Chiller"}, state);

        state = mngr.getState();
        assertState(new Object[]{71, 51, true, true, true, true, true, true, true, "Heater"}, state);

        state = mngr.getState();
        assertState(new Object[]{32, 2, false, false, false, false, null, null, null, null}, state);

        state = mngr.getState();
        assertState(new Object[]{0, 0, true, true, true, true, null, null, null, null}, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertEquals(0, state.size());

        verify(conn);
    }

    @Test
    public void testSetStateWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();

        expect(conn.sendMessageToHouse("SS:CHS=0;AS=0;LS=0;HUS=0;HES=0;DS=0;PS=0" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);
        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=1;HUS=1;HES=1;DS=1;PS=1" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);
        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=0;HUS=0;HES=0;DS=0;PS=1" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=0;HUS=0;DS=0;HES=0;PS=1;" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:DS=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:AS=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:LS=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:PS=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:HES=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:HUS=0" + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse("SS:CHS=0" + IoTValues.MSG_END))
                .andReturn(null);

        replay(conn);

        IoTConnectManager mngr;
        Hashtable<String, Object> state;

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", false);
        state.put("DS", false);
        state.put("LS", false);
        state.put("PS", false);
        state.put("AS", false);
        state.put("CHS", false);
        state.put("HES", false);

        assertEquals(true, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", true);
        state.put("DS", true);
        state.put("LS", true);
        state.put("PS", true);
        state.put("AS", true);
        state.put("CHS", true);
        state.put("HES", true);

        assertEquals(true, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", false);
        state.put("DS", false);
        state.put("LS", false);
        state.put("PS", true);
        state.put("AS", true);
        state.put("CHS", true);
        state.put("HES", false);

        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", false);
        state.put("LS", false);
        state.put("PS", true);
        state.put("AS", true);
        state.put("CHS", true);
        state.put("HES", false);
        state.put("VS", false);
        state.put("DS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("DS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("AS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("LS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("PS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HES", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", false);
        assertEquals(false, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("CHS", false);
        assertEquals(false, mngr.setState(state));

        verify(conn);
    }

    @Test
    public void testIsConnected() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("isConnected")
                .createMock();

        expect(conn.isConnected()).andReturn(true);
        expect(conn.isConnected()).andReturn(false);
        replay(conn);

        IoTConnectManager mngr;
        mngr = new IoTConnectManager(conn);
        assertEquals(true, mngr.isConnected());
        assertEquals(false, mngr.isConnected());
        mngr = new IoTConnectManager(null);
        assertEquals(false, mngr.isConnected());

        verify(conn);
    }

    @Test(expected=NullPointerException.class)
    public void testDisconnectFromHouseWithNullConnection() {
        IoTConnectManager mngr;
        mngr = new IoTConnectManager(null);
        mngr.disconnectFromHouse();
    }

    @Test
    public void testDisconnectFromHouse() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .createMock();

        IoTConnectManager mngr;
        mngr = new IoTConnectManager(conn);
        mngr.disconnectFromHouse();
    }

}
