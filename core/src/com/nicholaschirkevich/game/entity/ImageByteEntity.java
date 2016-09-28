package com.nicholaschirkevich.game.entity;

import java.io.File;

/**
 * Created by Колян on 19.09.2016.
 */
public class ImageByteEntity {
    private byte[] pixels;
    private int srcOffset;
    private int numElements;
    private File file;

    public ImageByteEntity() {
    }

    public ImageByteEntity(byte[] pixels, int srcOffset, int numElements) {
        this.pixels = pixels;
        this.srcOffset = srcOffset;
        this.numElements = numElements;
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }

    public int getSrcOffset() {
        return srcOffset;
    }

    public void setSrcOffset(int srcOffset) {
        this.srcOffset = srcOffset;
    }

    public int getNumElements() {
        return numElements;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
