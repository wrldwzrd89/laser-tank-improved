package com.puttysoftware.lasertank.improved.fileio;

import java.io.IOException;

public abstract class GameIOWriter implements AutoCloseable {
    // Constructors
    protected GameIOWriter() throws IOException {
	super();
    }

    public abstract void writeBoolean(final boolean value) throws IOException;

    public abstract void writeByte(final byte value) throws IOException;

    // Methods
    public abstract void writeDouble(final double value) throws IOException;

    public abstract void writeInt(final int value) throws IOException;

    public abstract void writeLong(final long value) throws IOException;

    public abstract void writeString(final String value) throws IOException;
}
