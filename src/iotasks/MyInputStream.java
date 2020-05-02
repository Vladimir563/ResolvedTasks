package iotasks;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MyInputStream extends FilterInputStream
{
    protected MyInputStream(InputStream in)
    {
        super(in);
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        int count = super.read(b);
        byte key = (byte) 10;

        for (int i = 0; i < b.length; i++)
        {
            b[i] = (byte) (b[i] ^ key);
        }
        return count;
    }
}
