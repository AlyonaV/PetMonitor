package com.springapp.mvc.protocol;

import java.net.SocketAddress;
import java.util.TimeZone;

/**
 * Created by Alona on 25.05.2016.
 */
public class GT06ProtocolDecoder {
    private boolean forceTimeZone = false;
    private final TimeZone timeZone = TimeZone.getTimeZone("UTC");

    public GT06ProtocolDecoder() {

    }

    public static final int MSG_LOGIN = 0x01;
    public static final int MSG_GPS = 0x10;
    public static final int MSG_LBS = 0x11;
    public static final int MSG_GPS_LBS_1 = 0x12;
    public static final int MSG_GPS_LBS_2 = 0x22;
    public static final int MSG_STATUS = 0x13;
    public static final int MSG_SATELLITE = 0x14;
    public static final int MSG_STRING = 0x15;
    public static final int MSG_GPS_LBS_STATUS_1 = 0x16;
    public static final int MSG_GPS_LBS_STATUS_2 = 0x26;
    public static final int MSG_GPS_LBS_STATUS_3 = 0x27;
    public static final int MSG_LBS_PHONE = 0x17;
    public static final int MSG_LBS_EXTEND = 0x18;
    public static final int MSG_LBS_STATUS = 0x19;
    public static final int MSG_GPS_PHONE = 0x1A;
    public static final int MSG_GPS_LBS_EXTEND = 0x1E;
    public static final int MSG_COMMAND_0 = 0x80;
    public static final int MSG_COMMAND_1 = 0x81;
    public static final int MSG_COMMAND_2 = 0x82;

    private static boolean isSupported(int type) {
        return hasGps(type) || hasLbs(type) || hasStatus(type);
    }

    private static boolean hasGps(int type) {
        return type == MSG_GPS || type == MSG_GPS_LBS_1 || type == MSG_GPS_LBS_2
                || type == MSG_GPS_LBS_STATUS_1 || type == MSG_GPS_LBS_STATUS_2 || type == MSG_GPS_LBS_STATUS_3
                || type == MSG_GPS_PHONE || type == MSG_GPS_LBS_EXTEND;
    }

    private static boolean hasLbs(int type) {
        return type == MSG_LBS || type == MSG_LBS_STATUS || type == MSG_GPS_LBS_1 || type == MSG_GPS_LBS_2
                || type == MSG_GPS_LBS_STATUS_1 || type ==  MSG_GPS_LBS_STATUS_2 || type == MSG_GPS_LBS_STATUS_3;
    }

