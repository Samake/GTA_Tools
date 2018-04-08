package com.gtasa.math;

import org.apache.commons.math3.util.FastMath;

public class Quaternion {
	
	public static float[] convertToEulerDegrees(float x, float y, float z, float w) {
		float[] angles = new float[3];
		
        float sqw = w * w;
        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        float unit = sqx + sqy + sqz + sqw;

        float test = x * y + z * w;
        
        if (test > 0.499 * unit) {
            angles[1] = (float) (2 * FastMath.atan2(x, w));
            angles[2] = (float) (Math.PI / 2);
            angles[0] = 0;
        } else if (test < -0.499 * unit) {
            angles[1] = (float) (-2 * FastMath.atan2(x, w));
            angles[2] = -(float) (Math.PI / 2);
            angles[0] = 0;
        } else {
            angles[1] = (float) FastMath.atan2(2 * y * w - 2 * x * z, sqx - sqy - sqz + sqw);
            angles[2] = (float) FastMath.asin(2 * test / unit);
            angles[0] = (float) FastMath.atan2(2 * x * w - 2 * y * z, -sqx + sqy - sqz + sqw);
        }
        
        angles[1] = (float) Math.toDegrees(angles[1]);
        angles[1] = (angles[1] % 360 + 360) % 360;
        angles[2] = (float) Math.toDegrees(angles[2])%360;
        angles[2] = (angles[2] % 360 + 360) % 360;
        angles[0] = (float) Math.toDegrees(angles[0])%360;
        angles[0] = (angles[0] % 360 + 360) % 360;
        
        return angles;
	}
}
