import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zhong on 16/9/27.
 */
public class UnsafeTest {


    public static void showBytes() {

        try {
            Unsafe unsafe = getUnsafe();
            byte value  = Byte.MAX_VALUE;
            long bytes = 1;
            long memoryAddress = unsafe.allocateMemory(bytes);
            unsafe.putAddress(memoryAddress, value);

            System.out.println("[Byte] Writing value " + value + " under the " + memoryAddress + " address");

            long readValue = unsafe.getAddress(memoryAddress);
            System.out.println("[Byte] Reading " + readValue + " under the "+ memoryAddress + " address");

            unsafe.freeMemory(memoryAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Unsafe getUnsafe() throws Exception {

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        return (Unsafe) field.get(null);
    }


    public static void main(String[] args) {
        showBytes();
    }
}
