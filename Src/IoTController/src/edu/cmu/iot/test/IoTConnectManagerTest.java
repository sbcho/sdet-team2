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
//            for (String key : rstate.keySet()) {
//                System.out.println(key + ":" + rstate.get(key));
//            }
            assertEquals(estate[0], rstate.get(IoTValues.TEMP_READING));
            assertEquals(estate[1], rstate.get(IoTValues.HUMIDITY_READING));
            assertEquals(estate[2], rstate.get(IoTValues.HUMIDIFIER_STATE));
            assertEquals(estate[3], rstate.get(IoTValues.DOOR_STATE));
            assertEquals(estate[4], rstate.get(IoTValues.LIGHT_STATE));
            assertEquals(estate[5], rstate.get(IoTValues.PROXIMITY_STATE));
            assertEquals(estate[6], rstate.get(IoTValues.ALARM_STATE));
            assertEquals(estate[7], rstate.get(IoTValues.CHILLER_STATE));
            assertEquals(estate[8], rstate.get(IoTValues.HEATER_STATE));
        }
    }

    @Test
    public void testGetStateOfHumidityWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();
        // Humidity sensor (HUS) enable
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=71;HR=51;HUS=0;DS=0;LS=0;PS=1;AS=1;CHS=1;HES=0" + IoTValues.MSG_END);
        // Humidity sensor (HUS) disable
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=71;HR=51;HUS=1;DS=0;LS=0;PS=1;AS=1;CHS=1;HES=0" + IoTValues.MSG_END);
        replay(conn);
        IoTConnectManager mngr = new IoTConnectManager(conn);
        Hashtable<String, Object> state;

        state = mngr.getState();
        assertState(new Object[]{71, 51, false, false, false, true, true, true, false}, state);

        state = mngr.getState();
        assertState(new Object[]{71, 51, true, false, false, true, true, true, false}, state);
//        assertEquals(true, state.get(IoTValues.HUMIDIFIER_STATE));

        verify(conn);
    }

    @Test
    public void testSetStateOfHumidityWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();

        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=0;HUS=0;HES=0;DS=0;PS=1" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);
        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=0;HUS=1;HES=0;DS=0;PS=1" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);

        replay(conn);

        IoTConnectManager mngr;
        Hashtable<String, Object> state;

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", false);
        state.put("DS", false);
        state.put("LS", false);
        state.put("PS", true);
        state.put("AS", true);
        state.put("CHS", true);
        state.put("HES", false);

        assertEquals(true, mngr.setState(state));

        mngr = new IoTConnectManager(conn);
        state = new Hashtable<String, Object>();
        state.put("HUS", true);
        state.put("DS", false);
        state.put("LS", false);
        state.put("PS", true);
        state.put("AS", true);
        state.put("CHS", true);
        state.put("HES", false);

        assertEquals(true, mngr.setState(state));

        verify(conn);
    }

    @Test
    public void testGetStateOfAllDisableAndEnableWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=71;HR=51;HUS=0;DS=0;LS=0;PS=0;AS=0;CHS=0;HES=0" + IoTValues.MSG_END);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:TR=0;HR=0;HUS=1;DS=1;LS=1;PS=1;AS=1;CHS=1;HES=1" + IoTValues.MSG_END);
        replay(conn);
        IoTConnectManager mngr = new IoTConnectManager(conn);
        Hashtable<String, Object> state;

        state = mngr.getState();
        assertState(new Object[]{71, 51, false, false, false, false, false, false, false}, state);

        state = mngr.getState();
        assertState(new Object[]{0, 0, true, true, true, true, true, true, true}, state);

        verify(conn);
    }

    @Test
    public void testSetStateOfAllDisableAndEnableWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();

        expect(conn.sendMessageToHouse("SS:CHS=0;AS=0;LS=0;HUS=0;HES=0;DS=0;PS=0" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);
        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=1;HUS=1;HES=1;DS=1;PS=1" + IoTValues.MSG_END))
                .andReturn(IoTValues.OK);

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

        verify(conn);
    }

    @Test
    public void testGetStateOfInvalidInputsWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn(null);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("");
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SS:" + IoTValues.MSG_END);
        expect(conn.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END))
                .andReturn("SU:");
        replay(conn);
        IoTConnectManager mngr = new IoTConnectManager(conn);
        Hashtable<String, Object> state;

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        state = mngr.getState();
        assertState(null, state);

        verify(conn);
    }

    @Test
    public void testSetStateForNullResponseWithMocking() {
        IoTConnection conn = partialMockBuilder(IoTConnection.class)
                .addMockedMethod("sendMessageToHouse", String.class).createMock();

        expect(conn.sendMessageToHouse("SS:CHS=1;AS=1;LS=0;HUS=0;HES=0;DS=0;PS=1" + IoTValues.MSG_END))
                .andReturn(null);

        replay(conn);

        IoTConnectManager mngr;
        Hashtable<String, Object> state;

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

        verify(conn);
    }
}
