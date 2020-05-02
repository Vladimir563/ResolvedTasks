package iotasks;

import java.io.*;
import java.util.Arrays;

public class MyOutputStream extends FilterOutputStream
{
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public MyOutputStream(OutputStream out)
    {
        super(out);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        byte[] res = new byte[b.length];

        byte key = (byte) 10;

        for (int i = 0; i < b.length; i++)
        {
            res[i] = (byte) (b[i] ^ key);
        }
        super.write(res);
    }
}
