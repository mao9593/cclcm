package hdsec.web.project.common.util;

public class EncryptFile {
	public static void encryptBuffer(byte[] buffer) {
		short[] mask = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int i = 0;
		for (i = 0; i < buffer.length; i++)
			buffer[i] ^= mask[i % 16];
	}
}