    private static boolean hasStatus(int type) {
        return type == MSG_STATUS || type == MSG_LBS_STATUS
                || type == MSG_GPS_LBS_STATUS_1 || type == MSG_GPS_LBS_STATUS_2 || type == MSG_GPS_LBS_STATUS_3;
    }

//    private static void sendResponse(Channel channel, int type, int index) {
//        if (channel != null) {
//            ChannelBuffer response = ChannelBuffers.directBuffer(10);
//            response.writeByte(0x78); response.writeByte(0x78); // header
//            response.writeByte(0x05); // size
//            response.writeByte(type);
//            response.writeShort(index);
//            response.writeShort(Checksum.crc16(Checksum.CRC16_X25, response.toByteBuffer(2, 4)));
//            response.writeByte(0x0D); response.writeByte(0x0A); // ending
//            channel.write(response);
//        }
//    }
//
//    private void decodeGps(Position position, ChannelBuffer buf) {
//
//        DateBuilder dateBuilder = new DateBuilder(timeZone)
//                .setDate(buf.readUnsignedByte(), buf.readUnsignedByte(), buf.readUnsignedByte())
//                .setTime(buf.readUnsignedByte(), buf.readUnsignedByte(), buf.readUnsignedByte());
//        position.setTime(dateBuilder.getDate());
//
//        int length = buf.readUnsignedByte();
//        position.set(Event.KEY_SATELLITES, BitUtil.to(length, 4));
//        length = BitUtil.from(length, 4);
//
//        double latitude = buf.readUnsignedInt() / 60.0 / 30000.0;
//        double longitude = buf.readUnsignedInt() / 60.0 / 30000.0;
//        position.setSpeed(UnitsConverter.knotsFromKph(buf.readUnsignedByte()));
//
//        int flags = buf.readUnsignedShort();
//        position.setCourse(BitUtil.to(flags, 10));
//        position.setValid(BitUtil.check(flags, 12));
//
//        if (!BitUtil.check(flags, 10)) {
//            latitude = -latitude;
//        }
//        if (BitUtil.check(flags, 11)) {
//            longitude = -longitude;
//        }
//
//        position.setLatitude(latitude);
//        position.setLongitude(longitude);
//
//        if (BitUtil.check(flags, 14)) {
//            position.set(Event.KEY_IGNITION, BitUtil.check(flags, 15));
//        }
//
//        buf.skipBytes(length - 12); // skip reserved
//    }
//
//    private void decodeLbs(Position position, ChannelBuffer buf, boolean hasLength) {
//
//        int lbsLength = 0;
//        if (hasLength) {
//            lbsLength = buf.readUnsignedByte();
//        }
//
//        position.set(Event.KEY_MCC, buf.readUnsignedShort());
//        position.set(Event.KEY_MNC, buf.readUnsignedByte());
//        position.set(Event.KEY_LAC, buf.readUnsignedShort());
//        position.set(Event.KEY_CID, buf.readUnsignedMedium());
//
//        if (lbsLength > 0) {
//            buf.skipBytes(lbsLength - 9);
//        }
//    }
//
//    private void decodeStatus(Position position, ChannelBuffer buf) {
//
//        position.set(Event.KEY_ALARM, true);
//
//        int flags = buf.readUnsignedByte();
//
//        position.set(Event.KEY_IGNITION, BitUtil.check(flags, 1));
//        position.set(Event.KEY_STATUS, flags);
//        position.set(Event.KEY_POWER, buf.readUnsignedByte());
//        position.set(Event.KEY_GSM, buf.readUnsignedByte());
//    }
//
//    @Override
//    protected Object decode(
//            Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
//
//        ChannelBuffer buf = (ChannelBuffer) msg;
//
//        if (buf.readByte() != 0x78 || buf.readByte() != 0x78) {
//            return null;
//        }
//
//        int length = buf.readUnsignedByte(); // size
//        int dataLength = length - 5;
//
//        int type = buf.readUnsignedByte();
//
//        if (type == MSG_LOGIN) {
//
//            String imei = ChannelBuffers.hexDump(buf.readBytes(8)).substring(1);
//            buf.readUnsignedShort(); // type
//
//            // Timezone offset
//            if (dataLength > 10) {
//                int extensionBits = buf.readUnsignedShort();
//                int hours = (extensionBits >> 4) / 100;
//                int minutes = (extensionBits >> 4) % 100;
//                int offset = (hours * 60 + minutes) * 60;
//                if ((extensionBits & 0x8) != 0) {
//                    offset = -offset;
//                }
//                if (!forceTimeZone) {
//                    timeZone.setRawOffset(offset * 1000);
//                }
//            }
//
//            if (identify(imei, channel, remoteAddress)) {
//                buf.skipBytes(buf.readableBytes() - 6);
//                sendResponse(channel, type, buf.readUnsignedShort());
//            }
//
//        } else if (hasDeviceId()) {
//
//            if (isSupported(type)) {
//
//                Position position = new Position();
//                position.setDeviceId(getDeviceId());
//                position.setProtocol(getProtocolName());
//
//                if (hasGps(type)) {
//                    decodeGps(position, buf);
//                } else {
//                    getLastLocation(position, null);
//                }
//
//                if (hasLbs(type)) {
//                    decodeLbs(position, buf, hasStatus(type));
//                }
//
//                if (hasStatus(type)) {
//                    decodeStatus(position, buf);
//                }
//
//                if (type == MSG_GPS_LBS_1 && buf.readableBytes() == 4 + 6) {
//                    position.set(Event.KEY_ODOMETER, buf.readUnsignedInt());
//                }
//
//                if (buf.readableBytes() > 6) {
//                    buf.skipBytes(buf.readableBytes() - 6);
//                }
//                int index = buf.readUnsignedShort();
//                position.set(Event.KEY_INDEX, index);
//                sendResponse(channel, type, index);
//
//                return position;
//
//            } else {
//
//                buf.skipBytes(dataLength);
//                if (type != MSG_COMMAND_0 && type != MSG_COMMAND_1 && type != MSG_COMMAND_2) {
//                    sendResponse(channel, type, buf.readUnsignedShort());
//                }
//
//            }
//
//        }
//
//        return null;
//    }
}